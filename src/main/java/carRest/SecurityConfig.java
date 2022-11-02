package carRest;



import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {
	
	/*
	 * @Bean public SecurityFilterChain configure(HttpSecurity http) throws
	 * Exception {
	 * 
	 * return http.authorizeRequests(auth -> { // css folder is accessible for all
	 * auth.antMatchers("/css/**").permitAll();
	 * auth.antMatchers("/h2-console/**").permitAll();
	 * auth.antMatchers("/h2-console").permitAll(); // hasRole is not working with
	 * H2 // auth.antMatchers("/owners/**").hasRole("ADMIN"); // if user's role is
	 * ADMIN s/he has access to all pages "under" owners
	 * auth.antMatchers("/owners/**").hasAuthority("ADMIN"); // every http request
	 * will be authenticated auth.anyRequest().authenticated();
	 * }).csrf().ignoringAntMatchers("h2-console").and() // tells where to go after
	 * successful login .formLogin().defaultSuccessUrl("/main", true).and() //
	 * Logout is permitted for all users .logout().permitAll().and()
	 * .httpBasic(Customizer.withDefaults()).build(); }
	 */
}
