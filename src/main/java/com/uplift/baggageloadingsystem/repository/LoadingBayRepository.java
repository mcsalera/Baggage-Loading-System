package com.uplift.baggageloadingsystem.repository;


import com.uplift.baggageloadingsystem.domain.LoadingBay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;


public interface LoadingBayRepository extends JpaRepository<LoadingBay, Integer> {

}
