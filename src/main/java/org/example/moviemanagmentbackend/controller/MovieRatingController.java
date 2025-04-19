package org.example.moviemanagmentbackend.controller;

import org.example.moviemanagmentbackend.entity.MovieRating;
import org.example.moviemanagmentbackend.entity.User;
import org.example.moviemanagmentbackend.service.MovieRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies/{movieId}/ratings")
public class MovieRatingController {
    @Autowired
    private MovieRatingService movieRatingService;

    @PostMapping
    public ResponseEntity<MovieRating> rateMovie(
            @PathVariable String movieId,
            @RequestBody RatingRequest request,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(movieRatingService.rateMovie(
                user,
                movieId,
                request.getRating(),
                request.getReview()
        ));
    }
}

class RatingRequest {
    private Integer rating;
    private String review;

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }
}