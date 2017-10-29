package com.uplift.baggageloadingsystem.api;

import com.uplift.baggageloadingsystem.domain.Baggage;
import com.uplift.baggageloadingsystem.domain.Passenger;
import com.uplift.baggageloadingsystem.forms.PassengerForm;
import com.uplift.baggageloadingsystem.repository.PassengerRepository;
import com.uplift.baggageloadingsystem.service.PassengerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {

    private PassengerRepository passengerRepository;
    private PassengerService passengerService;
    private Validator validator;

    PassengerController(Validator validator, PassengerRepository passengerRepository, PassengerService passengerService) {
        this.passengerRepository = passengerRepository;
        this.passengerService = passengerService;
    }

    @GetMapping
    public List<Passenger> getPassenger(){
        return passengerRepository.findAll();
    }

    @PostMapping
    public PassengerForm createPassenger(@Validated(PassengerForm.Create.class) @RequestBody PassengerForm form){
        SpringValidatorAdapter v = new SpringValidatorAdapter(validator);
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(form, "order");
//        v.validate(order, errors, FinalChecks.class);
        return passengerService.createPassenger(form);
    }

    @PutMapping
    public PassengerForm updatePassenger(@RequestBody PassengerForm form){
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
        //passenger.setStatus("BOARDED")
        return passengerRepository.save(passenger);
    }

    @GetMapping("/{code}/baggage")
    public Collection<Baggage> getPassengerBaggage(@PathVariable("code") String code) {
        return passengerService.getPassengerBaggage(code);
    }
}
