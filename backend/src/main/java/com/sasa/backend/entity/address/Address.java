package com.sasa.backend.entity.address;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sasa.backend.dto.address.AddressDTO;
import com.sasa.backend.entity.user.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "addresses")
public class Address {

    public Address() {
        user = new User();
    }

    public Address(AddressDTO dto) {
        this.id = dto.getId();
        this.streetAddress = dto.getStreetAddress();
        this.addressType = AddressType.valueOf(dto.getAddressType());
        this.city = dto.getCity();
        this.state = dto.getState();
        this.postalCode = dto.getPostalCode();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.emailAddress = dto.getEmailAddress();
    }

    public enum AddressType {
        BILLING,
        SHIPPING,
        HOME,
        WORK;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    private String streetAddress;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    @NotNull
    private AddressType addressType;
    
    private String city;

    private String state;

    private String postalCode;

    private String firstName;

    private String lastName;

    private String emailAddress;
}
