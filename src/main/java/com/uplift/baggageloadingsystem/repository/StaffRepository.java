package com.uplift.baggageloadingsystem.repository;

import com.uplift.baggageloadingsystem.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    Staff findByUsername(String username);
}
