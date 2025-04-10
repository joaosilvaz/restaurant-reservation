package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cache.annotation.EnableCaching;
 
 import io.swagger.v3.oas.annotations.OpenAPIDefinition;
 import io.swagger.v3.oas.annotations.info.Info;



@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "Restaurant Reservation", description = "Aqui vai a descrição", version = "v1"))


public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
