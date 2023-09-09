package com.udacity.jdnd.course3.critter.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.PetRepository;

@Service
@Transactional
public class PetService {
	@Autowired
	PetRepository petRepository;

	public Pet save(PetDTO dto) {
		Customer cus = new Customer();
		cus.setId(dto.getOwnerId());
		Pet pet = convert(dto);
		pet.setCustomer(cus);
		return petRepository.save(pet);
	}
	
	public Optional<Pet> findById(Long id) {
		return petRepository.findById(id);
	}
	
	Pet convert(PetDTO dto) {
		return new Pet(dto.getId(), dto.getName(), dto.getType(), dto.getBirthDate(), dto.getNotes(), null);
	}

	public List<Pet> findAll() {
		return petRepository.findAll();
	}

	public List<Pet> findByOwnerId(Long id) {
		return petRepository.findByCustomerId(id);
	}
}
