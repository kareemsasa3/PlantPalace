package com.sasa.backend.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

import com.sasa.backend.dto.user.UserDTO;
import com.sasa.backend.entity.address.Address;
import com.sasa.backend.entity.order.Order;
import com.sasa.backend.entity.role.Role;

/**
 * User is a JPA entity used for database operations
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    public User(Long id) {
        this.id = id;
    }

    public User(UserDTO dto) {
        this.id = dto.getId();
        this.username = dto.getUsername();
        this.email = dto.getEmail();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orderHistory = Collections.emptyList();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> addresses = Collections.emptyList();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_name")
    private Role role;
}
