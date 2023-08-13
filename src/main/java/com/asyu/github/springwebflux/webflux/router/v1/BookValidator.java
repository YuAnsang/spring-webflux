package com.asyu.github.springwebflux.webflux.router.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Component
public class BookValidator<T>{

    private final Validator validator;

    public void validate(T body) {
        Errors errors = new BeanPropertyBindingResult(body, body.getClass().getName());
        validator.validate(body, errors);
        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getAllErrors().toString());
        }
    }

}
