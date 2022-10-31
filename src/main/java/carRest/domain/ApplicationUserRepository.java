package carRest.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {
	Optional<ApplicationUser> findByUsername(String username);
}