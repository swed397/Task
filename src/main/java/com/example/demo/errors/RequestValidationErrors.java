package com.example.demo.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestValidationErrors {
    private List<FieldValidationErrorDto> fieldValidationErrorDtoList;
}
