package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.services.PetService;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
	@Autowired
	PetService petService;

    @PostMapping
    public Pet savePet(@RequestBody PetDTO petDTO) {
		return petService.save(petDTO);
    }

    @GetMapping("/{petId}")
    public Pet getPet(@PathVariable long petId) {
        return petService.findById(petId).get();
    }

    @GetMapping
    public List<Pet> getPets(){
    	return petService.findAll();
    }

    @GetMapping("/owner/{ownerId}")
    public List<Pet> getPetsByOwner(@PathVariable long ownerId) {
        return petService.findByOwnerId(ownerId);
    }
}
