package com.uplift.baggageloadingsystem.api;

import com.uplift.baggageloadingsystem.domain.Baggage;
import com.uplift.baggageloadingsystem.repository.BaggageRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value  = "/baggage")
public class BaggageController {
    private BaggageRepository baggageRepository;

    BaggageController(BaggageRepository baggageRepository){
        this.baggageRepository = baggageRepository;
    }

    @GetMapping
    public List<Baggage> getBaggage(){return this.baggageRepository.findAll();}



}
