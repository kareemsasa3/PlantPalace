package com.sasa.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sasa.backend.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {}