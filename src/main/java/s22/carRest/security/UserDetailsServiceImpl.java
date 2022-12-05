package s22.carRest.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import s22.carRest.domain.ApplicationUser;
import s22.carRest.domain.ApplicationUserRepository;

/**
 * This class is used by spring security to authenticate and authorize user
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private ApplicationUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info("BACKEND: UserDetails loadUserByUsername " + username);
		ApplicationUser user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		return new User(user.getUsername(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole()));
	}

}
