package fr.fms.hotels;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HotelsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
