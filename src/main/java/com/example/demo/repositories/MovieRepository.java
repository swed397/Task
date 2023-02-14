package com.example.demo.repositories;

import com.example.demo.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    ArrayList<Movie> findByNameContaining(String name);
}
