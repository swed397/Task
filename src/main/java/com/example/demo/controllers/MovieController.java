package com.example.demo.controllers;

import com.example.demo.dtos.MovieCrateDto;
import com.example.demo.dtos.MovieDto;
import com.example.demo.entities.Movie;
import com.example.demo.mappers.MovieMapper;
import com.example.demo.services.MovieService;
import io.swagger.annotations.Api;
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
@Api
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
        Movie movie = service.save(movieMapper.toEntity(movieCrateDto));

        return ResponseEntity.ok(movieMapper.toDto(movie));
    }

    @PostMapping("/addAll")
    public ResponseEntity saveAll(@RequestBody @Valid MovieCrateDto[] movieCrateDtos) {

        Arrays.stream(movieCrateDtos).forEach(o -> {
                    Movie movie = movieMapper.toEntity(o);
                    service.save(movie);
                }
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/findByName{name}")
    public ResponseEntity<List<MovieDto>> findById(@PathVariable String name) {
        return ResponseEntity.ok(service.findAllByName(name).stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList()));
    }
}
