package com.jgastaldi.vaultTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jgastaldi.vaultTest.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

}
