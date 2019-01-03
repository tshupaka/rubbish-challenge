package com.akapush.rubbish.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akapush.rubbish.dao.RubbishDAO;
import com.akapush.rubbish.dao.RubbishTypeDAO;
import com.akapush.rubbish.domain.bean.RubbishGroupStats;
import com.akapush.rubbish.domain.bean.RubbishStats;
import com.akapush.rubbish.domain.model.Rubbish;
import com.akapush.rubbish.domain.model.RubbishType;
import com.akapush.rubbish.domain.model.User;

@Service
public class RubbishServiceImpl implements RubbishService {

	private static final long NB_MILLIS_IN_DAY = 1000 * 3600 * 24;

	private static final long NB_MILLIS_IN_WEEK = NB_MILLIS_IN_DAY * 7;

	@Autowired
	private RubbishDAO rubbishDAO;

	@Autowired
	private RubbishTypeDAO rubbishTypeDAO;

	@Autowired
	private UserService userService;

	@Override
	public Iterable<Rubbish> getRubbishes(Long userId) {
		User user = new User();
		user.setId(userId);
		return rubbishDAO.findByUserOrderByDate(user);
	}

	@Override
	public Rubbish addRubbish(Rubbish rubbish) {
		return rubbishDAO.save(rubbish);
	}

	@Override
	public void deleteRubbish(Long rubbishId) {
		rubbishDAO.delete(rubbishId);
	}

	@Override
	public Iterable<RubbishType> getRubbishesTypes() {
		return rubbishTypeDAO.findAll();
	}

	@Override
	public RubbishType getRubbishType(Long rubbishTypeId) {
		return rubbishTypeDAO.findOne(rubbishTypeId);
	}

	@Override
	public Iterable<Rubbish> getSmoothedRubbishes(Long userId) {
		Iterable<Rubbish> rubbishes = getRubbishes(userId);
		return smoothRubbishesList(rubbishes);

	}

	@Override
	public Iterable<Rubbish> smoothRubbishesList(Iterable<Rubbish> rubbishes) {
		Rubbish previousRubbish = null;
		// On recherche la première et la dernière date
		Date firstDate = null;
		Date lastDate = null;
		for (Rubbish rubbish : rubbishes) {
			Date currentDate = rubbish.getDate();
			if (firstDate == null) {
				firstDate = currentDate;
			}
			if (lastDate == null) {
				lastDate = currentDate;
			}
			if (currentDate.compareTo(firstDate) < 0) {
				firstDate = currentDate;
			}
			if (currentDate.compareTo(lastDate) > 0) {
				lastDate = currentDate;
			}
		}
		long nbTotalDay = (lastDate.getTime() - firstDate.getTime()) / NB_MILLIS_IN_DAY;

		int[] weightDistribution = new int[(int) nbTotalDay];
		int currentIndex = 0;
		for (Rubbish rubbish : rubbishes) {
			if (previousRubbish == null) {
				previousRubbish = rubbish;
				continue;
			}
			long nbDay = (rubbish.getDate().getTime() - previousRubbish.getDate().getTime()) / NB_MILLIS_IN_DAY;
			Integer weight = rubbish.getWeight();
			Integer nbUser = rubbish.getNbUser();
			int weightPerUserPerDay = weight / nbUser / (int) nbDay;

			for (int i = 0; i < nbDay && currentIndex < nbTotalDay; i++) {
				weightDistribution[currentIndex] = weightPerUserPerDay;
				currentIndex += 1;
			}
			previousRubbish = rubbish;

		}
		List<Rubbish> smoothedRubbishes = new ArrayList<Rubbish>();
		Rubbish rubbish = null;
		for (int i = 0; i < nbTotalDay; i++) {
			if (i % 7 == 0) {
				rubbish = new Rubbish();
				Date rubbishDate = new Date(firstDate.getTime() + i * NB_MILLIS_IN_DAY);
				rubbish.setDate(rubbishDate);
				rubbish.setWeight(0);
				smoothedRubbishes.add(rubbish);
			}
			rubbish.setWeight(rubbish.getWeight() + weightDistribution[i]);
		}

		return smoothedRubbishes;
	}

