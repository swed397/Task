package com.example.demo.services.impl;

import com.example.demo.entities.MovieType;
import com.example.demo.repositories.MovieTypeRepository;
import com.example.demo.services.interfaces.MovieTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieTypeServiceImpl implements MovieTypeService {

    public final MovieTypeRepository movieTypeRepository;

    @Autowired
    public MovieTypeServiceImpl(MovieTypeRepository movieTypeRepository) {
        this.movieTypeRepository = movieTypeRepository;
    }

    public boolean checkMovieTypeExisting(String type) {
        return movieTypeRepository.getByNameIgnoreCase(type).isPresent();
    }

    public MovieType findTypeByName(String name) {
        return movieTypeRepository.getByNameIgnoreCase(name).orElse(new MovieType());
    }

    public List<MovieType> findAll() {
        return movieTypeRepository.findAll();
    }
}
