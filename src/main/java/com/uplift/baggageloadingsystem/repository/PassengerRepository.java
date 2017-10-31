package com.uplift.baggageloadingsystem.repository;


import com.uplift.baggageloadingsystem.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;


public interface    PassengerRepository extends JpaRepository<Passenger, Integer> {
    Passenger findByCode(String code);
}