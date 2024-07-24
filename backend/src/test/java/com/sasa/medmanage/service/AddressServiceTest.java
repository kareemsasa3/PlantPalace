package com.sasa.medmanage.service;

import com.sasa.medmanage.dto.AddressDTO;
import com.sasa.medmanage.entity.Address;
import com.sasa.medmanage.entity.AddressType;
import com.sasa.medmanage.mapper.AddressMapper;
import com.sasa.medmanage.repository.AddressRepository;
import com.sasa.medmanage.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @BeforeEach
    void setUp() {
        // MockitoExtension handles initialization
    }

    @Test
    void testGetAllAddresses() {
        // Create Address entities with AddressType set
        Address address1 = new Address();
        address1.setAddressType(AddressType.HOME); 
        Address address2 = new Address();
        address2.setAddressType(AddressType.WORK);

        // Convert Address entities to AddressDTO
        AddressDTO d1 = AddressMapper.toDTO(address1);
        AddressDTO d2 = AddressMapper.toDTO(address2);

        // Mock repository to return the address entities
        when(addressRepository.findAll()).thenReturn(Arrays.asList(address1, address2));

        // Call service method
        List<AddressDTO> addressDTOs = addressService.getAllAddresses();

        // Assert that the result is not null
        assertNotNull(addressDTOs);
        assertEquals(2, addressDTOs.size());

        System.out.println("AddressDTOs from service: " + addressDTOs);

        // Assert that the result contains the expected AddressDTOs
        // Use assertTrue with detailed failure message
        assertTrue(addressDTOs.contains(d1), "Expected AddressDTO list to contain: " + d1);
        assertTrue(addressDTOs.contains(d2), "Expected AddressDTO list to contain: " + d2);

        // Additional Debugging
        System.out.println("Expected AddressDTOs: " + Arrays.asList(d1, d2));
    }

    @Test
    void testGetAddressById() {
        Address address = new Address();
        address.setAddressType(AddressType.HOME); // Set non-null AddressType
        AddressDTO addressDTO = AddressMapper.toDTO(address);

        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        AddressDTO result = addressService.getAddressById(1L);

        assertNotNull(result);
        assertEquals(addressDTO.getAddressType(), result.getAddressType()); // Ensure AddressType is not null
    }

    @Test
    void testGetAddressById_NotFound() {
        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> addressService.getAddressById(1L));
        assertEquals("Address not found with id: 1", thrown.getMessage());
    }

    @Test
    void testCreateAddress() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressType(AddressType.HOME.name()); // Set non-null AddressType
        Address address = AddressMapper.toEntity(addressDTO);

        when(addressRepository.save(address)).thenReturn(address);
        AddressDTO result = addressService.createAddress(addressDTO);

        assertNotNull(result);
        assertEquals(addressDTO.getAddressType(), result.getAddressType());
    }

    @Test
    void testUpdateAddress() {
        Long id = 1L;
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressType(AddressType.HOME.name()); // Set non-null AddressType
        Address existingAddress = new Address();
        existingAddress.setId(id);
        existingAddress.setAddressType(AddressType.WORK);

        when(addressRepository.findById(id)).thenReturn(Optional.of(existingAddress));
        when(addressRepository.save(existingAddress)).thenReturn(existingAddress);

        AddressDTO updatedAddressDTO = addressService.updateAddress(id, addressDTO);

        assertNotNull(updatedAddressDTO);
        assertEquals(AddressType.HOME.name(), updatedAddressDTO.getAddressType());
    }

    @Test
    void testDeleteAddress() {
        Long id = 1L;
        Address existingAddress = new Address();
        existingAddress.setId(id);

        when(addressRepository.findById(id)).thenReturn(Optional.of(existingAddress));
        doNothing().when(addressRepository).delete(existingAddress);

        addressService.deleteAddress(id);

        verify(addressRepository, times(1)).delete(existingAddress);
    }
}
