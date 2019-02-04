package com.jgastaldi.vaultTest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jgastaldi.vaultTest.model.Employee;
import com.jgastaldi.vaultTest.model.Job;
import com.jgastaldi.vaultTest.model.Location;
import com.jgastaldi.vaultTest.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Transactional(readOnly = true)
	public Optional<Employee> findEmployeeById(Long id) {
		return employeeRepository.findById(id);
	}

	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public void deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
	}

	@Transactional(readOnly = true)
	public List<Employee> findAllEmployeeByJobAndManagerAndLastName(Job job, Employee manager, String lastName,
			Pageable pageable) {
		List<Employee> result = employeeRepository.findByJobOrManagerOrLastName(job, manager, lastName, pageable);
		result.sort((emp1, emp2) -> emp1.getHireDate().compareTo(emp2.getHireDate()));
		return result;
	}

	@Transactional(readOnly = true)
	public Double findMeanSalaryByLocation(Location location) {
		return employeeRepository.findMeanSalaryByLocation(location);
	}
}
