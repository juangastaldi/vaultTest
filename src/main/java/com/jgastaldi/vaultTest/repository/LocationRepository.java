package com.jgastaldi.vaultTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jgastaldi.vaultTest.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
