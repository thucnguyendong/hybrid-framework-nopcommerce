package utilities;

import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

public class DataHelper {
	
	private Locale locale = new Locale("en");
	private Faker faker = new Faker(locale);
	
	public static DataHelper getData() {
		return new DataHelper();
	}
	public int getRandomNumber(int minimum, int maximum) {
		Random rand = new Random();
		return minimum + rand.nextInt(maximum - minimum);
	}
	
	public String getFirstName() {
		return faker.address().firstName();
	}
	
	public String getLastName() {
		return faker.address().lastName();
	}
	
	public String getFullname() {
		return getFirstName() + " " + getLastName();
	}
	
	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}
	
	public String getAddress() {
		return faker.address().fullAddress();
	}
	
	public String getUsername() {
		return faker.name().username();
	}
	
	public String getPassword() {
		return faker.internet().password();
	}
	
	public String getNationality() {
		return faker.nation().capitalCity();
	}
	
	public String getCardNumber() {
		return faker.finance().creditCard();
	}
}
