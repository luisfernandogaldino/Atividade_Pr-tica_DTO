package com.exemploDTO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemploDTO.entities.Pet;

public interface PetRepository  extends JpaRepository<Pet, Long> {

}
