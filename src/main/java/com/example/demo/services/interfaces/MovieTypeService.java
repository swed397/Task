package com.example.demo.services.interfaces;

import com.example.demo.entities.MovieType;

import java.util.List;

public interface MovieTypeService {

    List<MovieType> findAll();

    MovieType findTypeByName(String name);

    boolean checkMovieTypeExisting(String type);
}
