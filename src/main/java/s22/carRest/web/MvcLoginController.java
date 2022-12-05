package s22.carRest.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller	
public class MvcLoginController {
	private static final Logger log = LoggerFactory.getLogger(MvcLoginController.class);
	
    @GetMapping(value="/mlogin")
    public String login() {	
    	log.info("MvcLoginController/login got to mvcLogin page");
        return "login";
    }	
	
}
