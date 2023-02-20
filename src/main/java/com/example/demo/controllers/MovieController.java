package com.example.demo.controllers;

import com.example.demo.dtos.MovieCrateDto;
import com.example.demo.dtos.MovieDto;
import com.example.demo.entities.Movie;
import com.example.demo.mappers.MovieMapper;
import com.example.demo.services.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping()
public class MovieController {

    public final MovieService service;
    public final MovieMapper movieMapper;

    @Autowired
    public MovieController(MovieService service, MovieMapper movieMapper) {
        this.service = service;
        this.movieMapper = movieMapper;
    }


    @PostMapping("/add")
    public ResponseEntity save(@RequestBody @Valid MovieCrateDto movieCrateDto) {
        log.info("Saving new movie ...");
        Movie movie = service.save(movieMapper.toEntity(movieCrateDto));
        return ResponseEntity.ok(movieMapper.toDto(movie));
    }

    @PostMapping("/addAll")
    public ResponseEntity saveAll(@RequestBody @Valid MovieCrateDto[] movieCrateDtos) {
        log.info("Saving new list of movie, size of : " + movieCrateDtos.length);
        Arrays.stream(movieCrateDtos).forEach(o -> {
                    Movie movie = movieMapper.toEntity(o);
                    service.save(movie);
                }
        );

        log.info("Saving list of movies");

        return ResponseEntity.ok().build();
    }

    @GetMapping("/findByName{name}")
    public ResponseEntity<List<MovieDto>> findById(@PathVariable String name) {
        log.info("Getting movies by name : " + name);
        return ResponseEntity.ok(service.findAllByName(name).stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/findByType{type}")
    public ResponseEntity<List<MovieDto>> findByType(@PathVariable String type) {
        log.info("Getting movies by type : " + type);
        return ResponseEntity.ok(service.findAllByType(type).stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/findByYear{year}")
    public ResponseEntity<List<MovieDto>> findByYear(@PathVariable Integer year) {
        log.info("Getting movies by year : " + year);
        return ResponseEntity.ok(service.findAllByYear(year).stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/getAll&&page={page}&&size={size}")
    public ResponseEntity<List<MovieDto>> findAll(@PathVariable int page, @PathVariable int size) {
        log.info("Get all movies on page " + page);
        return ResponseEntity.ok(service.findAll(page, size).stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList()));
    }
}
