package com.udacity.jdnd.course3.critter.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;

@Service
@Transactional
public class CustomerService {
	@Autowired
	CustomerRepository cusRepo;

	public Customer save(CustomerDTO dto) {
		Customer cus = new Customer(dto.getId(), dto.getName(), dto.getPhoneNumber(), dto.getNotes(), new ArrayList<>());
		
		return cusRepo.save(cus);
	}
	
	public Optional<Customer> getById(Long id) {
		return cusRepo.findById(id);
	}

	public List<Customer> findAll() {
		return cusRepo.findAll();
	}

}
