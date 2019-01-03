package com.akapush.rubbish.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.akapush.rubbish.domain.model.Group;
import com.akapush.rubbish.domain.model.User;

@Component
public class MailManagerImpl implements MailManager {

	@Autowired

	public JavaMailSender emailSender;

	@Autowired
	public MailContentBuilder mailContentBuilder;

	@Override
	public void sendRegistrationEmail(User user, String registerId) {

		String content = mailContentBuilder.buildRegisterMail(registerId);
		sendEmail("contact@missionzerodechet.fr", user.getEmail(), "Confirmation de l'inscription", content);
	}

	@Override
	public void sendExistingUserInvitationMail(String destEmail, String username, String userEmail, String message) {
		String content = mailContentBuilder.buildExistingUserInvitation(username, userEmail, message);
		sendEmail("contact@missionzerodechet.fr", destEmail, "Adhésion à un groupe", content);
	}

	@Override
	public void sendNewUserInvitationMail(String email, String username, String userEmail, String message, Group group,
			String userKey) {
		String content = mailContentBuilder.buildNewUserInvitation(username, userEmail, message, group, userKey);
		sendEmail("contact@missionzerodechet.fr", email, "Rejoignez la communauté zéro déchet", content);

	}

	private void sendEmail(String from, String email, String subject, String content) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom(from);
			messageHelper.setTo(email);
			messageHelper.setSubject(subject);

			messageHelper.setText(content, true);
		};
		try {
			emailSender.send(messagePreparator);

		} catch (MailException e) {
			e.printStackTrace();
			// runtime exception; compiler will not force you to handle it
		}
	}

}
