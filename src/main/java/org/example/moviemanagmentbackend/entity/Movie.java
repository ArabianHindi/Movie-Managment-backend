package org.example.moviemanagmentbackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
@Data
public class Movie {
    @Id
    @Column(nullable = false, unique = true)
    @JsonProperty("imdbID")
    private String imdbId;

    @Column(nullable = false)
    @JsonProperty("Title")
    private String title;

    @Column(length = 1000)
    @JsonProperty("Plot")
    private String plot;

    @JsonProperty("Year")
    private String year;

    @JsonProperty("Director")
    private String director;

    @JsonProperty("Actors")
    private String actors;

    @JsonProperty("Poster")
    private String poster;

    @JsonProperty("imdbRating")
    private Double imdbRating;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<MovieRating> ratings = new HashSet<>();
}