package com.akapush.rubbish.utils;

import java.util.List;

import com.akapush.rubbish.domain.bean.GroupStatistics;
import com.akapush.rubbish.domain.dto.GroupDTO;
import com.akapush.rubbish.domain.dto.GroupStatisticsDTO;
import com.akapush.rubbish.domain.dto.RubbishDTO;
import com.akapush.rubbish.domain.dto.UserDTO;
import com.akapush.rubbish.domain.model.Group;
import com.akapush.rubbish.domain.model.Rubbish;
import com.akapush.rubbish.domain.model.User;

public interface DTOHelper {

	public UserDTO getUserDTO(User user, String jwtToken);

	public Rubbish getRubbish(User user, RubbishDTO rubbishDTO);

	public Iterable<RubbishDTO> getRubbishesDTO(Iterable<Rubbish> rubbishes);

	public RubbishDTO getRubbishDTO(Rubbish rubbish);

	public List<GroupDTO> getGroupsDTO(Iterable<Group> groups);

	public GroupStatisticsDTO getGroupsStatisticsDTO(GroupStatistics groups);

}
