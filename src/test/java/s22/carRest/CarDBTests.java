package s22.carRest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import s22.carRest.domain.Car;
import s22.carRest.domain.CarRepository;

@DataJpaTest
public class CarDBTests {
	
	@Autowired 
	CarRepository carRepository; 
	
	
	@Test
	public void findByOwnerLastName() {
		List<Car> cars = carRepository.findByOwnerLastName("Minnie");
		assertThat(cars).hasSize(2);
	}


	@Test
	public void findByModel() {
		List<Car> cars = carRepository.findByModel("Golf");
		assertThat(cars).hasSize(1);
	}

	
	@Test
	public void saveCar() {
		Car car = new Car("Volkswagen", "Gof", "Silver", "MKP-111", 2020, 29000);
		carRepository.save(car);
		assertThat(car.getId()).isNotNull();
	}

}
