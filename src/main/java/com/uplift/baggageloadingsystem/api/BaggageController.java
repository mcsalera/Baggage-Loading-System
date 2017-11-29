package com.uplift.baggageloadingsystem.api;

import com.uplift.baggageloadingsystem.api.exceptions.ResourceNotFoundException;
import com.uplift.baggageloadingsystem.domain.Baggage;
import com.uplift.baggageloadingsystem.repository.BaggageRepository;
import com.uplift.baggageloadingsystem.service.BaggageService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value  = "/api/baggage")
public class BaggageController {

    private BaggageRepository baggageRepository;
    private BaggageService baggageService;

    BaggageController(BaggageRepository baggageRepository, BaggageService baggageService){
        this.baggageRepository = baggageRepository;
        this.baggageService = baggageService;
    }

    @GetMapping
    public Collection<Baggage> getBaggage(){
        return this.baggageRepository.findAll();
    }

    @PutMapping("/{code}")
    public Baggage updateBaggageStatus(@PathVariable("code") String code){
        return baggageService.loadBaggage(code);
    }

}
