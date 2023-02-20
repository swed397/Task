package com.example.demo.valid;

import com.example.demo.entities.MovieType;
import com.example.demo.services.MovieTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.stream.Collectors;

@Component
public class MovieTypeValidator implements ConstraintValidator<ValidateMovieType, String> {

    public final MovieTypeService service;


    @Autowired
    public MovieTypeValidator(MovieTypeService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(String type, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(String.format(
                "Invalid movie type. Available: %s", service.findAll().stream()
                        .map(MovieType::getName)
                        .collect(Collectors.joining("; "))) + "."
        ).addConstraintViolation();

        return service.checkMovieTypeExisting(type);
    }
}
