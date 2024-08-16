package com.sasa.backend.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    private String streetAddress;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;
    
    private String city;
    private String state;
    private String postalCode;

    private String firstName;
    private String lastName;
    private String emailAddress;

    // Additional methods if needed
}
