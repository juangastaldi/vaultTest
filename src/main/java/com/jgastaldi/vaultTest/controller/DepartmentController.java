package com.jgastaldi.vaultTest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jgastaldi.vaultTest.exception.ErrorValitationException;
import com.jgastaldi.vaultTest.model.Department;
import com.jgastaldi.vaultTest.service.DepartmentService;
import com.jgastaldi.vaultTest.validator.DepartmentValidator;

@RestController
@RequestMapping("api/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private DepartmentValidator departmentValidator;

	@PostMapping
	public Department createDepartment(@Valid @RequestBody Department department, Errors error) {
		departmentValidator.validate(department, error);
		if (!error.hasErrors()) {
			return departmentService.saveDepartement(department);
		} else {
			throw new ErrorValitationException(error);
		}
	}

}
