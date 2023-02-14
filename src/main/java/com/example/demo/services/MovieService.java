package com.example.demo.services;

import com.example.demo.entities.Movie;
import com.example.demo.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MovieService {
    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

    public final MovieRepository repository;

    @Autowired
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Movie save(Movie movie) {
        Movie savedMovie = repository.save(movie);
        if (savedMovie.getId() != null) {
            logger.info("Successfully saved new movie");
        }
        return repository.save(movie);
    }

    public List<Movie> findAllByName(String name) {
        return repository.findAllByNameContainingIgnoreCase(name);
    }

    public List<Movie> findAllByType(String type) {
        return repository.findAllByTypeIgnoreCase(type);
    }

    public List<Movie> findAllByYear(Integer year) {
        return repository.findByYear(year);
    }

    public List<Movie> findAll() {
        return repository.findAll();
    }
}
