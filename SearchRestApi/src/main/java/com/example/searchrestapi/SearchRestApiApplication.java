package com.example.searchrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.searchrestapi.*"})
public class SearchRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchRestApiApplication.class, args);
	}

}
