package com.sasa.backend.dto;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private Long id;
    private String streetAddress;
    private String addressType; // BILLING, SHIPPING, HOME, WORK
    private String city;
    private String state;
    private String postalCode;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Long userId; // Add this field to relate to User

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDTO that = (AddressDTO) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(streetAddress, that.streetAddress) &&
               Objects.equals(addressType, that.addressType) &&
               Objects.equals(city, that.city) &&
               Objects.equals(state, that.state) &&
               Objects.equals(postalCode, that.postalCode) &&
               Objects.equals(userId, that.userId); // Include userId in equals
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetAddress, addressType, city, state, postalCode, userId); // Include userId in hashCode
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
               "id=" + id +
               ", streetAddress='" + streetAddress + '\'' +
               ", addressType='" + addressType + '\'' +
               ", city='" + city + '\'' +
               ", state='" + state + '\'' +
               ", postalCode='" + postalCode + '\'' +
               ", userId=" + userId + // Include userId in toString
               '}';
    }
}
