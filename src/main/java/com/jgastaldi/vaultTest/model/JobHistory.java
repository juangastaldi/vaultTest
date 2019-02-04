package com.jgastaldi.vaultTest.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "JOB_HISTORY")
public class JobHistory implements Serializable {

	private static final long serialVersionUID = 8953819595184379090L;

	@Id
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn
	private Employee employee;

	@NotNull
	private LocalDate startDate;

	@NotNull
	private LocalDate endDate;

	@ManyToOne(optional = false)
	@JoinColumn
	private Job job;

	@ManyToOne(optional = true)
	@JoinColumn
	private Department department;

	public JobHistory() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
