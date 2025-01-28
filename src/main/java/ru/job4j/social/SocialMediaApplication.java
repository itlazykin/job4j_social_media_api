package ru.job4j.social;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocialMediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaApplication.class, args);
		System.out.println("Go to http://localhost:8080/");
	}
}
