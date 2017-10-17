package com.uplift.baggageloadingsystem.api;

import com.uplift.baggageloadingsystem.domain.Baggage;
import com.uplift.baggageloadingsystem.domain.Passenger;
import com.uplift.baggageloadingsystem.forms.PassengerForm;
import com.uplift.baggageloadingsystem.repository.PassengerRepository;
import com.uplift.baggageloadingsystem.service.PassengerService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    private PassengerRepository passengerRepository;
    private PassengerService passengerService;

    PassengerController(PassengerRepository passengerRepository, PassengerService passengerService) {
        this.passengerRepository = passengerRepository;
        this.passengerService = passengerService;
    }

    @GetMapping
    public List<Passenger> getPassenger(){
        return passengerRepository.findAll();
    }

    @PostMapping
    public PassengerForm createPassenger(@RequestBody PassengerForm form){
        return passengerService.createPassenger(form);
    }

    @PutMapping
    public Passenger updatePassenger(){
        return null;
    }

    @GetMapping("/{code}")
    public Passenger getPassengerByCode(@PathVariable("code") String code) {
        return this.passengerRepository.findByCode(code);
    }

    @GetMapping("/{code}/baggage")
    public Collection<Baggage> getPassengerBaggage(@PathVariable("code") String code) {
        return passengerService.getPassengerBaggage(code);
    }
}
