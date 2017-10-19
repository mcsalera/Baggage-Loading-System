package com.uplift.baggageloadingsystem.repository;

import com.uplift.baggageloadingsystem.domain.Baggage;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface BaggageRepository extends JpaRepository<Baggage, Integer> {
    Baggage findByCode(String code);
}