package org.cogent.infotech.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cogent.infotech.exception.ResourceNotFoundException;
import org.cogent.infotech.model.Employee;
import org.cogent.infotech.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
//find all employee
	@GetMapping("/employees")
	public List<Employee>getallEmployees(){
		return employeeRepository.findAll();
		
	}
	//create employee rest api
	@PostMapping("/employees")
	public Employee creatEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
		
	}
	//get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee>getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Not found : " + id));
		return ResponseEntity.ok(employee);
		
		
	}
	// update employee rest api
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee>updateEmployee(@PathVariable Long id,@RequestBody Employee emp){
		
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Not found : " + id));
		employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		employee.setEmailId(emp.getEmailId());
		Employee updateEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updateEmployee);
		
	}
	//Delete employee rest api
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>>deleteEmployee(@PathVariable Long id){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Not found : " + id));
		employeeRepository.delete(employee);
		Map<String, Boolean> responseMap = new HashMap<>();
		responseMap.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(responseMap);
		
	}

	

}
