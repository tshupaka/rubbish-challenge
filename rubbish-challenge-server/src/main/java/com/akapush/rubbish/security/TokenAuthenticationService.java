package com.akapush.rubbish.security;

import static java.util.Collections.emptyList;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {
	public static final long EXPIRATIONTIME = 864_000_000; // 10 days
	// TODO paramétriser
	static final String SECRET = "ThisIsASecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	/**
	 * Crée le token et l'ajoute à la requête
	 * 
	 * @param res
	 * @param user
	 * @throws IOException
	 */
	static void addAuthentication(HttpServletResponse res, UserContext user) throws IOException {
		String JWT = Jwts.builder().setSubject(String.valueOf(user.getId()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
		res.getOutputStream().write("{}".getBytes());
	}

	/**
	 * Extrait les informations sur l'utilisateur depuis le token
	 * 
	 * @param request
	 * @return
	 */
	static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
					.getSubject();

			return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;
		}
		return null;
	}
}