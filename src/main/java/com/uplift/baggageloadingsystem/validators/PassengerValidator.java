package com.uplift.baggageloadingsystem.validators;

import com.uplift.baggageloadingsystem.forms.PassengerForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class PassengerValidator implements ConstraintValidator{


    @Override
    public void initialize(Annotation annotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
