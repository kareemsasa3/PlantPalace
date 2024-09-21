package com.sasa.backend.dto.address;

import com.sasa.backend.entity.address.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotBlank(message = "Street address cannot be blank")
    @Size(max = 255, message = "Street address must be less than 255 characters")
    private String streetAddress;

    @NotBlank(message = "Address type cannot be blank")
    private String addressType;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "State cannot be blank")
    @Size(max = 2, message = "State must be a valid 2-letter code")
    private String state;

    @NotBlank(message = "Postal code cannot be blank")
    @Pattern(regexp = "\\d{5}", message = "Postal code must be a 5-digit number")
    private String postalCode;

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Email(message = "Email should be valid")
    private String emailAddress;
}