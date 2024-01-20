package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.sql.Driver;

@SpringBootApplication
public class BackendApplication  {
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}
