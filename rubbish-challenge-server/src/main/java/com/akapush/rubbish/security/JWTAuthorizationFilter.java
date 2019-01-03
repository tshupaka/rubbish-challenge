package com.akapush.rubbish.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Filtre en charge de la vérification des habilitations d'un utilisateur pour
 * une requête
 * 
 * @author eric
 *
 */
public class JWTAuthorizationFilter extends GenericFilterBean {

	/**
	 * Filtre la requête. Si le JWTToken est renseigné, le contexte de sécurité est
	 * mis en place
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		Authentication authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest) request);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}
}