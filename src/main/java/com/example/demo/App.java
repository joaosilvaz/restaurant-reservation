package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cache.annotation.EnableCaching;
 
 import io.swagger.v3.oas.annotations.OpenAPIDefinition;
 import io.swagger.v3.oas.annotations.info.Info;



@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "Restaurant Reservation", description = "Bem-vindo ao repositório do Yamato Sushi, um sistema de reservas para restaurantes desenvolvido para otimizar e facilitar a gestão de mesas e clientes.", version = "v1"))


public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
