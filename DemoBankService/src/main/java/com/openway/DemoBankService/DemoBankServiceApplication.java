package com.openway.DemoBankService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoBankServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBankServiceApplication.class, args);
	}

}

