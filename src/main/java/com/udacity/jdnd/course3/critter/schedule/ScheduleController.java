package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.services.PetService;
import com.udacity.jdnd.course3.critter.services.ScheduleService;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	PetService petService;
	
    @PostMapping
    public Schedule createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return scheduleService.create(scheduleDTO);
    }

    @GetMapping
    public List<Schedule> getAllSchedules() {
        return scheduleService.findAll();
    }

    @GetMapping("/pet/{petId}")
    public List<Schedule> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.findByPetId(petId);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Schedule> getScheduleForEmployee(@PathVariable long employeeId) {
    	return scheduleService.findByEmployeeId(employeeId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Schedule> getScheduleForCustomer(@PathVariable long customerId) {
    	List<Pet> petList = petService.findByOwnerId(customerId);
    	List<Schedule> scheduleList = new ArrayList<>();
    	for (Pet pet : petList) {
            scheduleList.addAll(scheduleService.findByPetId(pet.getId()));
        }
    	return scheduleList;
    }
}
