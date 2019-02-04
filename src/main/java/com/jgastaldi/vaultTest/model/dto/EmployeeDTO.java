package com.jgastaldi.vaultTest.model.dto;

import java.util.Date;

import com.jgastaldi.vaultTest.model.Department;
import com.jgastaldi.vaultTest.model.Employee;
import com.jgastaldi.vaultTest.model.Job;

public class EmployeeDTO {

	private Long id;

	private Employee manager;

	private Job job;

	private Department department;

	private String firstName;

	private String lastName;

	private String email;

	private Long phoneNumber;

	private Date hireDate;

	private Double salary;

	private Double commissionPct;

	public EmployeeDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getManager() {
		return manager != null ? manager.getId() : null;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Long getJob() {
		return job != null ? job.getId() : null;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Long getDepartment() {
		return department != null ? department.getId() : null;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Double getCommissionPct() {
		return commissionPct;
	}

	public void setCommissionPct(Double commissionPct) {
		this.commissionPct = commissionPct;
	}
}
