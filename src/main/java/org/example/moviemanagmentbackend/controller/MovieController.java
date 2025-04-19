package org.example.moviemanagmentbackend.controller;
import org.example.moviemanagmentbackend.entity.Movie;
import org.example.moviemanagmentbackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<Page<Movie>> getAllMovies(Pageable pageable) {
        return ResponseEntity.ok(movieService.getAllMovies(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Movie>> searchMovies(
            @RequestParam String title,
            Pageable pageable) {
        return ResponseEntity.ok(movieService.searchMovies(title, pageable));
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Movie> getMovieDetails(@PathVariable String imdbId) {
        return ResponseEntity.ok(movieService.getMovieDetails(imdbId));
    }
}