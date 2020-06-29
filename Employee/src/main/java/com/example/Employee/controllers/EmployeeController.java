package com.example.Employee.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Employee.model.Employee;
import com.example.Employee.repositories.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository repo;
	
	@GetMapping("employees")
	public List<Employee> getAllEmployees(){
		return repo.findAll();
		
	}
	
	@GetMapping("employee/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
		return repo.findById(employeeId);
		
	}
	
	
	@PostMapping("employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		 return repo.save(employee);
	}
	
	
	@PutMapping("employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId, @RequestBody Employee emp) {
		
		Employee employee = repo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setEmailId(emp.getEmailId());
		employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		final Employee updatedEmployee = repo.save(employee);
        return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("/employee/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
         throws ResourceNotFoundException {
        Employee employee =  repo.findById(employeeId)
       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        repo.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
