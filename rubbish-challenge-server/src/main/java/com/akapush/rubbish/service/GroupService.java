package com.akapush.rubbish.service;

import com.akapush.rubbish.domain.exception.UserAlreadExistsOnGroup;
import com.akapush.rubbish.domain.model.Group;
import com.akapush.rubbish.domain.model.User;

public interface GroupService {

	Iterable<Group> getGroupsFromUser(Long userId);

	Group getGroupFromId(Long groupId);

	Boolean addNewUserToGroup(User currentUser, Group group, String email, String message)
			throws UserAlreadExistsOnGroup;

	void acceptInvitation(User user, Long groupId);

	void declineInvitation(User user, Long groupId);

}
