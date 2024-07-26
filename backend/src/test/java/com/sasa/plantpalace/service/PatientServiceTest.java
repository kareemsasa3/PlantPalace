package com.sasa.plantpalace.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sasa.backend.dto.AddressDTO;
import com.sasa.backend.dto.PatientDTO;
import com.sasa.backend.entity.Patient;
import com.sasa.backend.exception.ResourceNotFoundException;
import com.sasa.backend.mapper.PatientMapper;
import com.sasa.backend.repository.PatientRepository;
import com.sasa.backend.service.AddressServiceInterface;
import com.sasa.backend.service.PatientService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private AddressServiceInterface addressService;

    @BeforeEach
    void setUp() {
        patientService = new PatientService(patientRepository, addressService);
    }

    @Test
    void testCreatePatient() {
        // Set up the DTO
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setFirstName("John");
        patientDTO.setLastName("Doe");
        // ... set other properties

        // Convert DTO to entity using the actual PatientMapper
        Patient patient = PatientMapper.toEntity(patientDTO);
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        // Convert the saved entity back to DTO
        PatientDTO savedDTO = PatientMapper.toDTO(patient);

        // Call the service method
        PatientDTO result = patientService.createPatient(patientDTO);

        // Assertions
        assertNotNull(result);
        assertEquals(savedDTO.getFirstName(), result.getFirstName());
        assertEquals(savedDTO.getLastName(), result.getLastName());
        // ... add other assertions as needed
    }

    @Test
    void testGetAllPatients() {
        Patient patient1 = new Patient();
        Patient patient2 = new Patient();
        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient1, patient2));

        PatientDTO dto1 = PatientMapper.toDTO(patient1);
        PatientDTO dto2 = PatientMapper.toDTO(patient2);
        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient1, patient2));

        List<PatientDTO> patients = patientService.getAllPatients();

        assertNotNull(patients);
        assertEquals(2, patients.size());
        assertEquals(dto1, patients.get(0));
        assertEquals(dto2, patients.get(1));
    }

    @Test
    void testGetPatientById() {
        Long patientId = 1L;
        Patient patient = new Patient();
        patient.setId(patientId);
        patient.setFirstName("John");
        patient.setLastName("Doe");
        // Set other necessary fields

        PatientDTO expectedPatientDTO = new PatientDTO();
        expectedPatientDTO.setId(patientId);
        expectedPatientDTO.setFirstName("John");
        expectedPatientDTO.setLastName("Doe");
        // Set other necessary fields

        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));
        PatientDTO actualPatientDTO = patientService.getPatientById(patientId);

        // Print debug information
        System.out.println("Expected: " + expectedPatientDTO);
        System.out.println("Actual: " + actualPatientDTO);

        // Use assertEquals to compare objects based on content
        assertNotNull(actualPatientDTO);
        assertEquals(expectedPatientDTO.getId(), actualPatientDTO.getId());
        assertEquals(expectedPatientDTO.getFirstName(), actualPatientDTO.getFirstName());
        assertEquals(expectedPatientDTO.getLastName(), actualPatientDTO.getLastName());
        // Add other field comparisons as needed
    }

    @Test
    void testGetPatientById_NotFound() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> patientService.getPatientById(1L));
        assertEquals("Patient not found with id: 1", thrown.getMessage());
    }

    @Test
    void testUpdatePatientWithNonNullValues() {
        Long id = 1L;

        // Create AddressDTO list with non-null values
        AddressDTO addressDTO1 = new AddressDTO();
        addressDTO1.setStreetAddress("123 Main St");
        addressDTO1.setAddressType("HOME"); // Corrected

        AddressDTO addressDTO2 = new AddressDTO();
        addressDTO2.setStreetAddress("456 Elm St");
        addressDTO2.setAddressType("SHIPPING"); // Corrected

        List<AddressDTO> newAddresses = Arrays.asList(addressDTO1, addressDTO2);

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setAddresses(newAddresses); // Ensure addresses are set
        patientDTO.setFirstName("John");
        patientDTO.setLastName("Doe");
        patientDTO.setEmail("john.doe@example.com");
        patientDTO.setGender("Male");
        patientDTO.setHeight(180.0);
        patientDTO.setWeight(75.0);
        patientDTO.setDateOfBirth(LocalDate.of(1990, 1, 1));

        Patient existingPatient = new Patient();
        existingPatient.setId(id);
        existingPatient.setAddresses(new ArrayList<>()); // Initialize to empty list

        // Set up mocks
        when(patientRepository.findById(id)).thenReturn(Optional.of(existingPatient));
        when(patientRepository.save(any(Patient.class))).thenReturn(existingPatient);

        // Perform the update
        PatientDTO updatedPatientDTO = patientService.updatePatient(id, patientDTO);

        // Verify the results
        assertNotNull(updatedPatientDTO);
        assertNotNull(updatedPatientDTO.getAddresses());
        assertEquals(newAddresses.size(), updatedPatientDTO.getAddresses().size());

        // Check individual address details
        assertEquals(newAddresses.get(0).getStreetAddress(), updatedPatientDTO.getAddresses().get(0).getStreetAddress());
        assertEquals(newAddresses.get(0).getAddressType(), updatedPatientDTO.getAddresses().get(0).getAddressType());
        assertEquals(newAddresses.get(1).getStreetAddress(), updatedPatientDTO.getAddresses().get(1).getStreetAddress());
        assertEquals(newAddresses.get(1).getAddressType(), updatedPatientDTO.getAddresses().get(1).getAddressType());

        // Check patient details
        assertEquals("John", updatedPatientDTO.getFirstName());
        assertEquals("Doe", updatedPatientDTO.getLastName());
        assertEquals("john.doe@example.com", updatedPatientDTO.getEmail());
        assertEquals("Male", updatedPatientDTO.getGender());
        assertEquals(180.0, updatedPatientDTO.getHeight());
        assertEquals(75.0, updatedPatientDTO.getWeight());
        assertEquals(LocalDate.of(1990, 1, 1), updatedPatientDTO.getDateOfBirth());
    }

    @Test
    void testUpdatePatientWithNullValues() {
        Long id = 1L;

        // Create AddressDTO list with non-null values
        AddressDTO addressDTO1 = new AddressDTO();
        addressDTO1.setStreetAddress("123 Main St");
        addressDTO1.setAddressType("HOME"); // Corrected

        AddressDTO addressDTO2 = new AddressDTO();
        addressDTO2.setStreetAddress("456 Elm St");
        addressDTO2.setAddressType("SHIPPING"); // Corrected

        List<AddressDTO> newAddresses = Arrays.asList(addressDTO1, addressDTO2);

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setAddresses(newAddresses); // Ensure addresses are set
        // Set other fields to null to simulate the scenario
        patientDTO.setFirstName(null);
        patientDTO.setLastName(null);
        patientDTO.setEmail(null);
        patientDTO.setGender(null);
        patientDTO.setHeight(0.0); // Assuming height and weight can't be null, setting to default value
        patientDTO.setWeight(0.0);
        patientDTO.setDateOfBirth(null);

        Patient existingPatient = new Patient();
        existingPatient.setId(id);
        existingPatient.setFirstName("ExistingFirstName");
        existingPatient.setLastName("ExistingLastName");
        existingPatient.setEmail("existing@example.com");
        existingPatient.setGender("ExistingGender");
        existingPatient.setHeight(175.0);
        existingPatient.setWeight(70.0);
        existingPatient.setDateOfBirth(LocalDate.of(1985, 1, 1));
        existingPatient.setAddresses(new ArrayList<>()); // Initialize to empty list

        // Set up mocks
        when(patientRepository.findById(id)).thenReturn(Optional.of(existingPatient));
        when(patientRepository.save(any(Patient.class))).thenReturn(existingPatient);

        // Perform the update
        PatientDTO updatedPatientDTO = patientService.updatePatient(id, patientDTO);

        // Verify the results
        assertNotNull(updatedPatientDTO);
        assertNotNull(updatedPatientDTO.getAddresses());
        assertEquals(newAddresses.size(), updatedPatientDTO.getAddresses().size());

        // Check individual address details
        assertEquals(newAddresses.get(0).getStreetAddress(), updatedPatientDTO.getAddresses().get(0).getStreetAddress());
        assertEquals(newAddresses.get(0).getAddressType(), updatedPatientDTO.getAddresses().get(0).getAddressType());
        assertEquals(newAddresses.get(1).getStreetAddress(), updatedPatientDTO.getAddresses().get(1).getStreetAddress());
        assertEquals(newAddresses.get(1).getAddressType(), updatedPatientDTO.getAddresses().get(1).getAddressType());

        // Check that existing patient details are not overwritten with null
        assertEquals("ExistingFirstName", updatedPatientDTO.getFirstName());
        assertEquals("ExistingLastName", updatedPatientDTO.getLastName());
        assertEquals("existing@example.com", updatedPatientDTO.getEmail());
        assertEquals("ExistingGender", updatedPatientDTO.getGender());
        assertEquals(175.0, updatedPatientDTO.getHeight());
        assertEquals(70.0, updatedPatientDTO.getWeight());
        assertEquals(LocalDate.of(1985, 1, 1), updatedPatientDTO.getDateOfBirth());
    }


    @Test
    void testDeletePatient() {
        Long id = 1L;
        Patient existingPatient = new Patient();
        existingPatient.setId(id);

        when(patientRepository.findById(id)).thenReturn(Optional.of(existingPatient));
        doNothing().when(patientRepository).delete(existingPatient);

        patientService.deletePatient(id);

        verify(patientRepository, times(1)).delete(existingPatient);
    }
}
