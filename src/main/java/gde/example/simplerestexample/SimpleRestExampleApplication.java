package gde.example.simplerestexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleRestExampleApplication {
	@Bean
	protected UserDAO userDao() {
		return new UserDAO();
	}
	public static void main(String[] args) {
		SpringApplication.run(SimpleRestExampleApplication.class, args);
	}
}
