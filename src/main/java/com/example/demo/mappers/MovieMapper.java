package com.example.demo.mappers;

import com.example.demo.dtos.MovieCrateDto;
import com.example.demo.dtos.MovieDto;
import com.example.demo.entities.Movie;
import com.example.demo.entities.MovieType;
import com.example.demo.services.MovieTypeService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class MovieMapper {

    @Autowired
    public MovieTypeService movieTypeService;


    @Mapping(source = "type", target = "type", qualifiedByName = "typeToStr")
    public abstract MovieDto toDto(Movie source);

    @Mapping(source = "type", target = "type", qualifiedByName = "strToType")
    public abstract Movie toEntity(MovieCrateDto source);

    @Named( value = "typeToStr")
    protected String typeToStr(MovieType type) {
        return type.getName();
    }

    @Named( value = "strToType")
    protected MovieType strToType(String type) {
        return movieTypeService.findTypeByName(type);
    }

}
