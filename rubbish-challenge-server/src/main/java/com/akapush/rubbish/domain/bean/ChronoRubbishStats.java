package com.akapush.rubbish.domain.bean;

import java.util.Date;
import java.util.Set;

public class ChronoRubbishStats implements Comparable<ChronoRubbishStats>  {

	private Date date;

	private Set<RubbishStats> rubbishesStats;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<RubbishStats> getRubbishesStats() {
		return rubbishesStats;
	}

	public void setRubbishesStats(Set<RubbishStats> rubbishesStats) {
		this.rubbishesStats = rubbishesStats;
	}

	@Override
	public int compareTo(ChronoRubbishStats o) {
		return this.date.compareTo(o.getDate());
	}

}
