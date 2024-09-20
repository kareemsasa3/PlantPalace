package com.sasa.backend.repository.address;

import com.sasa.backend.entity.address.Address;
import com.sasa.backend.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByUser(User user);
}