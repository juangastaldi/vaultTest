package com.jgastaldi.vaultTest.validator;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jgastaldi.vaultTest.model.Department;
import com.jgastaldi.vaultTest.service.EmployeeService;

@Component("beforeCreateDepartmentValidator")
public class DepartmentValidator implements Validator {

	@Autowired
	private EmployeeService employeeService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Department.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors error) {
		Department department = (Department) obj;
		Double mean = employeeService.findMeanSalaryByLocation(department.getLocation());
		String reject = "No se puede ingresar un nuevo Departamento ya que el promedio ";
		if (LocalDate.now().getDayOfMonth() <= 14) {
			if (mean > 1000.0) {
				error.reject(reject + "es mayor a $1000 y se encuentra dentro de las primeras dos semanas");
			}
		} else {
			if (mean > 1500.0) {
				error.reject(reject + "es mayor a $1500 y se encuentra despues de las dos primeras semanas");
			}
		}
	}

}
