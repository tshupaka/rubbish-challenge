package com.akapush.rubbish.domain.bean;

import java.util.List;

public class GroupStatistics {

	private List<UserRubbishSummary> userRubbishSummaries;
	private List<HistoGroup> histoGroups;

	public List<UserRubbishSummary> getUserRubbishSummaries() {
		return userRubbishSummaries;
	}

	public void setUserRubbishSummaries(List<UserRubbishSummary> userRubbishSummaries) {
		this.userRubbishSummaries = userRubbishSummaries;
	}

	public List<HistoGroup> getHistoGroups() {
		return histoGroups;
	}

	public void setHistoGroups(List<HistoGroup> histoGroups) {
		this.histoGroups = histoGroups;
	}

}
