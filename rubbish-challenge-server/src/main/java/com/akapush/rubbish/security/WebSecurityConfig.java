package com.akapush.rubbish.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Classe de configuration de la sécurité Spring.<br/>
 * 
 * Lorsqu'un utilisateur se logue sur l'url /api/auth, il passe par le filtre
 * {@link JWTAuthenticationFilter} qui :
 * 
 * <ul>
 * <li>Transforme le flux JSON en Objet Java {@link AccountCredentials}
 * <li>Demande l'authentification à l'AuthenticationManager
 * <li>L'AuthenticationManger est configuré pour utiliser la classe
 * {@link UserDetailsServiceImpl} pour récupérer l'utilisateur à partir de son
 * email
 * <li>L'AuthenticationManager vérifie le mot de passe en utilisant l'algorythme
 * bcryp défini dans cette classe
 * <li>Si le mot de passe est correct, la méthode successfulAuthentication de
 * {@link JWTAuthenticationFilter}.
 * <li>Le token est généré en utilisant la classe utilitaire
 * {@link TokenAuthenticationService}
 * </ul>
 * 
 * 
 * Lorsqu'un utilisateur essaie d'accéder à une ressource protégée, il passe par
 * le filtre {@link JWTAuthorizationFilter} qui :
 * 
 * <ul>
 * <li>extrait le token de l'entête.
 * <li>Charge le contexte de sécurité de Spring
 * </ul>
 * 
 * 
 * @author eric
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/").permitAll()
				// Autorise les accès sans authent pour le login
				.antMatchers("/api/auth", "/api/register").permitAll()
				// Tous les autres accès doivent être authentifié
				.anyRequest().authenticated().and()
				// Ajout du filtre pour le login
				.addFilterBefore(new JWTAuthenticationFilter("/api/auth", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				// Ajout du filtre pour l'authentification
				.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Create a default account
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}