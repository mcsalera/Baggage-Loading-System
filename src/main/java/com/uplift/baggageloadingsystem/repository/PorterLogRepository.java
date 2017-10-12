package com.uplift.baggageloadingsystem.repository;

import com.uplift.baggageloadingsystem.domain.PorterLog;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PorterLogRepository extends JpaRepository<PorterLog, Integer> {

}