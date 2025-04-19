package org.example.moviemanagmentbackend.service;

import org.example.moviemanagmentbackend.entity.MovieRating;
import org.example.moviemanagmentbackend.entity.User;
import org.example.moviemanagmentbackend.repository.MovieRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieRatingService {
    @Autowired
    private MovieRatingRepository movieRatingRepository;

    public MovieRating rateMovie(User user, String movieId, Integer rating, String review) {
        MovieRating movieRating = movieRatingRepository
                .findByUser_IdAndMovie_ImdbId(user.getId(), movieId)
                .orElse(new MovieRating());

        movieRating.setUser(user);
        movieRating.setRating(rating);
        movieRating.setReview(review);

        return movieRatingRepository.save(movieRating);
    }
}