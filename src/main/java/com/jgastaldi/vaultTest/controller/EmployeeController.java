package com.jgastaldi.vaultTest.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jgastaldi.vaultTest.exception.ResourceNotFoundException;
import com.jgastaldi.vaultTest.model.Employee;
import com.jgastaldi.vaultTest.model.Job;
import com.jgastaldi.vaultTest.model.dto.EmployeeDTO;
import com.jgastaldi.vaultTest.service.EmployeeService;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	private static final ModelMapper modelMapper = new ModelMapper();

	@PostMapping
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}

	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee newEmployee) {
		Employee employee = employeeService.findEmployeeById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

		employee.setCommissionPct(newEmployee.getCommissionPct());
		employee.setDepartment(newEmployee.getDepartment());
		employee.setEmail(newEmployee.getEmail());
		employee.setFirstName(newEmployee.getFirstName());
		employee.setHireDate(newEmployee.getHireDate());
		employee.setJob(newEmployee.getJob());
		employee.setLastName(newEmployee.getLastName());
		employee.setManager(newEmployee.getManager());
		employee.setPhoneNumber(newEmployee.getPhoneNumber());
		employee.setSalary(newEmployee.getSalary());

		return employeeService.saveEmployee(employee);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
		Employee employee = employeeService.findEmployeeById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

		employeeService.deleteEmployee(employee);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public EmployeeDTO getEmployeeById(@PathVariable(value = "id") Long employeeId) {

		return modelMapper.map(employeeService.findEmployeeById(employeeId).orElse(null), EmployeeDTO.class);
	}

	@GetMapping
	public List<Employee> getAllEmployee(@RequestParam(name = "jobId", required = false) Long jobId,
			@RequestParam(name = "managerId", required = false) Long managerId,
			@RequestParam(name = "lastName", required = false) String lastName, Pageable pageRequest) {
		return employeeService.findAllEmployeeByJobAndManagerAndLastName(jobId != null ? new Job(jobId) : null,
				managerId != null ? new Employee(managerId) : null, lastName, pageRequest);
	}

}
