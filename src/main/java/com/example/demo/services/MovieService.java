package com.example.demo.services;

import com.example.demo.entities.Movie;
import com.example.demo.repositories.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
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
        Movie savedMovie = repository.save(movie);
        if (savedMovie.getId() != null) {
            log.info("Successfully saved new movie");
        }
        return repository.save(movie);
    }

    public List<Movie> findAllByName(String name) {
        return repository.findAllByNameContainingIgnoreCase(name);
    }

    public List<Movie> findAllByType(String type) {
        if (type == null) {
            return null;
        }
        return repository.findAllByType_NameIgnoreCase(type);
    }

    public List<Movie> findAllByYear(Integer year) {
        return repository.findAllByYear(year);
    }

    public List<Movie> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).getContent();
    }
}
