package carRest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import carRest.domain.Car;
import carRest.domain.CarRepository;

@RestController
public class CarRestController {

	@Autowired
	CarRepository carRepository;

	// return list of cars
	@GetMapping("/restCars")
	public Iterable<Car> getCars() {
		// fetch and return cars
		return carRepository.findAll();
	}

	// add new car
	@PostMapping("restCars")
	Car newCar(@RequestBody Car newCar) {
		return carRepository.save(newCar);
	}

	// edit existing car information
	@PutMapping("/restCars/{id}")
	Car editCar(@RequestBody Car editedCar, @PathVariable Long id) {
		editedCar.setId(id);
		return carRepository.save(editedCar);

	}

	// delete car
	@DeleteMapping("/restCars/{id}")
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
