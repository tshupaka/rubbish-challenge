package com.akapush.rubbish.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.akapush.rubbish.domain.bean.GroupStatistics;
import com.akapush.rubbish.domain.bean.HistoGroup;
import com.akapush.rubbish.domain.bean.HistoGroupWeight;
import com.akapush.rubbish.domain.bean.UserRubbishSummary;
import com.akapush.rubbish.domain.dto.GroupDTO;
import com.akapush.rubbish.domain.dto.GroupStatisticsDTO;
import com.akapush.rubbish.domain.dto.HistoGroupDTO;
import com.akapush.rubbish.domain.dto.HistoGroupWeightDTO;
import com.akapush.rubbish.domain.dto.RubbishDTO;
import com.akapush.rubbish.domain.dto.UserDTO;
import com.akapush.rubbish.domain.dto.UserRubbishSummaryDTO;
import com.akapush.rubbish.domain.model.Group;
import com.akapush.rubbish.domain.model.Rubbish;
import com.akapush.rubbish.domain.model.RubbishType;
import com.akapush.rubbish.domain.model.User;

@Component
public class DTOHelperImpl implements DTOHelper {

	@Override
	public UserDTO getUserDTO(User user, String jwtToken) {
		UserDTO userDTO = new UserDTO(user.getEmail(), user.getName(), user.getUserState().toString(), jwtToken);
		return userDTO;
	}

	@Override
	public Rubbish getRubbish(User user, RubbishDTO rubbishDTO) {
		Rubbish rubbish = new Rubbish(null, rubbishDTO.getWeight(), rubbishDTO.getNumber(), rubbishDTO.getDate(), user);
		return rubbish;
	}

	@Override
	public Iterable<RubbishDTO> getRubbishesDTO(Iterable<Rubbish> rubbishes) {
		List<RubbishDTO> rubbishesDTO = new ArrayList<RubbishDTO>();
		for (Rubbish rubbish : rubbishes) {
			RubbishDTO rubbishDTO = getRubbishDTO(rubbish);
			rubbishesDTO.add(rubbishDTO);
		}
		return rubbishesDTO;
	}

	@Override
	public RubbishDTO getRubbishDTO(Rubbish rubbish) {
		RubbishType type = rubbish.getRubbishType();
		long typeId = 0;
		String rubbishTypeLabel = null;
		if (type != null) {
			typeId = type.getId();
			rubbishTypeLabel = type.getLabel();
		}
		RubbishDTO rubbishDTO = new RubbishDTO(rubbish.getDate(), rubbish.getWeight(), rubbish.getNbUser(),
				(int) typeId, rubbishTypeLabel);
		return rubbishDTO;
	}

	@Override
	public List<GroupDTO> getGroupsDTO(Iterable<Group> groups) {
		List<GroupDTO> groupsDTO = new ArrayList<GroupDTO>();
		for (Group group : groups) {
			GroupDTO groupDTO = getGroupDTO(group);
			groupsDTO.add(groupDTO);
		}
		return groupsDTO;
	}

	private GroupDTO getGroupDTO(Group group) {
		GroupDTO groupDTO = new GroupDTO();
		groupDTO.setId((group.getId()));
		groupDTO.setName(group.getName());
		return groupDTO;

	}

	@Override
	public GroupStatisticsDTO getGroupsStatisticsDTO(GroupStatistics groupStatistics) {
		GroupStatisticsDTO groupStatisticDTO = new GroupStatisticsDTO();
		List<UserRubbishSummaryDTO> userRubbishSummariesDTO = new ArrayList<UserRubbishSummaryDTO>();
		for (UserRubbishSummary userRubbishSummary : groupStatistics.getUserRubbishSummaries()) {
			UserRubbishSummaryDTO userRubbishSummaryDTO = getUserRubbishSummaryDTO(userRubbishSummary);
			userRubbishSummariesDTO.add(userRubbishSummaryDTO);
		}
		groupStatisticDTO.setUserRubbishSummaries(userRubbishSummariesDTO);
		List<HistoGroupDTO> histoGroupsDTO = new ArrayList<HistoGroupDTO>();
		for (HistoGroup histoGroup : groupStatistics.getHistoGroups()) {
			HistoGroupDTO histoGroupDTO = getHistoGroupDTO(histoGroup);
			histoGroupsDTO.add(histoGroupDTO);
		}
		groupStatisticDTO.setHistoGroups(histoGroupsDTO);
		return groupStatisticDTO;
	}

	private HistoGroupDTO getHistoGroupDTO(HistoGroup histoGroup) {
		List<HistoGroupWeightDTO> histoGroupWeights = new ArrayList<HistoGroupWeightDTO>();
		for (HistoGroupWeight histoGroupWeight : histoGroup.getValues()) {
			histoGroupWeights.add(getHistoGroupWeightDTO(histoGroupWeight));
		}
		HistoGroupDTO histoGroupDTO = new HistoGroupDTO(histoGroup.getDate(), histoGroupWeights);
		return histoGroupDTO;
	}

	private HistoGroupWeightDTO getHistoGroupWeightDTO(HistoGroupWeight histoGroupWeight) {
		String username = histoGroupWeight.getUsername();
		int weight = histoGroupWeight.getWeight();
		HistoGroupWeightDTO histoGroupWeightDTO = new HistoGroupWeightDTO(username, weight);
		return histoGroupWeightDTO;
	}

	private UserRubbishSummaryDTO getUserRubbishSummaryDTO(UserRubbishSummary userRubbishSummary) {
		UserRubbishSummaryDTO userRubbishSummaryDTO = new UserRubbishSummaryDTO();
		userRubbishSummaryDTO.setUsername(userRubbishSummary.getUsername());
		userRubbishSummaryDTO.setWeight30d(userRubbishSummary.getWeight30d());
		userRubbishSummaryDTO.setWeight365d(userRubbishSummary.getWeight365d());
		return userRubbishSummaryDTO;
	}

}
