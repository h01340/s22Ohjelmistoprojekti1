package s22.carRest.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import s22.carRest.domain.Car;
import s22.carRest.domain.CarRepository;

@RestController
@CrossOrigin(origins = "https://localhost:3000/", maxAge = 3600)
public class CarRestController {
	private static final Logger log = LoggerFactory.getLogger(CarRestController.class);
	

	@Autowired
	CarRepository carRepository;

	// return list of cars
	@GetMapping("/rest/cars")
	@CrossOrigin	
	public Iterable<Car> getCars() {
		log.info("BACKEND REST: /rest/cars - getCars()");
		// fetch and return cars
		return carRepository.findAll();
	}
	
	// return specific cars by specific model
	@GetMapping("/findByModel/{malli}")
	public Iterable<Car> findByModel(@PathVariable (name="malli") String malli) {
		log.info("/findByModel/{model} " + malli);
		log.info("TARKISTA TÄMÄ " + carRepository.findByModel(malli));
		//carRepository.findByModel("Golf")
		return carRepository.findByModel("malli");
	}	

	// add new car
	@PostMapping("rest/cars")
	Car newCar(@RequestBody Car newCar) {
		return carRepository.save(newCar);
	}

	// edit existing car information
	@PutMapping("/rest/cars/{id}")
	Car editCar(@RequestBody Car editedCar, @PathVariable Long id) {
		editedCar.setId(id);
		return carRepository.save(editedCar);

	}

	// delete car
	@CrossOrigin
	@DeleteMapping("/rest/cars/{id}")
	void deleteCar(@PathVariable Long id) {
		log.info("REST -> DELETE");
		carRepository.deleteById(id);
	}
	
}
