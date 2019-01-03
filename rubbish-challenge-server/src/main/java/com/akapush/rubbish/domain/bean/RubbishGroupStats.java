package com.akapush.rubbish.domain.bean;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class RubbishGroupStats {

	private Set<RubbishStats> totalRubbishStats;

	private Set<ChronoRubbishStats> chronoRubbishStats;

	public Set<RubbishStats> getTotalRubbishStats() {
		return totalRubbishStats;
	}

	public void setTotalRubbishStats(Set<RubbishStats> totalRubbishStats) {
		this.totalRubbishStats = totalRubbishStats;
	}

	public Set<ChronoRubbishStats> getChronoRubbishStats() {
		return chronoRubbishStats;
	}

	public void setChronoRubbishStats(Set<ChronoRubbishStats> chronoRubbishStats) {
		this.chronoRubbishStats = chronoRubbishStats;
	}

}
