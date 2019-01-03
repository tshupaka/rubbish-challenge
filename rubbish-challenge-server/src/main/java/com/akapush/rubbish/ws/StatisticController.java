package com.akapush.rubbish.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akapush.rubbish.domain.bean.GroupStatistics;
import com.akapush.rubbish.domain.dto.GroupStatisticsDTO;
import com.akapush.rubbish.service.StatisticService;
import com.akapush.rubbish.utils.DTOHelper;

@RestController
public class StatisticController {

	@Autowired
	private StatisticService statisticService;

	@Autowired
	private DTOHelper dtoHelper;

	@RequestMapping("/api/statistics/group/{groupId}")
	public ResponseEntity<GroupStatisticsDTO> getGroups(@AuthenticationPrincipal String userId,
			@PathVariable("groupId") String groupId) {
		GroupStatistics groupStatistics = statisticService.getGroupStatistics(Long.valueOf(userId),
				Long.valueOf(groupId));
		GroupStatisticsDTO groupStatisticsDTO = dtoHelper.getGroupsStatisticsDTO(groupStatistics);
		return new ResponseEntity<GroupStatisticsDTO>(groupStatisticsDTO, HttpStatus.OK);
	}

}