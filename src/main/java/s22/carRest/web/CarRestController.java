package s22.carRest.web;

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
public class CarRestController {

	@Autowired
	CarRepository carRepository;

	// return list of cars
	@CrossOrigin
	@GetMapping("/rest/cars")
	public Iterable<Car> getCars() {
		// fetch and return cars
		return carRepository.findAll();
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
	@DeleteMapping("/rest/cars/{id}")
	void deleteCar(@PathVariable Long id) {
		carRepository.deleteById(id);

	}
	
	
	// findByModel 
	@GetMapping("/restFindCarByModel/{model}")
	Iterable<Car> findCarByModel(@PathVariable("model") String model) {
		System.out.println("restFindCarByModel metodissa");
		return carRepository.findByModel(model);

	}

}
