package com.example.collegeServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CollegeServerApplication {

	private static Initializer initializer;

	public CollegeServerApplication(Initializer initializer) {
		this.initializer = initializer;
	}

	public static void main(String[] args) throws Exception {
		new SpringApplication(CollegeServerApplication.class).run(args);
		Initializer.createAdmin();
	}

}
