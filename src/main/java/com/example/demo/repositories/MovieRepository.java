package com.example.demo.repositories;

import com.example.demo.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    ArrayList<Movie> findAllByNameContainingIgnoreCase(String name);

    ArrayList<Movie> findAllByTypeIgnoreCase(String type);

    @Query(
            nativeQuery = true,
            value = "select * from movies where id in (select id from movies where year(date) = :#{#year})"
    )
    ArrayList<Movie> findByYear(@Param("year") Integer year);
}
