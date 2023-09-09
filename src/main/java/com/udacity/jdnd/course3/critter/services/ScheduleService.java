package com.udacity.jdnd.course3.critter.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;

@Service
@Transactional
public class ScheduleService {
	@Autowired
	ScheduleRepository scheRepo;
	
	public Schedule create(ScheduleDTO dto) {
		return scheRepo.save(convert(dto));
	}
	
	public List<Schedule> findAll() {
		return scheRepo.findAll();
	}
	
	public List<Schedule> findByPetId(Long id) {
		return scheRepo.findByPetId(id);
	}
	
	public List<Schedule> findByEmployeeId(Long id) {
		return scheRepo.findByEmployeeId(id);
	}
	
	
	public Schedule convert(ScheduleDTO dto) {
		Schedule c = new Schedule();
		c.setId(dto.getId());
		c.setDate(dto.getDate());
		c.setSetEmployeeSkill(dto.getActivities());
		
		List<Employee> listEmployee = new ArrayList<>();
		for(Long id : dto.getEmployeeIds()) {
			listEmployee.add(new Employee(id,null,null, null));
		}
		c.setEmployees(listEmployee);
		
		List<Pet> listPet = new ArrayList<>();
		for(Long id : dto.getPetIds()) {
			listPet.add(new Pet(id, null, null, null, null, null));
		}
		c.setPetList(listPet);
		
		return c;
	}
}
