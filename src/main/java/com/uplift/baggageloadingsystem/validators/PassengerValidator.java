package com.uplift.baggageloadingsystem.validators;

import com.uplift.baggageloadingsystem.domain.Passenger;
import com.uplift.baggageloadingsystem.forms.PassengerForm;
import com.uplift.baggageloadingsystem.repository.LoadingBayRepository;
import com.uplift.baggageloadingsystem.repository.PassengerRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class PassengerValidator implements Validator{

    private PassengerRepository passengerRepo;
    private LoadingBayRepository loadingBayRepo;

    PassengerValidator(PassengerRepository passengerRepo,
                       LoadingBayRepository loadingBayRepo) {
        this.passengerRepo = passengerRepo;
        this.loadingBayRepo = loadingBayRepo;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PassengerForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PassengerForm form = (PassengerForm) o;
        if(form.getLoadingBayId() != null && loadingBayRepo.findOne(form.getLoadingBayId()) == null)
            errors.reject("", "Loading bay does not exists");

        if(form.getId() != null)
        {
            Passenger passenger= passengerRepo.findOne(form.getId());
            if(passenger == null)
                errors.reject("", "Passenger does not exists");
            if(form.getBaggageWeight() != null && passenger.getBaggageWeight() > form.getBaggageWeight())
                errors.reject("", "Baggage weight should be greater than or equal to original value");
        }

    }
}
