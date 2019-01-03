package com.akapush.rubbish.service;

import com.akapush.rubbish.domain.exception.UserAlreadyRegisterException;
import com.akapush.rubbish.domain.model.EUserState;
import com.akapush.rubbish.domain.model.User;

public interface UserService {

	public Iterable<User> getAllUsers();

	public User getUserById(Long userId);

	public User authenticateUser(String email, String password);

	public User register(String email) throws UserAlreadyRegisterException;

	public User validRegister(String email, String key);

	public User getUserByEmail(String email);

	public String createUser(User user, String email, EUserState userState);

	public User acceptInvitation(String email, String key, Long groupId);

	public User declineInvitation(String email, String key, Long groupId);

}
