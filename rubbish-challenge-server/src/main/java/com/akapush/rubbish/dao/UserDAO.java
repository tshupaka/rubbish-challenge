package com.akapush.rubbish.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.akapush.rubbish.domain.model.Group;
import com.akapush.rubbish.domain.model.User;

@Transactional
public interface UserDAO extends CrudRepository<User, Long> {

	User findByEmailAndPassword(String email, String password);

	User findByEmail(String email);

	User findByEmailAndUuidRegister(String email, String key);

	List<User> findByUserGroupsGroup(Group groupId);

}
