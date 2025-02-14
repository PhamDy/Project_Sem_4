package com.projectsem4.StadiumService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StadiumServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(StadiumServiceApplication.class, args);
	}
}
