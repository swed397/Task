package com.example.demo.dtos;

import com.example.demo.valid.ValidateMovieType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieCrateDto {

    @NotBlank(message = "Name is NOT NULL constraint")
    private String name;

    @NotBlank(message = "Comment is NOT NULL constraint")
    private String comment;

    @ValidateMovieType
    private String type;

    @NotBlank(message = "Genre is NOT NULL constraint")
    private String genre;

    @NotNull(message = "Date is NOT NULL constraint")
    private LocalDate date;
}
