package com.example.demo.specs;

import com.example.demo.entities.Movie;
import com.example.demo.entities.MovieType;
import com.example.demo.utils.SpecUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class MovieSpec {

    private static Specification<Movie> yearLike(Integer year) {
        if (year != null) {
            return ((root, query, criteriaBuilder) -> SpecUtils.equalsYear(root, criteriaBuilder, "date", year));
        } else {
            return null;
        }
    }

    private static Specification<Movie> nameLike(String name) {
        if (name != null) {
            return ((root, query, criteriaBuilder) -> SpecUtils.textLike(root, criteriaBuilder, "name", name));
        } else {
            return null;
        }
    }

    private static Specification<Movie> typeIs(String type) {
        return (root, query, criteriaBuilder) -> {
            if (type != null) {
                Join<Movie, MovieType> movieTypeJoin = root.join("type");
                return SpecUtils.textLike(movieTypeJoin, criteriaBuilder, "name", type);
            } else {
                return null;
            }
        };
    }

    public static Specification<Movie> searchWithConditions(Integer year, String name, String type) {
        return Specification
                .where(yearLike(year))
                .and(nameLike(name))
                .and(typeIs(type));

    }

}
