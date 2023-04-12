package com.example.demo.repositories;

import com.example.demo.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, PagingAndSortingRepository<Movie, Long>,
        JpaSpecificationExecutor<Movie> {

    ArrayList<Movie> findAllByNameContainingIgnoreCase(String name);

    ArrayList<Movie> findAllByType_NameIgnoreCase(String type);

    @Query(
            value = "select m from Movie m where year(m.date) = :#{#year}"
    )
    ArrayList<Movie> findAllByYear(@Param("year") Integer year);
}
