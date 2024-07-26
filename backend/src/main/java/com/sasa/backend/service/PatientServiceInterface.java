package com.sasa.backend.service;

import java.util.List;

import com.sasa.backend.dto.PatientDTO;

public interface PatientServiceInterface {
    
    List<PatientDTO> getAllPatients();

    PatientDTO getPatientById(Long id);

    PatientDTO createPatient(PatientDTO patientDTO);

    PatientDTO updatePatient(Long id, PatientDTO patientDTO);

    void deletePatient(Long id);

    int getPatientAge(Long id);
}
