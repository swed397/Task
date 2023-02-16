package com.example.demo.entities;

import com.example.demo.utils.Codable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "movie_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Transient
    private MovieType.Code code;

    public MovieType.Code getCode() {
        if (code == null) {
            return Codable.find(MovieType.Code.class, name.toLowerCase());
        } else {
            return code;
        }
    }

    public void setCode(MovieType.Code code) {
        this.code = code;
    }

    public enum Code implements Codable {
        FULL("полнометражный"),
        SHORT("короткометражный"),
        SERIAL("сериал");

        private String code;

        Code(String code) {
            this.code = code;
        }

        @Override
        public String getCode() {
            return code;
        }
    }
}
