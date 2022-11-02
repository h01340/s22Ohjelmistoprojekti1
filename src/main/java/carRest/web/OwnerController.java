package carRest.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import carRest.domain.Owner;
import carRest.domain.OwnerRepository;

@Controller
public class OwnerController {

	@Autowired
	OwnerRepository ownerRepository;

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

}
