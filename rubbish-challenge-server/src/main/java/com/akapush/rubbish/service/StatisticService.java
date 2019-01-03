package com.akapush.rubbish.service;

import com.akapush.rubbish.domain.bean.GroupStatistics;

public interface StatisticService {

	public GroupStatistics getGroupStatistics(Long userId, Long groupId);

}
