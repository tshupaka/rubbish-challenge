package com.akapush.rubbish.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.akapush.rubbish.domain.model.Rubbish;
import com.akapush.rubbish.domain.model.User;

@Transactional
public interface RubbishDAO extends CrudRepository<Rubbish, Long> {

	/*
	 * Iterable<Rubbish> findByUserGroupAndDateGreaterThan(Group group, Date
	 * previousMonthDate);
	 */

	Iterable<Rubbish> findByUserOrderByDate(User user);

	List<Rubbish> findByUserAndDateGreaterThanOrderByDate(User user, Date previousYearDate);
}
