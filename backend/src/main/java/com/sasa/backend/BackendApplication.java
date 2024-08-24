package com.sasa.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sasa.backend.config.DotenvConfig;

@SpringBootApplication
public class BackendApplication {

	static {
		DotenvConfig.load();
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
