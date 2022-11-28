package com.domenec.springboot.backend.api.dto;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class HeroRequest {
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String poder ;
		
}
