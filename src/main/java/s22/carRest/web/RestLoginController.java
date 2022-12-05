package s22.carRest.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import s22.carRest.domain.AccountCredentials;
import s22.carRest.security.JwtService;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
public class RestLoginController {
	private static final Logger log = LoggerFactory.getLogger(RestLoginController.class);

	@Autowired      
	private AuthenticationManager authenticationManager;
	
	@Autowired 
	private JwtService jwtService;
	
	@PostMapping(value="rest/login")
	public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials) {

		log.info("BACKEND/RestLoginController:restLogin " + credentials);
		UsernamePasswordAuthenticationToken creds = 
				new UsernamePasswordAuthenticationToken(
						credentials.getUsername(),
						credentials.getPassword());	

		log.info("BACKEND/RestLoginController:restLogin -> creds " + creds); 
		Authentication auth = authenticationManager.authenticate(creds);
		log.info("BACKEND/RestLoginController:restLogin after authentication auth " + auth); 
	
		log.info("BACKEND/RestLoginController:restLogin go to generate token "); 
		// Generate token
		String jwts = jwtService.getToken(auth.getName());
		log.info("BACKEND/RestLoginController:restLogin/jwts " + jwts);


		// Build response with the generated token
		return ResponseEntity.ok()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
				.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
				.build();
	  }
}
