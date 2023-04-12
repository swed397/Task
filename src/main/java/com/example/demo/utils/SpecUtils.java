package com.example.demo.utils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Locale;

public class SpecUtils {

    public static Predicate textLike(Root<?> root, CriteriaBuilder builder, String column, String value) {
        if (value != null) {
            return builder.like(builder.lower(root.get(column)), "%" + value.toLowerCase(Locale.ROOT) + "%");
        } else {
            return null;
        }
    }

    public static Predicate textLike(Join<?, ?> root, CriteriaBuilder builder, String column, String value) {
        if (value != null) {
            return builder.like(builder.lower(root.get(column)), "%" + value.toLowerCase(Locale.ROOT) + "%");
        } else {
            return null;
        }
    }

    public static Predicate equalsYear(Root<?> root, CriteriaBuilder builder, String column, Integer value) {
        if (value != null) {
            return builder.equal(builder.function("YEAR", Integer.class, root.get(column)), value);
        } else {
            return null;
        }
    }
}
