package com.akapush.rubbish.domain.dto;

import java.util.List;

public class GroupStatisticsDTO {

	private List<UserRubbishSummaryDTO> userRubbishSummaries;

	private List<HistoGroupDTO> histoGroups;

	public List<UserRubbishSummaryDTO> getUserRubbishSummaries() {
		return userRubbishSummaries;
	}

	public void setUserRubbishSummaries(List<UserRubbishSummaryDTO> userRubbishSummaries) {
		this.userRubbishSummaries = userRubbishSummaries;
	}

	public List<HistoGroupDTO> getHistoGroups() {
		return histoGroups;
	}

	public void setHistoGroups(List<HistoGroupDTO> histoGroups) {
		this.histoGroups = histoGroups;
	}

}
