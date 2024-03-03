package com.micro.examenes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.micro.common.entities"})
public class ExamenesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamenesApplication.class, args);
	}

}
