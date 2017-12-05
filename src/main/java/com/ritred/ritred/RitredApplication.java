package com.ritred.ritred;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.ritred.ritred")
public class RitredApplication {

	public static void main(String[] args) {
		SpringApplication.run(RitredApplication.class, args);
	}
}
