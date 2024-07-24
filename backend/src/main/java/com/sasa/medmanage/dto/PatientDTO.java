package com.sasa.medmanage.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private Double height;
    private Double weight;
    private LocalDate dateOfBirth;
    private List<PhoneNumberDTO> phoneNumbers;
    private List<AddressDTO> addresses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientDTO that = (PatientDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(height, that.height) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(phoneNumbers, that.phoneNumbers) &&
                Objects.equals(addresses, that.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, gender, height, weight, dateOfBirth, phoneNumbers, addresses);
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                ", addresses=" + addresses +
                '}';
    }
}
