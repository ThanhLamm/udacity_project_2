package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import com.udacity.jdnd.course3.critter.services.PetService;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	PetService petService;

    @PostMapping("/customer")
    public Customer saveCustomer(@RequestBody CustomerDTO customerDTO){
		return customerService.save(customerDTO);
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomers(){
    	return customerService.findAll();
    }

    @GetMapping("/customer/pet/{petId}")
    public Customer getOwnerByPet(@PathVariable long petId){
        Pet pet = petService.findById(petId).get();
        return customerService.getById(pet.getCustomer().getId()).get();
    }

    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.insert(employeeDTO);
    }

    @PostMapping("/employee/{employeeId}")
    public Employee getEmployee(@PathVariable long employeeId) {
        return employeeService.getById(employeeId).get();
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.update(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<Employee> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        return employeeService.findEmployeesForService(employeeDTO);
    }

}
