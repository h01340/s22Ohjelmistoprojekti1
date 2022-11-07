package s22.carRest.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Owner {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(min = 1, max = 30)
	private String firstName, lastName;
	private String city;
	private String ssn;

	@Min(value = 1900, message = "min value is 1900")
	@Max(value = 2024, message = "max value is 2024")
	private int yob;

	@JsonIgnore // this prevents the infinte loop in our cars REST service
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	private List<Car> cars;

	public Owner() {
		super();
	}

	public Owner(String firstName, String lastName, String city, String ssn, int yob) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.ssn = ssn;
		this.yob = yob;
	}

	public Owner(String firstName, String lastName, String city, String ssn, int yob, List<Car> cars) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.ssn = ssn;
		this.yob = yob;
		this.cars = cars;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public int getYob() {
		return yob;
	}

	public void setYob(int yob) {
		this.yob = yob;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

//	@Override
//	public String toString() {
//		return "Owner [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", city=" + city + ", ssn="
//				+ ssn + ", yob=" + yob + ", cars=" + cars + "]";
//	}

	@Override
	public String toString() {
		return "Owner [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", city=" + city + ", ssn="
				+ ssn + ", yob=" + yob + "]";
	}

}
