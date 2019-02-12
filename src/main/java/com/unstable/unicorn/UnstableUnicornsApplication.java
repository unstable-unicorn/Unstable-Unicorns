package com.unstable.unicorn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.unstable"})
public class UnstableUnicornsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnstableUnicornsApplication.class, args);
	}

}

