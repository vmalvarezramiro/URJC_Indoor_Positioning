package com.indoorpositioning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableJpaRepositories
public class IndoorPositioning2Application extends SpringBootServletInitializer{

	@Override
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	       return builder.sources(IndoorPositioning2Application.class);
	   }
	public static void main(String[] args) {
		SpringApplication.run(IndoorPositioning2Application.class, args);
	}

}