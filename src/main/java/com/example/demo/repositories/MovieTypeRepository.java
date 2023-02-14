package com.example.demo.repositories;

import com.example.demo.entities.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieTypeRepository extends JpaRepository<MovieType, Long> {

    Optional<MovieType> getByName(String name);
}
