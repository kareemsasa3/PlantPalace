package com.sasa.backend.dto.address;

import com.sasa.backend.entity.address.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.userId = address.getUser().getId();
        this.streetAddress = address.getStreetAddress();
        this.addressType = address.getAddressType().name();
        this.city = address.getCity();
        this.state = address.getState();
        this.postalCode = address.getPostalCode();
        this.firstName = address.getFirstName();
        this.lastName = address.getLastName();
        this.emailAddress = address.getEmailAddress();
    }

    private Long id;

    private Long userId;

    private String streetAddress;

    private String addressType;

    private String city;

    private String state;

    private String postalCode;
    
    private String firstName;

    private String lastName;
    
    private String emailAddress;
}