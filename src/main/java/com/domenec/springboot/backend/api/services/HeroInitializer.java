package com.domenec.springboot.backend.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.domenec.springboot.backend.api.entity.Hero;
import com.domenec.springboot.backend.api.repository.HeroRepository;
import com.github.javafaker.Faker;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HeroInitializer implements CommandLineRunner {

	@Autowired
	private HeroRepository heroeDao;

	@Override
	public void run(String... args) throws Exception {

		log.info("Starting to initializa sample data ...");

		Faker faker = new Faker();

		for (int i = 0; i < 10; i++) {

			Hero heroe = new Hero();
			heroe.setNombre(faker.superhero().name());
			heroe.setPoder(faker.superhero().power());
			
			heroeDao.save(heroe);
		}

		log.info("Finished with data initialization");
	}

}
