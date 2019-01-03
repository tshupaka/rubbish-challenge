package com.akapush.rubbish.dao;

import org.springframework.data.repository.CrudRepository;

import com.akapush.rubbish.domain.model.Group;
import com.akapush.rubbish.domain.model.User;
import com.akapush.rubbish.domain.model.UserGroup;

public interface UserGroupDAO extends CrudRepository<UserGroup, Long> {

	UserGroup findByUserAndGroup(User user, Group group);

}
