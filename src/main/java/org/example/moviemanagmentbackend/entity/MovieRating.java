package org.example.moviemanagmentbackend.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "movie_ratings")
@Data
public class MovieRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @Column(nullable = false)
    private Integer rating;

    @Column(length = 1000)
    private String review;
}