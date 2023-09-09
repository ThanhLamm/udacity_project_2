package com.udacity.jdnd.course3.critter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
	@Query(value= "SELECT * FROM SCHEDULE a join schedule_pet b on a.id = b.schedule_id\r\n"
			+ "where b.pet_id = ?", nativeQuery = true)
	public List<Schedule> findByPetId(Long id);
	
	@Query(value= "SELECT * FROM SCHEDULE a join schedule_employee b on a.id = b.schedule_id\r\n"
			+ "where b.employee_id = ?", nativeQuery = true)
	public List<Schedule> findByEmployeeId(Long id);
}
