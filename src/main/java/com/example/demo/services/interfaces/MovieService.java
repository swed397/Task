package com.example.demo.services.interfaces;

import com.example.demo.dtos.FiltersDto;
import com.example.demo.entities.Movie;

import java.util.List;

public interface MovieService {

    Movie save(Movie movie);

    List<Movie> findAllByName(String name);

    List<Movie> findAllByType(String type);

    List<Movie> findAllByYear(Integer year);

    List<Movie> findAll(int page, int size);

    List<Movie> findAllByAllParams(FiltersDto filtersDto);
}
