package com.example.demo.services;

import com.example.demo.entities.Movie;
import com.example.demo.entities.MovieType;
import com.example.demo.repositories.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class MovieServiceTest {

    @Autowired
    public MovieService movieService;
    @MockBean
    public MovieRepository movieRepository;

    @BeforeEach
    public void setUp() {
        Mockito.when(movieRepository.save(Mockito.any(Movie.class)))
                .thenReturn(new Movie(1L, "Test film",
                        "TEST", new MovieType(), "TEST", LocalDate.now()));

        Mockito.when(movieRepository.findAll())
                .thenReturn(Arrays.asList(new Movie(), new Movie(), new Movie()));

    }

    @Test
    void saveTest() {
        Movie movie = new Movie();
        Movie savedMovie = movieService.save(movie);

        Assertions.assertNotNull(savedMovie);
        Assertions.assertNotNull(savedMovie.getId());
    }

    @Test
    void findAllByNameTest() {
        ArrayList<Movie> movieArrayList = movieRepository.findAllByNameContainingIgnoreCase("");

        Assertions.assertNotNull(movieArrayList);
        Assertions.assertEquals(movieArrayList.size(), 0);
    }

    @Test
    void findAllByTypeTest() {
        ArrayList<Movie> movieArrayList = movieRepository.findAllByType_NameIgnoreCase("");

        Assertions.assertNotNull(movieArrayList);
        Assertions.assertEquals(movieArrayList.size(), 0);
    }

    @Test
    void findAllByYearTest() {
        ArrayList<Movie> movieArrayList = movieRepository.findAllByYear(2018);

        Assertions.assertNotNull(movieArrayList);
        Assertions.assertEquals(movieArrayList.size(), 0);
    }

    @Test
    void findAll() {
        List<Movie> movieArrayList = movieRepository.findAll();

        Assertions.assertNotNull(movieArrayList);
        Assertions.assertEquals(movieArrayList.size(), 3);
    }
}