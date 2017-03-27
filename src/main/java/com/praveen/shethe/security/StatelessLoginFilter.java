package com.praveen.shethe.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.praveen.shethe.model.Upayogakarta;
import com.praveen.shethe.repository.UpayogakartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	@Autowired
	private UpayogakartaRepository upayogakartaRepository;

	@Autowired
	private UpayogakartaDetailsService upayogakartaDetailsService;

	public StatelessLoginFilter(
			String defaultFilterProcessesUrl,
			TokenAuthenticationService tokenAuthenticationService,
			UpayogakartaDetailsService upayogakartaDetailsService,
			AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
		this.upayogakartaDetailsService = upayogakartaDetailsService;
		this.tokenAuthenticationService = tokenAuthenticationService;
		setAuthenticationManager(authenticationManager);
	}

	public StatelessLoginFilter(String urlMapping,
								TokenAuthenticationService tokenAuthenticationService,
								AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(urlMapping));
		this.tokenAuthenticationService = tokenAuthenticationService;
		setAuthenticationManager(authManager);
	}
	protected StatelessLoginFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
	}
	@Override
	public Authentication attemptAuthentication(
			HttpServletRequest request,
			HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		final Upayogakarta user = new ObjectMapper().readValue(request.getInputStream(), Upayogakarta.class);
		final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				user.getUsername(), user.getPassword());

		return getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
	}

//	@SuppressWarnings("serial")
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
											Authentication authentication) throws IOException, ServletException {
		final Upayogakarta user = upayogakartaDetailsService.getUpayogakarta((User) upayogakartaDetailsService.loadUserByUsername(authentication.getName()));
				final UpayogakartaAuthentication userAuthentication = new UpayogakartaAuthentication(user);
		tokenAuthenticationService.addAuthenticationTokenInHeader(response, userAuthentication);
		SecurityContextHolder.getContext().setAuthentication(userAuthentication);
	}

	protected StatelessLoginFilter(RequestMatcher requiresAuthenticationRequestMatcher, TokenAuthenticationService tokenAuthenticationService) {
		super(requiresAuthenticationRequestMatcher);
		this.tokenAuthenticationService = tokenAuthenticationService;
	}
}