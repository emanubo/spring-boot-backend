package com.domenec.springboot.backend.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootBackendApiApplication {

	public static void main(String[] args) throws InterruptedException {
		long inicio = System.currentTimeMillis();
		Thread.sleep(2000);
		long fin = System.currentTimeMillis();
		double tiempo = (double) ((fin - inicio) / 1000);
		SpringApplication.run(SpringBootBackendApiApplication.class, args);
		System.out.println(tiempo + " segundos");
	}
}
