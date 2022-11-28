package com.domenec.springboot.backend.api.controller.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.domenec.springboot.backend.api.dto.HeroRequest;
import com.domenec.springboot.backend.api.entity.Hero;
import com.domenec.springboot.backend.api.services.HeroService;


@RestController
@RequestMapping("/api/heroes")
public class HeroRestController {
	
	@Autowired
	private HeroService heroService;
	
	 @PostMapping
	  public ResponseEntity<Void> createNewHero(@Validated @RequestBody HeroRequest heroRequest, UriComponentsBuilder uriComponentsBuilder) {
		 Long primaryKey = heroService.createNewHero(heroRequest);
	    		
	    UriComponents uriComponents = uriComponentsBuilder.path("/api/heroes/{id}").buildAndExpand(primaryKey);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(uriComponents.toUri());

	    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	  }
	 
	 @GetMapping
	  public ResponseEntity<List<Hero>> getAllHeroes() {
	    return ResponseEntity.ok(heroService.getAllHeroes());
	  }
	 
	 @GetMapping("/{id}")
	  public ResponseEntity<Hero> getHeroById(@PathVariable("id") Long id) {
	    return ResponseEntity.ok(heroService.getHeroById(id));
	  }
	 
	 @PutMapping("/{id}")
	  public ResponseEntity<Hero> updateHero(@PathVariable("id") Long id, @Valid @RequestBody HeroRequest heroRequest) {
	    return ResponseEntity.ok(heroService.updateHero(id, heroRequest));
	  }
	 
	 @DeleteMapping("/{id}")
	  public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
	    heroService.deleteHeroById(id);
	    return ResponseEntity.ok().build();
	  }
	 
	 @GetMapping("busqueda/{nombre}")
	  public ResponseEntity<List<Hero>> getNameLike(@PathVariable("nombre") String nombre) {
	    return ResponseEntity.ok(heroService.getNameLike(nombre));
	  }
	 
	
}
