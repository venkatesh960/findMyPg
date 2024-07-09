package com.example.findmypg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.findmypg")
public class FindmypgApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindmypgApplication.class, args);
	}

}
