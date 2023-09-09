package com.udacity.jdnd.course3.critter.repository;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	@Query(value = "SELECT * FROM employee e join employee_days_available d on e.id = d.employee_id where d.days_available in ? GROUP by e.id", nativeQuery = true)
	public List<Employee> findEmployeeByDaysAvailable(DayOfWeek day);
}
