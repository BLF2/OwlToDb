package net.blf2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OwlToDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(OwlToDbApplication.class, args);
		ModelOp.insertData();
	}
}
