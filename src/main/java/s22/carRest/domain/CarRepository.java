package s22.carRest.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
	
	List<Car> findByModel(String model);
	List<Car> findByOwnerId(Long id);
	List<Car> findByOwnerLastName(String lastname);

}
