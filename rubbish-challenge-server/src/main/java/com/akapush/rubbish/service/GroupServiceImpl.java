package com.akapush.rubbish.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akapush.rubbish.dao.GroupDAO;
import com.akapush.rubbish.dao.UserGroupDAO;
import com.akapush.rubbish.domain.exception.UserAlreadExistsOnGroup;
import com.akapush.rubbish.domain.model.EUserGroupState;
import com.akapush.rubbish.domain.model.EUserState;
import com.akapush.rubbish.domain.model.Group;
import com.akapush.rubbish.domain.model.User;
import com.akapush.rubbish.domain.model.UserGroup;
import com.akapush.rubbish.utils.MailManager;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDAO groupDAO;

	@Autowired
	private UserService userService;

	@Autowired
	private MailManager mailManager;

	@Autowired
	private UserGroupDAO userGroupDAO;

	@Override
	public Iterable<Group> getGroupsFromUser(Long userId) {
		User user = new User();
		user.setId(userId);
		return groupDAO.findByUserGroupsUser(user);

	}

	@Override
	public Group getGroupFromId(Long groupId) {
		return groupDAO.findOne(groupId);
	}

	@Override
	public Boolean addNewUserToGroup(User currentUser, Group group, String email, String message)
			throws UserAlreadExistsOnGroup {
		/* Check if email exists in database */
		User user = userService.getUserByEmail(email);
		if (user != null && !EUserState.NONE.equals(user.getUserState())) {
			// Check if user is not already registred on group
			for (UserGroup userGroup : group.getUserGroups()) {
				if (userGroup.getUser().equals(user)) {
					throw new UserAlreadExistsOnGroup(user);
				}
			}
			UserGroup userGroup = new UserGroup();
			userGroup.setGroup(group);
			userGroup.setUser(user);

			userGroup.setDate(new Date());
			userGroup.setUserGroupState(EUserGroupState.EXISTING);
			userGroupDAO.save(userGroup);
			mailManager.sendExistingUserInvitationMail(email, currentUser.getName(), currentUser.getEmail(), message);
		} else {
			user = new User();
			String userKey = userService.createUser(user, email, EUserState.NONE);
			UserGroup userGroup = new UserGroup();
			userGroup.setGroup(group);
			userGroup.setUser(user);
			userGroup.setDate(new Date());
			userGroup.setUserGroupState(EUserGroupState.INVITED);
			userGroupDAO.save(userGroup);
			mailManager.sendNewUserInvitationMail(email, currentUser.getName(), currentUser.getEmail(), message, group,
					userKey);
		}

		return true;
	}

	@Override
	public void acceptInvitation(User user, Long groupId) {
		Group group = new Group();
		group.setId(groupId);
		UserGroup userGroup = userGroupDAO.findByUserAndGroup(user, group);
		userGroup.setUserGroupState(EUserGroupState.VALIDATE);
		userGroupDAO.save(userGroup);

	}

	@Override
	public void declineInvitation(User user, Long groupId) {
		Group group = new Group();
		group.setId(groupId);
		UserGroup userGroup = userGroupDAO.findByUserAndGroup(user, group);
		userGroup.setUserGroupState(EUserGroupState.REFUSED);
		userGroupDAO.save(userGroup);

	}

}
