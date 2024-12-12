package com.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(LibraryApplication.class, args);

		Environment environment = context.getEnvironment();
		String appEnv = environment.getProperty("APP_ENV");
		System.out.println("App Environment: " + appEnv);
	}

}
