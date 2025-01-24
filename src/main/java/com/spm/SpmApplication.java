package com.spm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpmApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpmApplication.class, args);
		System.out.println("Hello World!!");
	}

}
