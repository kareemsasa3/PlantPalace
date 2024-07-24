package com.sasa.medmanage.service;

import com.sasa.medmanage.constant.Constants;
import com.sasa.medmanage.dto.PatientDTO;
import com.sasa.medmanage.entity.Address;
import com.sasa.medmanage.entity.Patient;
import com.sasa.medmanage.exception.ResourceNotFoundException;
import com.sasa.medmanage.repository.PatientRepository;
import com.sasa.medmanage.mapper.AddressMapper;
import com.sasa.medmanage.mapper.PatientMapper;
import com.sasa.medmanage.util.DateUtil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService implements PatientServiceInterface {

    private final PatientRepository patientRepository;
    private final AddressServiceInterface addressService;

    public PatientService(PatientRepository patientRepository, AddressServiceInterface addressService) {
        this.patientRepository = patientRepository;
        this.addressService = addressService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(PatientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.PATIENT_NOT_FOUND_MESSAGE + id));
        return PatientMapper.toDTO(patient);
    }

    @Override
    @Transactional
    public int getPatientAge(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.PATIENT_NOT_FOUND_MESSAGE + id));
        return DateUtil.calculateAge(patient.getDateOfBirth());
    }

    @Override
    @Transactional
    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = PatientMapper.toEntity(patientDTO);
        Patient createdPatient = patientRepository.save(patient);

        if (patient.getAddresses() != null) {
            for (Address address : patient.getAddresses()) {
                address.setPatient(createdPatient);
                addressService.createAddress(AddressMapper.toDTO(address));
            }
        }

        return PatientMapper.toDTO(createdPatient);
    }

    @Override
    @Transactional
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
    
        // Update fields only if the provided values are not null or not default for primitives
        if (patientDTO.getFirstName() != null) {
            existingPatient.setFirstName(patientDTO.getFirstName());
        }
        if (patientDTO.getLastName() != null) {
            existingPatient.setLastName(patientDTO.getLastName());
        }
        if (patientDTO.getEmail() != null) {
            existingPatient.setEmail(patientDTO.getEmail());
        }
        if (patientDTO.getGender() != null) {
            existingPatient.setGender(patientDTO.getGender());
        }
        if (patientDTO.getHeight() != 0.0) {
            existingPatient.setHeight(patientDTO.getHeight());
        }
        if (patientDTO.getWeight() != 0.0) {
            existingPatient.setWeight(patientDTO.getWeight());
        }
        if (patientDTO.getDateOfBirth() != null) {
            existingPatient.setDateOfBirth(patientDTO.getDateOfBirth());
        }
        if (patientDTO.getAddresses() != null) {
            List<Address> updatedAddresses = patientDTO.getAddresses().stream()
                    .map(AddressMapper::toEntity)
                    .collect(Collectors.toList());
            existingPatient.setAddresses(updatedAddresses);
        }
    
        Patient updatedPatient = patientRepository.save(existingPatient);
        return PatientMapper.toDTO(updatedPatient);
    }    

    @Override
    @Transactional
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + id));
        
        if (patient.getAddresses() != null) {
            for (Address address : patient.getAddresses()) {
                addressService.deleteAddress(address.getId());
            }
        }
        
        patientRepository.delete(patient);
    }
}
