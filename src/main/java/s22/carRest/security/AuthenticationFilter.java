package s22.carRest.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		log.info("BACKEND: AuthenticationFilter: doFilterInternational/url: " + request.getRequestURL());

		// Get token from Authorization header
		String jws = request.getHeader(HttpHeaders.AUTHORIZATION);

		log.info("BACKEND: AuthenticationFilter: doFilterInternational/jws: " + jws);

		if (jws != null) {
			log.info("   BACKEND...: jws not null");
			// Verify token and get user
			String user = jwtService.getAuthUser(request);
			log.info("   BACKEND...: user =  " + user);
			
			// Authenticate
			Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,
					java.util.Collections.emptyList());
			SecurityContextHolder.getContext().setAuthentication(authentication);

		}
		log.info("BACKEND: AuthenticationFilter: doFilterInternational -> doFilter");
		filterChain.doFilter(request, response);
	}
}
