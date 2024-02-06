package com.spring.project.crudapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudApplication {
	public static final String USER_NAME = "Malik";
	
	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

}
