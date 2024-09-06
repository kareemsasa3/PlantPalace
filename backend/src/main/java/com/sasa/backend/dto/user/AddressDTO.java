package com.sasa.backend.dto.user;

import com.sasa.backend.entity.user.Address.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {

    private Long id;
    private Long userId;  // Include userId for association
    private String streetAddress;
    private AddressType addressType; // Use existing enum
    private String city;
    private String state;
    private String postalCode;
    private String firstName;
    private String lastName;
    private String emailAddress;
}