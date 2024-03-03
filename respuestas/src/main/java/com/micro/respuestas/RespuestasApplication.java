package com.micro.respuestas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class} )
@EnableFeignClients
@SpringBootApplication
public class RespuestasApplication {

	public static void main(String[] args) {
		SpringApplication.run(RespuestasApplication.class, args);
	}

}
