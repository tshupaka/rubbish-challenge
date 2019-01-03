package com.akapush.rubbish.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.akapush.rubbish.dao.UserDAO;
import com.akapush.rubbish.domain.exception.UserAlreadyRegisterException;
import com.akapush.rubbish.domain.model.EUserState;
import com.akapush.rubbish.domain.model.User;
import com.akapush.rubbish.utils.MailManager;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private GroupService groupService;

	@Autowired
	private MailManager mailManager;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public Iterable<User> getAllUsers() {
		Iterable<User> users = userDAO.findAll();
		return users;
	}

	@Override
	public User getUserById(Long userId) {
		User user = userDAO.findOne(userId);
		return user;
	}

	@Override
	public User authenticateUser(String email, String password) {
		User user = userDAO.findByEmail(email);
		if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public User register(String email) throws UserAlreadyRegisterException {
		// TODO vérifier validité email
		User user = userDAO.findByEmail(email);
		if (user != null) {
			throw new UserAlreadyRegisterException(email);
		}
		user = new User();
		String userKey = createUser(user, email, EUserState.REGISTERING);
		sendRegistrationEmail(user, userKey);
		return user;
	}

	@Override
	public String createUser(User user, String email, EUserState userState) {

		user.setEmail(email);
		String randomKey = UUID.randomUUID().toString();
		String userKey = generateUserKey(email, randomKey);
		user.setUuidRegister(randomKey);
		user.setActionDate(new Date());
		user.setUserState(userState);
		userDAO.save(user);
		return userKey;
	}

	private String generateUserKey(String email, String randomKey) {
		String key = email + "|" + randomKey;
		return Base64Utils.encodeToString(key.getBytes());
	}

	private void sendRegistrationEmail(User user, String userKey) {
		mailManager.sendRegistrationEmail(user, userKey);

	}

	@Override
	public User validRegister(String email, String key) {
		User user = userDAO.findByEmailAndUuidRegister(email, key);
		if (user != null) {
			user.setUserState(EUserState.EMAIL_VALID);
			user.setUuidRegister(null);
			user.setActionDate(null);
			userDAO.save(user);
		}
		return user;

	}

	@Override
	public User getUserByEmail(String email) {
		return userDAO.findByEmail(email);

	}

	@Override
	public User acceptInvitation(String email, String key, Long groupId) {
		User user = validRegister(email, key);
		groupService.acceptInvitation(user, groupId);
		return user;
	}

	@Override
	public User declineInvitation(String email, String key, Long groupId) {
		User user = userDAO.findByEmailAndUuidRegister(email, key);
		groupService.declineInvitation(user, groupId);
		return user;
	}

}
