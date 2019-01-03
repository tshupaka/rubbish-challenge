package com.akapush.rubbish.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.akapush.rubbish.domain.model.Group;
import com.akapush.rubbish.domain.model.User;

@Transactional()
public interface GroupDAO extends CrudRepository<Group, Long> {

	Iterable<Group> findByUserGroupsUser(User user);

}
