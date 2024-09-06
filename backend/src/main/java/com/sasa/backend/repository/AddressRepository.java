package com.sasa.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sasa.backend.entity.user.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {}