package com.domenec.springboot.backend.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HeroNotFoundException extends RuntimeException {

	public HeroNotFoundException(String message) {
		    super(message);
		  }
	private static final long serialVersionUID = 1L;
}
