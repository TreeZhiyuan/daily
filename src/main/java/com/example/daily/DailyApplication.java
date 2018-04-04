package com.example.daily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class DailyApplication {

	@RequestMapping("/")
	String home() {
		return "Hello World, this is a Spring boot web app!";
	}

	public static void main(String[] args) {
		SpringApplication.run(DailyApplication.class, args);
	}
}
