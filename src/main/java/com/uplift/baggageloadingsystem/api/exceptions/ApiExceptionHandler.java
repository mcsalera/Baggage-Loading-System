package com.uplift.baggageloadingsystem.api.exceptions;

import com.uplift.baggageloadingsystem.utils.Utility;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handle(MethodArgumentNotValidException exception) {
        return error(Stream
                        .concat(
                            exception
                                    .getBindingResult()
                                    .getFieldErrors()
                                    .stream()
                                    .map(e -> String.format("%s %s", Utility.formatFieldName(e.getField()), e.getDefaultMessage())),
                            exception
                                .getBindingResult()
                                .getGlobalErrors()
                                .stream()
                                .map(ObjectError::getDefaultMessage)
                        )
                        .collect(Collectors.toList()));
    }
    private Map error(Object message) {
        return Collections.singletonMap("error", message);
    }
}
