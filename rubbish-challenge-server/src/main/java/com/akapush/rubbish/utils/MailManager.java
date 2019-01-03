package com.akapush.rubbish.utils;

import com.akapush.rubbish.domain.model.Group;
import com.akapush.rubbish.domain.model.User;

public interface MailManager {

	public void sendRegistrationEmail(User user, String registerId);

	public void sendNewUserInvitationMail(String email, String username, String userEmail, String message, Group group,
			String userKey);

	public void sendExistingUserInvitationMail(String destEmail, String username, String userEmail, String message);

}
