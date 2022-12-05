package s22.carRest.security;

import java.security.Key;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	private static final Logger log = LoggerFactory.getLogger(JwtService.class);

	//static final long EXPIRATIONTIME = 86400000; // 1 day in ms
	static final long EXPIRATIONTIME = 1516234022; // 5 secs in ms
	static final String PREFIX = "Bearer";
	// Generate secret key. Only for the demonstration
	// You should read it from the application configuration
	static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	// Generate JWT token
	public String getToken(String username) {
		log.info("BACKEND: JwtService: getToken " + username);
		String token = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(key)
				.compact();
		return token;
	}

	// Get a token from request Authorization header,
	// parse a token and get username
	public String getAuthUser(HttpServletRequest request) {
		log.info("BACKEND: JwtService: getAuthUser/req " + request);

		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		log.info("BACKEND: JwtService: getAuthUser/token " + token);
		if (token != null) {
			log.info("BACKEND: JwtService: getAuthUser/token is not null");
			log.info("BACKEND: JwtService: getAuthUser/token ..." + Jwts.parserBuilder().setSigningKey(key).build());
			String user = Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token.replace(PREFIX, ""))
					.getBody()
					.getSubject();
			//
			log.info("BACKEND: JwtService: getAuthUser/user" + user);
			if (user != null)
				return user;
		}
		log.info("BACKEND: JwtService: getAuthUser/return null");
		return null;
	}
}
