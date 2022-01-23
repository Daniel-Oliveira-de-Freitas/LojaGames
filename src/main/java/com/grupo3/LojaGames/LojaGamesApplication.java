package com.grupo3.LojaGames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.grupo3.controller","com.grupo3.service"})
@EntityScan("com.grupo3.Model")
@EnableJpaRepositories("com.grupo3.dao")
public class LojaGamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaGamesApplication.class, args);
	}

}
