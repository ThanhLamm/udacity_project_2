package com.udacity.jdnd.course3.critter.entity;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schedule")
public class Schedule implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    @ManyToMany
    @JoinTable(
            name = "schedule_employee",
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;

    @ManyToMany
    @JoinTable(
            name = "schedule_pet",
            inverseJoinColumns = @JoinColumn(name = "pet_id"))
    private List<Pet> petList;

    @ElementCollection
    @JoinTable(name = "schedule_skill")
    private Set<EmployeeSkill> setEmployeeSkill;
    
    public List<Long> getPetIds(List<Pet> listPet) {
    	List<Long> listPetIds = new ArrayList<>();
    	for(Pet p : listPet) {
    		listPetIds.add(p.getId());
    	}
    	
    	return listPetIds;
    }
    
    public List<Long> getEmployeeIds(List<Employee> listEmployee) {
    	List<Long> listEmployeeIds = new ArrayList<>();
    	for(Employee e : listEmployee) {
    		listEmployeeIds.add(e.getId());
    	}
    	
    	return listEmployeeIds;
    }
}
