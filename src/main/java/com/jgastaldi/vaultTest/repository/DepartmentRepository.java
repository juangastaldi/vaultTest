package com.jgastaldi.vaultTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jgastaldi.vaultTest.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
