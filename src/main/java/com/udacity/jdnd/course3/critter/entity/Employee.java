package com.udacity.jdnd.course3.critter.entity;


import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ElementCollection
    @JoinTable(name = "employee_skill")
    private Set<EmployeeSkill> skills;

    @ElementCollection
    @JoinTable(name = "employee_days_available")
    private Set<DayOfWeek> daysAvailable;
    
}
