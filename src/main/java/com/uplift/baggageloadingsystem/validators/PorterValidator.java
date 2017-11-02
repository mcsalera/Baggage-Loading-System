package com.uplift.baggageloadingsystem.validators;

import com.uplift.baggageloadingsystem.domain.Porter;
import com.uplift.baggageloadingsystem.forms.PorterForm;
import com.uplift.baggageloadingsystem.repository.LoadingBayRepository;
import com.uplift.baggageloadingsystem.repository.PorterRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class PorterValidator implements Validator {

    private PorterRepository porterRepository;
    private LoadingBayRepository loadingBayRepository;

    PorterValidator(PorterRepository porterRepository, LoadingBayRepository loadingBayRepository){
        this.porterRepository = porterRepository;
        this.loadingBayRepository = loadingBayRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PorterForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PorterForm form = (PorterForm) o;
        if(form.getId() != null){
            Porter porter = porterRepository.findOne(form.getId());
            if(porter == null)
                errors.reject("", "Porter does not exists");
        }
        else if(form.getPorterNumber() != null && porterRepository.findByPorterNumber(form.getPorterNumber()) != null){
            errors.reject("", "Porter number already exists");
        }
        if(form.getLoadingBayIds() != null){
            boolean notExists = form.getLoadingBayIds().stream().anyMatch(id -> loadingBayRepository.findOne(id) == null);
            if(notExists)
                errors.reject("", "A provided loading bay does not exist");
        }
    }
}
