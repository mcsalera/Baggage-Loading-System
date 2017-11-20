package com.uplift.baggageloadingsystem.api;

import com.uplift.baggageloadingsystem.api.exceptions.ResourceNotFoundException;
import com.uplift.baggageloadingsystem.domain.Baggage;
import com.uplift.baggageloadingsystem.repository.BaggageRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value  = "/api/baggage")
public class BaggageController {
    private BaggageRepository baggageRepository;

    BaggageController(BaggageRepository baggageRepository){
        this.baggageRepository = baggageRepository;
    }

    @GetMapping
    public Collection<Baggage> getBaggage(){return this.baggageRepository.findAll();}

    @PutMapping("/{code}")
    public Baggage updateBaggageStatus(@PathVariable("code") String code){
        Baggage baggage = baggageRepository.findByCode(code);
        if(baggage == null)
            throw new ResourceNotFoundException("Baggage does not exists");

        baggage.setStatus("LOADED");
        return this.baggageRepository.save(baggage);
    }
}
