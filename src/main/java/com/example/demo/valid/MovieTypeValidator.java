package com.example.demo.valid;

import com.example.demo.services.MovieTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class MovieTypeValidator implements ConstraintValidator<ValidateMovieType, String> {

    public final MovieTypeService service;

    @Autowired
    public MovieTypeValidator(MovieTypeService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(String type, ConstraintValidatorContext constraintValidatorContext) {
        return service.checkMovieTypeExisting(type.toLowerCase());
    }
}
