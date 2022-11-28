package com.domenec.springboot.backend.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.domenec.springboot.backend.api.controller.impl.HeroRestController;
import com.domenec.springboot.backend.api.dto.HeroRequest;
import com.domenec.springboot.backend.api.services.HeroService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HeroRestController.class)

public class HeroControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private HeroService heroService;

	@Captor
	private ArgumentCaptor<HeroRequest> argumentCaptor;

	@Captor
	private ArgumentCaptor<HeroRequest> heroRequestArgumentCaptor;

	@Test
	public void postingANewHeroShouldCreateANewHeroInTheDatabase() throws Exception {

		HeroRequest heroRequest = new HeroRequest();
		heroRequest.setNombre("Manowar");
		heroRequest.setPoder("volar");

		when(heroService.createNewHero(heroRequestArgumentCaptor.capture())).thenReturn(1L);

		this.mockMvc
				.perform(post("/api/heroes").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(heroRequest)))
				.andExpect(status().isCreated()).andExpect(header().exists("Location"))
				.andExpect(header().string("Location", "http://localhost/api/heroes/1"));

		assertThat(heroRequestArgumentCaptor.getValue().getNombre(), is("Manowar"));
		assertThat(heroRequestArgumentCaptor.getValue().getPoder(), is("volar"));
		
	}

}
