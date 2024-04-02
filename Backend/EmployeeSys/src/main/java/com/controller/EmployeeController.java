package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Employee;
import com.service.EmployeeService;


@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	
	@Autowired 
	EmployeeService employeeService;
	
	
	
	@GetMapping
	public List<Employee> getAllEmployee()
	{
		return employeeService.getAllEmployees();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id)
	{
		Optional<Employee> employee = employeeService.getEmployeeById(id);
		
		return employee.map(ResponseEntity :: ok ).orElseGet(() -> ResponseEntity.notFound().build() ); 
	}
	
	
	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee)
	{
		return employeeService.createEmployee(employee);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id , Employee employeeDetails)
	{
		Employee updateEmployee = employeeService.updateEmployee(id, employeeDetails);
		return ResponseEntity.ok(updateEmployee);
	}
	
	
	public ResponseEntity<?> deleteEmployee(@PathVariable Long id)
	{
		employeeService.deleteEmployee(id);
		return ResponseEntity.ok().build();
	}
	 
	
	
}
