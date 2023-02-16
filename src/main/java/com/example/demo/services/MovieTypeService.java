package com.example.demo.services;

import com.example.demo.entities.MovieType;
import com.example.demo.repositories.MovieTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieTypeService {

    public final MovieTypeRepository movieTypeRepository;

    @Autowired
    public MovieTypeService(MovieTypeRepository movieTypeRepository) {
        this.movieTypeRepository = movieTypeRepository;
    }

    public boolean checkMovieTypeExisting(String type) {
        return movieTypeRepository.getByName(type).isPresent();
    }

    public MovieType findTypeByName(String name) {
        return movieTypeRepository.getByName(name.toLowerCase()).orElse(new MovieType());
    }
}
