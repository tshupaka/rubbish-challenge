package com.akapush.rubbish.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.akapush.rubbish.domain.model.Group;

@Component
public class MailContentBuilder {

	private TemplateEngine templateEngine;

	@Autowired
	public MailContentBuilder(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	public String buildRegisterMail(String registerId) {
		Context context = new Context();
		// TODO paramétriser
		context.setVariable("registerUrl", "https://www.missionzerodechet.fr/register/valid?id=" + registerId);
		return templateEngine.process("registerTemplate", context);
	}

	public String buildExistingUserInvitation(String username, String userEmail, String message) {
		Context context = new Context();
		context.setVariable("username", username);
		context.setVariable("userEmail", userEmail);
		context.setVariable("message", message);
		return templateEngine.process("existingUserInvitation", context);
	}

	public String buildNewUserInvitation(String username, String userEmail, String message, Group group,
			String userKey) {
		Context context = new Context();
		context.setVariable("username", username);
		context.setVariable("userEmail", userEmail);
		context.setVariable("message", message);
		context.setVariable("acceptUrl",
				"https://www.missionzerodechet.fr/register/accept?id=" + userKey + "&groupId=" + group.getId());
		context.setVariable("declineUrl",
				"https://www.missionzerodechet.fr/register/decline?id=" + userKey + "&groupId=" + group.getId());
		return templateEngine.process("newUserInvitation", context);
	}

}