package com.jgastaldi.vaultTest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.jgastaldi.vaultTest.model.Employee;
import com.jgastaldi.vaultTest.model.Job;
import com.jgastaldi.vaultTest.model.Location;

public interface EmployeeService {

	public Optional<Employee> findEmployeeById(Long id);

	public Employee saveEmployee(Employee employee);

	public void deleteEmployee(Employee employee);

	public List<Employee> findAllEmployeeByJobAndManagerAndLastName(Job job, Employee manager, String lastName,
			Pageable pageable);

	public Double findMeanSalaryByLocation(Location location);
}
