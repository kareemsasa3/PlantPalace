package com.sasa.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sasa.backend.dto.AddressDTO;
import com.sasa.backend.dto.ApiResponse;
import com.sasa.backend.service.AddressService;
import com.sasa.backend.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AddressDTO>>> getAllAddresses() {
        List<AddressDTO> addresses = addressService.getAllAddresses();
        return ResponseEntity.ok(new ApiResponse<>(addresses, null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AddressDTO>> getAddressById(@PathVariable Long id) {
        try {
            AddressDTO address = addressService.getAddressById(id);
            return ResponseEntity.ok(new ApiResponse<>(address, null));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new ApiResponse<>(null, ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AddressDTO>> createAddress(@RequestBody AddressDTO addressDTO) {
        AddressDTO createdAddress = addressService.createAddress(addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new ApiResponse<>(createdAddress, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AddressDTO>> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        AddressDTO updatedAddress = addressService.updateAddress(id, addressDTO);
        return ResponseEntity.ok(new ApiResponse<>(updatedAddress, null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
