package com.akapush.rubbish.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akapush.rubbish.domain.dto.GroupDTO;
import com.akapush.rubbish.domain.dto.UserInvitationDTO;
import com.akapush.rubbish.domain.exception.UserAlreadExistsOnGroup;
import com.akapush.rubbish.domain.model.Group;
import com.akapush.rubbish.domain.model.User;
import com.akapush.rubbish.service.GroupService;
import com.akapush.rubbish.service.UserService;
import com.akapush.rubbish.utils.DTOHelper;

@RestController
public class GroupController {

	@Autowired
	private GroupService groupService;

	@Autowired
	private UserService userService;

	@Autowired
	private DTOHelper dtoHelper;

	@RequestMapping("/api/groups")
	public ResponseEntity<Iterable<GroupDTO>> getGroups(@AuthenticationPrincipal String userId) {
		Iterable<Group> groups = groupService.getGroupsFromUser(Long.valueOf(userId));
		List<GroupDTO> groupsDTO = dtoHelper.getGroupsDTO(groups);
		return new ResponseEntity<Iterable<GroupDTO>>(groupsDTO, HttpStatus.OK);
	}

	@RequestMapping("/api/group/invitation/{groupId}")
	public ResponseEntity<Boolean> addNewUserToGroup(@AuthenticationPrincipal String userId,
			@RequestBody UserInvitationDTO userInvitationDTO, @PathVariable("groupId") Long groupId) {
		// TODO prevent attack from custom groupId
		Group group = groupService.getGroupFromId(groupId);
		User currentUser = userService.getUserById(Long.valueOf(userId));
		Boolean result = false;
		try {
			result = groupService.addNewUserToGroup(currentUser, group, userInvitationDTO.getEmail(),
					userInvitationDTO.getMessage());
		} catch (UserAlreadExistsOnGroup e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}