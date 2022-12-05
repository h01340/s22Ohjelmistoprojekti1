package s22.carRest.security;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig {
	private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private AuthenticationFilter authenticationFilter;
	
	//luodaan security filter chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	log.info("BACKEND: SecurityConfig: SecurityFilterChain: filterChain");
    	
    	http
//    	.cors().and()
    	.csrf().disable().cors()
//    	.csrf().ignoringAntMatchers("*")
    	.and()
    	.sessionManagement()
    	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    	.and()
		.authorizeRequests()
    	.antMatchers(HttpMethod.POST, "/rest/login").permitAll() 
    	//.antMatchers(HttpMethod.DELETE, "/rest/*").permitAll() 
    	.anyRequest().authenticated()
    	.and()
    	.addFilterBefore(authenticationFilter, 
				UsernamePasswordAuthenticationFilter.class)
     	.httpBasic();
     	
    	return http.build();
    }
 
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() 
//    {
//      CorsConfiguration configuration = new CorsConfiguration();
//      configuration.setAllowedOrigins(Arrays.asList("*"));
//      configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//      source.registerCorsConfiguration("/**", configuration);
//      return source;
//    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOriginPatterns(Arrays.asList("*"));
        
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("*"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception  {
		log.info("BACKEND: SecurityConfig: configureGlobal");
		auth.userDetailsService(userDetailsServiceImpl)
		.passwordEncoder(new BCryptPasswordEncoder());
	}


    /**
     * UsernamePasswordAuthenticationToken gets {username, password} from login Request, 
     * AuthenticationManager will use it to authenticate a login account
     */
    @Bean
    public AuthenticationManager authenticationManager(
          AuthenticationConfiguration authenticationConfiguration) throws Exception {
      log.info("BACKEND: SecurityConfig: authenticationManager: return auth mgr " + authenticationConfiguration.getAuthenticationManager());
    	return authenticationConfiguration.getAuthenticationManager();
    }    

    @Bean
    PasswordEncoder passwordEncoder() {
    	log.info("BACKEND: SecurityConfig: passwordEncoder ->return BCrypt encoder");
      return new BCryptPasswordEncoder();
    }

 }
