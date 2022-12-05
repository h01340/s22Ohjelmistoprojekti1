package s22.carRest.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {

	Optional <ApplicationUser> findByUsername(String username);
	
	//do we need this
	Boolean existsByUsername(String username);
}
