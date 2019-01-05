package com.panjura.mosque.miyabarimosque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MiyabarimosqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiyabarimosqueApplication.class, args);
	}

}
