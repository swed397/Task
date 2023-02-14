package com.example.demo.services;

import com.example.demo.entities.Movie;
import com.example.demo.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MovieService {

    public final MovieRepository repository;

    @Autowired
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Movie save(Movie movie) {

        return repository.save(movie);
    }

    public List<Movie> findAllByName(String name) {

        return repository.findByNameContaining(name);
    }
}
