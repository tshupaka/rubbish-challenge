package com.akapush.rubbish.ws;

import static com.akapush.rubbish.security.SecurityConstants.EXPIRATION_TIME;
import static com.akapush.rubbish.security.SecurityConstants.SECRET;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akapush.rubbish.domain.dto.UserDTO;
import com.akapush.rubbish.domain.exception.UserAlreadyRegisterException;
import com.akapush.rubbish.domain.model.User;
import com.akapush.rubbish.security.TokenAuthenticationService;
import com.akapush.rubbish.service.UserService;
import com.akapush.rubbish.utils.DTOHelper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private DTOHelper dtoHelper;

	@RequestMapping("/api/users")
	public ResponseEntity<Iterable<User>> getUsers() {
		Iterable<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/auth", method = RequestMethod.POST)
	public ResponseEntity<UserDTO> authenticate(@RequestBody User pUser) {
		User user = userService.authenticateUser(pUser.getEmail(), pUser.getPassword());
		if (user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.UNAUTHORIZED);
		}
		String token = Jwts.builder().setSubject(String.valueOf(user.getId()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
		UserDTO userDTO = dtoHelper.getUserDTO(user, token);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/register", method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody User pUser) {
		try {
			String email = pUser.getEmail();
			if (email == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			userService.register(pUser.getEmail());
		} catch (UserAlreadyRegisterException e) {
			return new ResponseEntity<String>("{\"code\": 100}", HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<User>(HttpStatus.OK);
	}

	@RequestMapping(value = "/api/register", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> validRegistration(@RequestParam Map<String, String> parameters) {
		String key = parameters.get("id");
		byte[] keyPayloadTab = Base64Utils.decodeFromString(key);
		try {
			String keyPayload = new String(keyPayloadTab, "UTF-8");
			if (StringUtils.isNotEmpty(keyPayload)) {
				String[] values = keyPayload.split("\\|");
				if (values.length == 2) {
					User user = userService.validRegister(values[0], values[1]);
					String jwtToken = Jwts.builder().setSubject(String.valueOf(user.getId()))
							.setExpiration(
									new Date(System.currentTimeMillis() + TokenAuthenticationService.EXPIRATIONTIME))
							.signWith(SignatureAlgorithm.HS512, SECRET).compact();
					dtoHelper.getUserDTO(user, jwtToken);
					return new ResponseEntity<UserDTO>(HttpStatus.OK);
				}
			}
			return new ResponseEntity<UserDTO>(HttpStatus.FORBIDDEN);

		} catch (UnsupportedEncodingException e) {
			return new ResponseEntity<UserDTO>(HttpStatus.FORBIDDEN);
		}

	}

	@RequestMapping(value = "/api/register/accept", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> acceptInvitation(@RequestParam Map<String, String> parameters) {
		String token = parameters.get("id");
		Long groupId = Long.valueOf(parameters.get("group"));
		byte[] keyPayloadTab = Base64Utils.decodeFromString(token);
		try {
			String keyPayload = new String(keyPayloadTab, "UTF-8");
			if (StringUtils.isNotEmpty(keyPayload)) {
				String[] values = keyPayload.split("\\|");
				if (values.length == 2) {
					String email = values[0];
					String key = values[1];
					User user = userService.acceptInvitation(email, key, groupId);
					String jwtToken = Jwts.builder().setSubject(String.valueOf(user.getId()))
							.setExpiration(
									new Date(System.currentTimeMillis() + TokenAuthenticationService.EXPIRATIONTIME))
							.signWith(SignatureAlgorithm.HS512, SECRET).compact();
					dtoHelper.getUserDTO(user, jwtToken);
					return new ResponseEntity<UserDTO>(HttpStatus.OK);
				}
			}
			return new ResponseEntity<UserDTO>(HttpStatus.FORBIDDEN);

		} catch (UnsupportedEncodingException e) {
			return new ResponseEntity<UserDTO>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/api/register/decline", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> declineInvitation(@RequestParam Map<String, String> parameters) {
		String token = parameters.get("id");
		Long groupId = Long.valueOf(parameters.get("group"));
		byte[] keyPayloadTab = Base64Utils.decodeFromString(token);
		try {
			String keyPayload = new String(keyPayloadTab, "UTF-8");
			if (StringUtils.isNotEmpty(keyPayload)) {
				String[] values = keyPayload.split("\\|");
				if (values.length == 2) {
					String email = values[0];
					String key = values[1];
					User user = userService.declineInvitation(email, key, groupId);
					String jwtToken = Jwts.builder().setSubject(String.valueOf(user.getId()))
							.setExpiration(
									new Date(System.currentTimeMillis() + TokenAuthenticationService.EXPIRATIONTIME))
							.signWith(SignatureAlgorithm.HS512, SECRET).compact();
					dtoHelper.getUserDTO(user, jwtToken);
					return new ResponseEntity<UserDTO>(HttpStatus.OK);
				}
			}
			return new ResponseEntity<UserDTO>(HttpStatus.FORBIDDEN);

		} catch (UnsupportedEncodingException e) {
			return new ResponseEntity<UserDTO>(HttpStatus.FORBIDDEN);
		}
	}
}