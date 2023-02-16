package com.example.demo.mappers;

import com.example.demo.dtos.MovieCrateDto;
import com.example.demo.dtos.MovieDto;
import com.example.demo.entities.Movie;
import com.example.demo.entities.MovieType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(source = "type", target = "type", qualifiedByName = "typeToStr")
    MovieDto toDto(Movie source);

    Movie toEntity(MovieCrateDto source);

    @Named("typeToStr")
    private static String typeToStr(MovieType movieType) {
        return movieType.getName();
    }

}
