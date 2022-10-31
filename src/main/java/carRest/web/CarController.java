package carRest.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import carRest.domain.Car;
import carRest.domain.CarRepository;
import carRest.domain.OwnerRepository;

@Controller
public class CarController {
	private static final Logger log = LoggerFactory.getLogger(CarController.class);
	@Autowired
	CarRepository carRepository;
	@Autowired
	OwnerRepository ownerRepository;

	@GetMapping(value = { "/main", "/" })
	public String showMainPage() {
		log.info("lets go to the main page");
		return "mainPage";
	}

	@GetMapping("/carlist")
	public String showCars(Model model) {
		log.info("find all cars");
		model.addAttribute("autot", carRepository.findAll());
		return "cars";
	}

	@GetMapping("/addCar")
	public String addCar(Model model) {
		log.info("lets go to create new car..");
		model.addAttribute("uusiAuto", new Car());
		model.addAttribute("omistajat", ownerRepository.findAll());
		return "newCar";
	}

	@GetMapping("/editCar/{id}")
	public String editCar(@PathVariable("id") Long id, Model model) {
		log.info("lets go to edit selected car..");
		model.addAttribute("editCar", carRepository.findById(id));
		model.addAttribute("omistajat", ownerRepository.findAll());
		return "editCar";
	}

	@PostMapping("saveCar")
	public String saveCar(Car car) {
		log.info("save the car");
		carRepository.save(car);
		return "redirect:carlist";
	}

	@GetMapping("delete/{id}")
	public String deleteCar(@PathVariable("id") Long id, Model model) {
		log.info("delete the selected car");
		carRepository.deleteById(id);
		return "redirect:/carlist";
	}
}
