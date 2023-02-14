package com.example.demo.repositories;

import com.example.demo.entities.Movie;
import com.example.demo.services.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MovieRepositoryTest {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieRepository movieRepository;

    @Test
    void findAllByNameContainingIgnoreCaseTest() {
        List<Movie> movieArrayList = movieService.findAllByName("Термин");

        Assertions.assertNotNull(movieArrayList);
        Assertions.assertEquals(movieArrayList.size(), 1);
        Assertions.assertEquals(movieArrayList.get(0).getName(), "Терминатор");
    }

    @Test
    void findAllByTypeIgnoreCaseTest() {
        List<Movie> movieArrayList1 = movieService.findAllByType("Сериал");
        List<Movie> movieArrayList2 = movieService.findAllByType("сериал");

        Assertions.assertNotNull(movieArrayList1);
        Assertions.assertEquals(movieArrayList1.size(), 2);
        Assertions.assertNotNull(movieArrayList2);
        Assertions.assertEquals(movieArrayList2.size(), 2);
        Assertions.assertEquals(movieArrayList1, movieArrayList2);
        movieArrayList1.forEach(o -> Assertions.assertEquals(o.getType(), "Сериал"));
        movieArrayList2.forEach(o -> Assertions.assertEquals(o.getType(), "Сериал"));
    }

    @Test
    void findByYearTest() {
        List<Movie> movieArrayList = movieService.findAllByYear(2018);

        Assertions.assertNotNull(movieArrayList);
        Assertions.assertEquals(movieArrayList.size(), 1);
        Assertions.assertEquals(movieArrayList.get(0).getDate().getYear(), 2018);
    }

    @Test
    void findAllTest() {
        List<Movie> movieList = movieService.findAll();

        Assertions.assertNotNull(movieList);
        Assertions.assertEquals(movieList.size(), 8);
    }
}