package com.epages.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterviewApplication {

	public static void main(String[] args) {
		// SpringApplication.run(InterviewApplication.class, args);
		SpringApplication app = new SpringApplication(InterviewApplication.class);
		app.setAdditionalProfiles("testData");
		app.run(args);
	}
}
