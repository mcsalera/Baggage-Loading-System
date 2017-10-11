package com.uplift.baggageloadingsystem.repository;

import com.uplift.baggageloadingsystem.domain.BaggageCounter;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface BaggageCounterRepository extends JpaRepository<BaggageCounter, Integer> {

}