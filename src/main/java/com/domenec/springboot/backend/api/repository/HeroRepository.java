package com.domenec.springboot.backend.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.domenec.springboot.backend.api.entity.Hero;


public interface HeroRepository extends JpaRepository<Hero, Long> {
	
	@Query("SELECT m FROM Hero m WHERE m.nombre LIKE %:nombre%")
	List<Hero> findByNombreContaining(@Param("nombre") String nombre);

}