package com.uplift.baggageloadingsystem.repository;


import com.uplift.baggageloadingsystem.domain.Passenger;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author Bert
 */
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    Passenger findByCode(String code);
}