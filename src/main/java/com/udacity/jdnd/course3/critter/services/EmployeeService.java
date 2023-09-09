package com.udacity.jdnd.course3.critter.services;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository emRe;

	public Employee insert(EmployeeDTO dto) {
		return emRe.save(convert(dto));
	}

	public Optional<Employee> getById(Long id) {
		return emRe.findById(id);
	}

	public void update(Set<DayOfWeek> daysAvailable, Long id) {
		Employee e = emRe.findById(id).get();
		e.setDaysAvailable(daysAvailable);
		emRe.save(e);
	}

	public List<Employee> findEmployeesForService(EmployeeRequestDTO dto) {
		List<Employee> employeeList = emRe.findEmployeeByDaysAvailable(dto.getDate().getDayOfWeek());
		List<Employee> employeeListReal = new ArrayList<>();

		for (Employee employee : employeeList) {
		    int skillCount = 0;
		    for (EmployeeSkill skill : dto.getSkills()) {
		        if (employee.getSkills().contains(skill)) {
		            skillCount++;
		        }
		    }
		    if (skillCount == dto.getSkills().size()) {
		        employeeListReal.add(employee);
		    }
		}

		return employeeListReal;
	}

	Employee convert(EmployeeDTO dto) {
		Employee e = new Employee();
		e.setId(dto.getId());
		e.setName(dto.getName());
		e.setDaysAvailable(dto.getDaysAvailable());
		e.setSkills(dto.getSkills());
		return e;
	}
}
