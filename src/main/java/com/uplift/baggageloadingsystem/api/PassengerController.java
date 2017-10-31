package com.uplift.baggageloadingsystem.api;

import com.uplift.baggageloadingsystem.domain.Baggage;
import com.uplift.baggageloadingsystem.domain.Passenger;
import com.uplift.baggageloadingsystem.forms.PassengerForm;
import com.uplift.baggageloadingsystem.repository.  PassengerRepository;
import com.uplift.baggageloadingsystem.service.PassengerService;
import com.uplift.baggageloadingsystem.validators.PassengerValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {

    private PassengerRepository passengerRepository;
    private PassengerService passengerService;
    private PassengerValidator passengerValidator;

    PassengerController(PassengerRepository passengerRepository, PassengerService passengerService,
                        PassengerValidator passengerValidator) {
        this.passengerRepository = passengerRepository;
        this.passengerService = passengerService;
        this.passengerValidator = passengerValidator;
    }

    @InitBinder("passengerForm")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(passengerValidator);
    }

    @GetMapping
    public List<Passenger> getPassenger(){
        return passengerRepository.findAll();
    }

    @PostMapping
    public PassengerForm createPassenger(@Validated(PassengerForm.Create.class) @RequestBody PassengerForm passengerForm){
        return passengerService.createPassenger(passengerForm);
    }

    @PutMapping
    public PassengerForm updatePassenger(@Validated(PassengerForm.Update.class) @RequestBody PassengerForm form){
        return passengerService.updatePassenger(form);
    }

    @GetMapping("/{code}")
    public Passenger getPassenger(@PathVariable("code") String code) {
        return StringUtils.isNumeric(code)? passengerRepository.findOne(Integer.valueOf(code)) :
                passengerRepository.findByCode(code);

    }

    @PutMapping("/{code}")
    public Passenger scanPassenger(@PathVariable("code") String code) {
        Passenger passenger = StringUtils.isNumeric(code)? passengerRepository.findOne(Integer.valueOf(code)) :
                passengerRepository.findByCode(code);
        passenger.setStatus("BOARDED");
        return passengerRepository.save(passenger);
    }

    @GetMapping("/{code}/baggage")
    public Collection<Baggage> getPassengerBaggage(@PathVariable("code") String code) {
        return passengerService.getPassengerBaggage(code);
    }
}
