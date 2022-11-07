package s22.carRest.web;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import s22.carRest.domain.CarRepository;
import s22.carRest.domain.Owner;
import s22.carRest.domain.OwnerRepository;

@Controller
public class OwnerController {
	private static final Logger log = LoggerFactory.getLogger(OwnerController.class);

	@Autowired
	OwnerRepository ownerRepository;
	
	@Autowired
	CarRepository carRepository;

	@GetMapping("ownerList")
	public String getOwners(Model model) {
		model.addAttribute("owners", ownerRepository.findAll());
		return "owners";
	}

	@GetMapping("addOwner")
	public String addOwner(Model model) {
		model.addAttribute("owner", new Owner());
		return "addOwner";
	}

	@PostMapping("saveOwner")
	public String saveOwner(@Valid Owner owner, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("some error happened");
			model.addAttribute("owner", owner);
			return "addOwner";
		}
		ownerRepository.save(owner);
		return "redirect:ownerList";

	}
	
	@GetMapping("getOwnerCars/{id}")
	public String getOwnerCars(@PathVariable("id") Long id, Model model) {
		Optional<Owner> owner = ownerRepository.findById(id);
		log.info("*********OWNER " + owner);
		if (owner.isPresent()) {
			model.addAttribute("ownerCars", carRepository.findByOwnerId(id));
			model.addAttribute("owner", owner.get());
			return "ownerCars";
	
		} else {
			return "redirect:ownerList";
		}
	}
	
	@GetMapping("deleteOwner/{id}")
	public String deleteCar(@PathVariable("id") Long id, Model model) {
		log.info("delete the owner");
		ownerRepository.deleteById(id);
		return "redirect:/ownerList";
	}	
}
