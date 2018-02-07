package com.praveen.shethe.security;

/**
 * Created by Praveenkumar on 5/1/2018.
 * */

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Simple base implementation of {@link javax.servlet.Filter} which treats
 * its config parameters ({@code init-param} entries within the
 * {@code filter} tag in {@code web.xml}) as bean properties.
 *
 * <p>A handy superclass for any type of filter. Type conversion of config
 * parameters is automatic, with the corresponding setter method getting
 * invoked with the converted value. It is also possible for subclasses to
 * specify required properties. Parameters without matching bean property
 * setter will simply be ignored.
 *
 * <p>This filter leaves actual filtering to subclasses, which have to
 * implement the {@link javax.servlet.Filter#doFilter} method.
 *
 * <p>This generic filter base class has no dependency on the Spring
 * {@link org.springframework.context.ApplicationContext} concept.
 * Filters usually don't load their own context but rather access service
 * beans from the Spring root application context, accessible via the
 * filter's {@link #getServletContext() ServletContext} (see
 * {@link org.springframework.web.context.support.WebApplicationContextUtils}).
 *
 * @author Juergen Hoeller
 * @since 06.12.2003
 * @see #addRequiredProperty
 * @see #initFilterBean
 * @see #doFilter
 */

public class StatelessAuthenticationFilter extends GenericFilterBean {

	private final TokenAuthenticationService tokenAuthenticationService;

	public StatelessAuthenticationFilter(TokenAuthenticationService taService) {
		this.tokenAuthenticationService = taService;
	}

	/**
	 * The <code>doFilter</code> method of the Filter is called by the
	 * container each time a request/response pair is passed through the
	 * chain due to a client request for a resources at the end of the chain.
	 * The FilterChain passed in to this method allows the Filter to pass
	 * on the request and response to the next entity in the chain.
	 *
	 * <p>A typical implementation of this method would follow the following
	 * pattern:
	 * <ol>
	 * <li>Examine the request
	 * <li>Optionally wrap the request object with a custom implementation to
	 * filter content or headers for input filtering
	 * <li>Optionally wrap the response object with a custom implementation to
	 * filter content or headers for output filtering
	 * <li>
	 * <ul>
	 * <li><strong>Either</strong> invoke the next entity in the chain
	 * using the FilterChain object
	 * (<code>chain.doFilter()</code>),
	 * <li><strong>or</strong> not pass on the request/response pair to
	 * the next entity in the filter chain to
	 * block the request processing
	 * </ul>
	 * <li>Directly set headers on the response after invocation of the
	 * next entity in the filter chain.
	 * </ol>
	 */

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		SecurityContextHolder.getContext()
				.setAuthentication(tokenAuthenticationService.getAuthenticationFromHeader((HttpServletRequest) request));
		chain.doFilter(request, response);
	}
}