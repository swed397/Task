package com.example.demo.services.impl;

import com.example.demo.dtos.FiltersDto;
import com.example.demo.entities.Movie;
import com.example.demo.repositories.MovieRepository;
import com.example.demo.services.interfaces.MovieService;
import com.example.demo.specs.MovieSpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class MovieServiceImp implements MovieService {

    public final MovieRepository repository;

    @Autowired
    public MovieServiceImp(MovieRepository repository) {
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

    @Override
    public List<Movie> findAllByAllParams(FiltersDto filtersDto) {
        return repository.findAll(MovieSpec.searchWithConditions(
                        filtersDto.getYear(), filtersDto.getName(), filtersDto.getType()),
                PageRequest.of(filtersDto.getPage(), filtersDto.getSize())).getContent();
    }
}
