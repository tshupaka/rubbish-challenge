package com.akapush.rubbish.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akapush.rubbish.dao.RubbishDAO;
import com.akapush.rubbish.dao.UserDAO;
import com.akapush.rubbish.domain.bean.GroupStatistics;
import com.akapush.rubbish.domain.bean.HistoGroup;
import com.akapush.rubbish.domain.bean.HistoGroupWeight;
import com.akapush.rubbish.domain.bean.UserRubbishSummary;
import com.akapush.rubbish.domain.model.Group;
import com.akapush.rubbish.domain.model.Rubbish;
import com.akapush.rubbish.domain.model.User;

@Service
public class StatisticServiceImpl implements StatisticService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private RubbishDAO rubbishDAO;

	@Autowired
	private RubbishService rubbishService;

	@Override
	public GroupStatistics getGroupStatistics(Long userId, Long groupId) {
		Group group = new Group();
		group.setId(groupId);

		/*
		 * User user = new User(); user.setId(userId);
		 */
		List<User> users = userDAO.findByUserGroupsGroup(group);
		// TODO vérifier si USER € USERS (sécurité)

		Calendar lastYear = GregorianCalendar.getInstance();
		lastYear.add(Calendar.YEAR, -1);

		Calendar lastMonth = GregorianCalendar.getInstance();
		lastMonth.add(Calendar.DAY_OF_YEAR, -30);
		Date lastMonthDate = lastMonth.getTime();

		List<UserRubbishSummary> userRubbishSummaries = getUserRubbishSummaries(users, lastYear, lastMonthDate);
		List<HistoGroup> histoGroups = getHistoGroups(users, lastYear);
		GroupStatistics groupStatistics = new GroupStatistics();
		groupStatistics.setUserRubbishSummaries(userRubbishSummaries);

		groupStatistics.setHistoGroups(histoGroups);
		return groupStatistics;
	}

	private List<HistoGroup> getHistoGroups(List<User> users, Calendar lastYear) {
		List<HistoGroup> histoGroups = new ArrayList<HistoGroup>();
		Map<Date, List<HistoGroupWeight>> histoGroupWeightsMap = new HashMap<Date, List<HistoGroupWeight>>();
		for (User user : users) {

			String username = user.getName();
			List<Rubbish> rubbishes = rubbishDAO.findByUserAndDateGreaterThanOrderByDate(user, lastYear.getTime());
			Iterable<Rubbish> smoothRubbishes = rubbishService.smoothRubbishesList(rubbishes);
			for (Rubbish rubbish : smoothRubbishes) {
				Date dateRubbish = rubbish.getDate();
				List<HistoGroupWeight> histoGroupWeights = histoGroupWeightsMap.get(dateRubbish);
				if (histoGroupWeights == null) {
					histoGroupWeights = new ArrayList<HistoGroupWeight>();
					histoGroupWeightsMap.put(dateRubbish, histoGroupWeights);
				}
				HistoGroupWeight histoGroupWeight = new HistoGroupWeight(username, rubbish.getWeight());
				histoGroupWeights.add(histoGroupWeight);
			}

		}
		for (Entry<Date, List<HistoGroupWeight>> histoGroupWeightEntry : histoGroupWeightsMap.entrySet()) {
			HistoGroup histoGroup = new HistoGroup(histoGroupWeightEntry.getKey(), histoGroupWeightEntry.getValue());
			histoGroups.add(histoGroup);
		}
		Collections.<HistoGroup>sort(histoGroups);
		return histoGroups;
	}

	private List<UserRubbishSummary> getUserRubbishSummaries(List<User> users, Calendar lastYear, Date lastMonthDate) {
		List<UserRubbishSummary> userRubbishSummaries = new ArrayList<UserRubbishSummary>();
		for (User user : users) {
			UserRubbishSummary userRubbishSummary = new UserRubbishSummary();
			userRubbishSummary.setUsername(user.getName());
			List<Rubbish> rubbishes = rubbishDAO.findByUserAndDateGreaterThanOrderByDate(user, lastYear.getTime());
			for (Rubbish rubbish : rubbishes) {
				Date date = rubbish.getDate();
				double rubbishWeight = Math.floor((double) rubbish.getWeight() / (double) rubbish.getNbUser());
				if (date.after(lastMonthDate)) {
					double weight30d = userRubbishSummary.getWeight30d();
					userRubbishSummary.setWeight30d(weight30d + rubbishWeight);
				}
				double weight365d = userRubbishSummary.getWeight365d();
				userRubbishSummary.setWeight365d(weight365d + rubbishWeight);
			}
			userRubbishSummary.setWeight30d(userRubbishSummary.getWeight30d() / 1000.0);
			userRubbishSummary.setWeight365d(userRubbishSummary.getWeight365d() / 1000.0);
			userRubbishSummaries.add(userRubbishSummary);
		}
		return userRubbishSummaries;
	}

}
