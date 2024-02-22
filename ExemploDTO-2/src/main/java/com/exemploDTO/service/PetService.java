package com.exemploDTO.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemploDTO.dto.PetDTO;
import com.exemploDTO.entities.Pet;
import com.exemploDTO.repository.PetRepository;

@Service
public class PetService {
	private final PetRepository petRepository;

	@Autowired
	public PetService(PetRepository petRepository) {
		this.petRepository = petRepository;
	}

	//Método modificado para utilizar o dto
	public PetDTO salvar(PetDTO petDTO) {

		//Convert DTO to entity
		Pet pet = new Pet(petDTO.id(),petDTO.nome(),petDTO.nascimento(), petDTO.cuidador());
		Pet salvarPet = petRepository.save(pet);
		return new PetDTO(salvarPet.getId(), salvarPet.getNome(),salvarPet.getNascimento(), salvarPet.getCuidador());
	}
	//Método modificado para utilizar o DTO
	public PetDTO atualizar(Long id,PetDTO petDTO){
		Pet existePet = petRepository.findById(id).orElseThrow(()-> new RuntimeException("Pet não encontrado"));	

		existePet.setNome(petDTO.nome());
		existePet.setNascimento(petDTO.nascimento());
		existePet.setCuidador(petDTO.cuidador());

		Pet updatePet = petRepository.save(existePet);
		return new PetDTO(updatePet.getId(), updatePet.getNome(), updatePet.getNascimento(), updatePet.getCuidador());
	}
	public boolean deletar(Long id) {
		Optional <Pet> existePet = petRepository.findById(id);
		if(existePet.isPresent()) {
			petRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public List<Pet> buscarTodos(){
		return petRepository.findAll();
	}

	public Pet buscarPorId(Long id) {
		Optional <Pet> pet = petRepository.findById(id);
		return pet.orElse(null);
	}

}

