package com.sasa.backend.mapper;

import java.util.Collections;
import java.util.stream.Collectors;

import com.sasa.backend.dto.AddressDTO;
import com.sasa.backend.dto.PatientDTO;
import com.sasa.backend.dto.PhoneNumberDTO;
import com.sasa.backend.entity.Address;
import com.sasa.backend.entity.AddressType;
import com.sasa.backend.entity.Patient;
import com.sasa.backend.entity.PhoneNumber;
import com.sasa.backend.entity.PhoneType;
import com.sasa.backend.exception.InvalidEnumValueException;

public final class PatientMapper {

    private PatientMapper() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    public static PatientDTO toDTO(Patient patient) {
        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setFirstName(patient.getFirstName());
        dto.setLastName(patient.getLastName());
        dto.setEmail(patient.getEmail());
        dto.setGender(patient.getGender());
        dto.setHeight(patient.getHeight());
        dto.setWeight(patient.getWeight());
        dto.setDateOfBirth(patient.getDateOfBirth());
        dto.setPhoneNumbers(patient.getPhoneNumbers() != null ? 
            patient.getPhoneNumbers().stream()
                .map(PatientMapper::toDTO)
                .collect(Collectors.toList()) : 
            Collections.emptyList());
        dto.setAddresses(patient.getAddresses() != null ? 
            patient.getAddresses().stream()
                .map(PatientMapper::toDTO)
                .collect(Collectors.toList()) : 
            Collections.emptyList());
        return dto;
    }
    
    public static Patient toEntity(PatientDTO dto) {
        Patient patient = new Patient();
        patient.setId(dto.getId());
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setEmail(dto.getEmail());
        patient.setGender(dto.getGender());
        patient.setHeight(dto.getHeight());
        patient.setWeight(dto.getWeight());
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setPhoneNumbers(dto.getPhoneNumbers() != null ? 
            dto.getPhoneNumbers().stream()
                .map(PatientMapper::toEntity)
                .collect(Collectors.toList()) : 
            Collections.emptyList());
        patient.setAddresses(dto.getAddresses() != null ? 
            dto.getAddresses().stream()
                .map(PatientMapper::toEntity)
                .collect(Collectors.toList()) : 
            Collections.emptyList());
        return patient;
    }

    public static PhoneNumberDTO toDTO(PhoneNumber phoneNumber) {
        PhoneNumberDTO dto = new PhoneNumberDTO();
        dto.setId(phoneNumber.getId());
        dto.setNumber(phoneNumber.getNumber());
        dto.setType(phoneNumber.getType().name());
        return dto;
    }

    public static PhoneNumber toEntity(PhoneNumberDTO dto) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setId(dto.getId());
        phoneNumber.setNumber(dto.getNumber());
        phoneNumber.setType(convertStringToEnum(PhoneType.class, dto.getType()));
        return phoneNumber;
    }

    public static AddressDTO toDTO(Address address) {
        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setStreetAddress(address.getStreetAddress());
        dto.setAddressType(address.getAddressType().name());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setPostalCode(address.getPostalCode());
        return dto;
    }

    public static Address toEntity(AddressDTO dto) {
        Address address = new Address();
        address.setId(dto.getId());
        address.setStreetAddress(dto.getStreetAddress());
        address.setAddressType(convertStringToEnum(AddressType.class, dto.getAddressType()));
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPostalCode(dto.getPostalCode());
        return address;
    }

    private static <T extends Enum<T>> T convertStringToEnum(Class<T> enumType, String type) {
        if (type == null) {
            throw new InvalidEnumValueException("Type cannot be null for enum: " + enumType.getSimpleName());
        }
        try {
            return Enum.valueOf(enumType, type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException("Invalid value: " + type + " for enum: " + enumType.getSimpleName());
        }
    }
    
}
