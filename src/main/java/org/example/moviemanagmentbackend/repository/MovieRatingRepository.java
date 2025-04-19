package org.example.moviemanagmentbackend.repository;

import org.example.moviemanagmentbackend.entity.MovieRating;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MovieRatingRepository extends JpaRepository<MovieRating, Long> {
    Optional<MovieRating> findByUser_IdAndMovie_ImdbId(Long userId, String movieId);
}