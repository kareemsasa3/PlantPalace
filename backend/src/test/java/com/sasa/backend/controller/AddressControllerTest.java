package com.sasa.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sasa.backend.dto.user.AddressDTO;
import com.sasa.backend.entity.user.Address;
import com.sasa.backend.entity.user.AddressType;
import com.sasa.backend.exception.ResourceNotFoundException;
import com.sasa.backend.service.AddressService;
import com.sasa.backend.util.mapper.user.AddressMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AddressControllerTest {

    @InjectMocks
    private AddressController addressController;

    @Mock
    private AddressService addressService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();
        objectMapper = new ObjectMapper(); // For converting objects to JSON
    }

    @Test
    void testGetAllAddresses() throws Exception {
        Address address1 = new Address();
        address1.setAddressType(AddressType.HOME);
        Address address2 = new Address();
        address2.setAddressType(AddressType.WORK);

        AddressDTO d1 = AddressMapper.toDTO(address1);
        AddressDTO d2 = AddressMapper.toDTO(address2);

        when(addressService.getAllAddresses()).thenReturn(Arrays.asList(d1, d2));

        mockMvc.perform(get("/api/addresses"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data", hasSize(2))) // Look for data field
            .andExpect(jsonPath("$.data[0].addressType", is(AddressType.HOME.name())))
            .andExpect(jsonPath("$.data[1].addressType", is(AddressType.WORK.name())));
    }


    @Test
    void testGetAddressById() throws Exception {
        Address address = new Address();
        address.setAddressType(AddressType.HOME);
        AddressDTO addressDTO = AddressMapper.toDTO(address);

        when(addressService.getAddressById(anyLong())).thenReturn(addressDTO);

        mockMvc.perform(get("/api/addresses/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.addressType", is(AddressType.HOME.name()))); // Adjust for 'data' field
    }

    @Test
    void testGetAddressById_NotFound() throws Exception {
        when(addressService.getAddressById(anyLong())).thenThrow(new ResourceNotFoundException("Address not found with id: 1"));

        mockMvc.perform(get("/api/addresses/1"))
            .andExpect(status().isNotFound())
            .andReturn();

        // Check for the error message
        mockMvc.perform(get("/api/addresses/1"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.data").doesNotExist())
            .andExpect(jsonPath("$.error", is("Address not found with id: 1")));
    }

    @Test
    void testCreateAddress() throws Exception {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressType(AddressType.HOME.name());

        when(addressService.createAddress(any(AddressDTO.class))).thenReturn(addressDTO);

        mockMvc.perform(post("/api/addresses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressDTO)))
            .andExpect(status().isCreated())
            .andReturn();

        // Verify the response
        mockMvc.perform(post("/api/addresses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.data.addressType", is(AddressType.HOME.name())));
    }


    @Test
    void testUpdateAddress() throws Exception {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressType(AddressType.WORK.name());

        // Mock the service to return the ApiResponse
        when(addressService.updateAddress(anyLong(), any(AddressDTO.class))).thenReturn(addressDTO);

        // Perform the PUT request
        mockMvc.perform(put("/api/addresses/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.addressType", is(AddressType.WORK.name())))
            .andExpect(jsonPath("$.error").doesNotExist()); // Ensure error field is absent
    }

    @Test
    void testDeleteAddress() throws Exception {
        doNothing().when(addressService).deleteAddress(anyLong());

        mockMvc.perform(delete("/api/addresses/1"))
            .andExpect(status().isNoContent());
    }
}
