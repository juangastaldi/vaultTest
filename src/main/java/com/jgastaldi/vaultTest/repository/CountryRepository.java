package com.jgastaldi.vaultTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jgastaldi.vaultTest.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
