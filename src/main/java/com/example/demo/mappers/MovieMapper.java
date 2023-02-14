package com.example.demo.mappers;

import com.example.demo.dtos.MovieCrateDto;
import com.example.demo.dtos.MovieDto;
import com.example.demo.entities.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDto toDto(Movie source);

    Movie toEntity(MovieCrateDto source);
}
