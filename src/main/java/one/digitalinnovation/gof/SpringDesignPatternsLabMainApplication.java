package one.digitalinnovation.gof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 
 * @author Moises
 */
@EnableFeignClients
@SpringBootApplication
public class SpringDesignPatternsLabMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDesignPatternsLabMainApplication.class, args);
	}

}
