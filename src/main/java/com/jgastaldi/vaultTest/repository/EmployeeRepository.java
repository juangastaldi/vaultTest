package com.jgastaldi.vaultTest.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jgastaldi.vaultTest.model.Employee;
import com.jgastaldi.vaultTest.model.Job;
import com.jgastaldi.vaultTest.model.Location;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public List<Employee> findByJobOrManagerOrLastName(Job job, Employee manager, String lastName,
			Pageable pageable);

	@Query("SELECT SUM(employee.salary) / COUNT(*) FROM Employee employee JOIN employee.department department WHERE department.location = :location ")
	public Double findMeanSalaryByLocation(@Param("location") Location location);
}
