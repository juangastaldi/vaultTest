package com.jgastaldi.vaultTest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jgastaldi.vaultTest.model.Department;
import com.jgastaldi.vaultTest.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	public Department saveDepartement(Department department) {
		return departmentRepository.save(department);
	}
}
