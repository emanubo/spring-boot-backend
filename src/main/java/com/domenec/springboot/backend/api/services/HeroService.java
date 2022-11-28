package com.domenec.springboot.backend.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domenec.springboot.backend.api.controller.HeroNotFoundException;
import com.domenec.springboot.backend.api.dto.HeroRequest;
import com.domenec.springboot.backend.api.entity.Hero;
import com.domenec.springboot.backend.api.repository.HeroRepository;

@Service
public class HeroService {

	/*
	 * public Long createNewHero(HeroRequest heroRequest) {
	 * 
	 * return null; }
	 */

	@Autowired
	private HeroRepository heroRepository;

	public Long createNewHero(HeroRequest heroRequest) {
		Hero heroe = new Hero();
		heroe.setNombre(heroRequest.getNombre());
		heroe.setPoder(heroRequest.getPoder());

		heroe = heroRepository.save(heroe);

		return heroe.getId();
	}

	public List<Hero> getAllHeroes() {
		return heroRepository.findAll();
	}

	public Hero getHeroById(Long id) {
		Optional<Hero> requestedHero = heroRepository.findById(id);

		if (requestedHero.isEmpty()) {
			throw new HeroNotFoundException(String.format("Hero with id: '%s' not found", id));
		}

		return requestedHero.get();
	}

	@Transactional
	public Hero updateHero(Long id, HeroRequest heroToUpdateRequest) {

		Optional<Hero> heroFromDatabase = heroRepository.findById(id);

		if (heroFromDatabase.isEmpty()) {
			throw new HeroNotFoundException(String.format("Hero with id: '%s' not found", id));
		}

		Hero heroToUpdate = heroFromDatabase.get();

		heroToUpdate.setNombre(heroToUpdateRequest.getNombre());
		heroToUpdate.setPoder(heroToUpdateRequest.getPoder());

		return heroToUpdate;
	}
	
	
	public void deleteHeroById(Long id) {
	    heroRepository.deleteById(id);
	  }
	
	
	public List<Hero> getNameLike(String nombre) {
		return heroRepository.findByNombreContaining(nombre);
	}
	
	
}