	@Override
	public RubbishGroupStats getRubbishGroupStats(Long groupId) {
		/*
		 * LocalDate today = LocalDate.now(); LocalDate previousMonth =
		 * today.minusMonths(1); Date previousMonthDate =
		 * Date.from(previousMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
		 * Iterable<Rubbish> rubbishes =
		 * rubbishDAO.findByUserGroupAndDateGreaterThan(new Group(groupId, ""),
		 * previousMonthDate);
		 * 
		 * Map<String, RubbishStats> mapRubbishes = new HashMap<String, RubbishStats>();
		 * Map<Date, Map<String, RubbishStats>> chronoMapRubbishes = new HashMap<Date,
		 * Map<String, RubbishStats>>(); for (Rubbish rubbish : rubbishes) {
		 * extractRubbishByName(mapRubbishes, rubbish);
		 * extractRubbishByDateAndName(chronoMapRubbishes, rubbish);
		 * 
		 * }
		 * 
		 * Set<RubbishStats> rubbishStats = new TreeSet<RubbishStats>();
		 * createRubbishStats(mapRubbishes, rubbishStats);
		 * 
		 * Set<ChronoRubbishStats> chronoRubbishStats = new
		 * TreeSet<ChronoRubbishStats>(); for (Entry<Date, Map<String, RubbishStats>>
		 * entry : chronoMapRubbishes.entrySet()) { Date date = entry.getKey();
		 * ChronoRubbishStats chronoRubbishStat = new ChronoRubbishStats();
		 * chronoRubbishStat.setDate(date); Set<RubbishStats> rubbishStats2 = new
		 * TreeSet<RubbishStats>(); createRubbishStats(entry.getValue(), rubbishStats2);
		 * chronoRubbishStat.setRubbishesStats(rubbishStats2);
		 * chronoRubbishStats.add(chronoRubbishStat); }
		 */
		RubbishGroupStats rubbishGroupStats = new RubbishGroupStats();
		// rubbishGroupStats.setTotalRubbishStats(rubbishStats);
		// rubbishGroupStats.setChronoRubbishStats(chronoRubbishStats);
		/*
		 * Map<String, Integer> stats = StreamSupport.stream(rubbishes.spliterator(),
		 * false). collect(Collectors.groupingBy(rubbish -> name=
		 * rubbish.getUser().getName())). compute(name, (k,v) -> {sum = sum+k;
		 * index++;});
		 */
		return rubbishGroupStats;
	}

	private void createRubbishStats(Map<String, RubbishStats> mapRubbishes, Set<RubbishStats> rubbishStats) {
		for (Entry<String, RubbishStats> entry : mapRubbishes.entrySet()) {
			RubbishStats rubbishStat = entry.getValue();
			rubbishStat.setName(entry.getKey());
			rubbishStat.setRubbisweight(rubbishStat.getTotalWeight() / rubbishStat.getNbUser());
			rubbishStats.add(rubbishStat);
		}
	}

	private void extractRubbishByDateAndName(Map<Date, Map<String, RubbishStats>> chronoMapRubbishes, Rubbish rubbish) {
		Date rubbishDate = rubbish.getDate();
		LocalDate truncateLocalDate = rubbishDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
				.withDayOfMonth(1);
		Date localDate = Date.from(truncateLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Map<String, RubbishStats> mapRubbishes = chronoMapRubbishes.get(localDate);
		if (mapRubbishes == null) {
			mapRubbishes = new HashMap<String, RubbishStats>();
			chronoMapRubbishes.put(localDate, mapRubbishes);
		}
		extractRubbishByName(mapRubbishes, rubbish);
	}

	private void extractRubbishByName(Map<String, RubbishStats> mapRubbishes, Rubbish rubbish) {
		RubbishStats rubbishStats = mapRubbishes.get(rubbish.getUser().getName());
		if (rubbishStats == null) {
			rubbishStats = new RubbishStats();
			mapRubbishes.put(rubbish.getUser().getName(), rubbishStats);
		}
		rubbishStats.addRubbishToTotal(rubbish.getWeight(), rubbish.getNbUser());
	}

}
