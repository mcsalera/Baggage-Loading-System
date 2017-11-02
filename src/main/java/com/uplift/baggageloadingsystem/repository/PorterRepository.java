package com.uplift.baggageloadingsystem.repository;

import com.uplift.baggageloadingsystem.domain.Porter;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PorterRepository extends JpaRepository<Porter, Integer> {
    Porter findByPorterNumber(String number);
}