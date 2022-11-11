package s22.carRest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import s22.carRest.domain.Car;
import s22.carRest.domain.CarRepository;
import s22.carRest.domain.Owner;
import s22.carRest.domain.OwnerRepository;

/**
 * This application is for Ohjelmistoprojekti1 demo purposes. .
 * 
 *
 * 
 * @author h01340
 *
 */
@SpringBootApplication
public class CarRestApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(CarRestApplication.class);

	// we need repository interfaces to put demo data to db
	@Autowired
	CarRepository carRepository;
	@Autowired
	OwnerRepository ownerRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(CarRestApplication.class, args);
	}

	/*
	 * @Bean public WebMvcConfigurer corsConfigurer() { return new
	 * WebMvcConfigurer() {
	 * 
	 * @Override public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/rest/cars").allowedOrigins("http://localhost:3000/"); }
	 * }; }
	 */
	@Override
	public void run(String... args) throws Exception {

		
		log.info("Luodaan muutama omistaja");
		Owner owner1 = new Owner("Macy", "Minnie", "Tampere", "150578-223L", 1970);
		ownerRepository.save(owner1);
		ownerRepository.save(new Owner("John", "Johnson", "Helsinki", "111177-134M", 1977));
		ownerRepository.save(new Owner("Kia", "Watson", "Helsinki", "300378-194L", 1978));

		// Add car object and link to owners and save these to db
		log.info("Luodaan autoja");
		carRepository.save(new Car("Volkswagen", "Golf", "White", "Abc-123", 2021, 59000, owner1));
		carRepository.save(new Car("Ford", "Mustang", "Red", "ADF-112", 2021, 59000,
				ownerRepository.findBySsn("111177-134M").get(0)));
		carRepository.save(new Car("Nissan", "Leaf", "White", "SSJ-300", 2019, 29000,
				ownerRepository.findBySsn("111177-134M").get(0)));
		carRepository.save(new Car("Toyota", "Prius", "Silver", "KKO-212", 2020, 39000,
				ownerRepository.findBySsn("300378-194L").get(0)));

		log.info("Tulostetaan IDEn konsoliin autot omistajineen:");
		for (Car car : carRepository.findAll()) {
			log.info(car.toString());
			log.info("Auton omistaja on " + car.getOwner().getFirstName() + " " + car.getOwner().getLastName());
		}

	}


}
