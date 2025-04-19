package org.example.moviemanagmentbackend.controller;

import org.example.moviemanagmentbackend.entity.Movie;
import org.example.moviemanagmentbackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/movies")
public class AdminController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/{imdbId}")
    public ResponseEntity<Movie> addMovie(@PathVariable String imdbId) {
        return ResponseEntity.ok(movieService.addMovie(imdbId));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Movie>> addMovies(@RequestBody List<String> imdbIds) {
        return ResponseEntity.ok(imdbIds.stream()
                .map(movieService::addMovie)
                .collect(java.util.stream.Collectors.toList()));
    }

    @DeleteMapping("/{imdbId}")
    public ResponseEntity<Void> removeMovie(@PathVariable String imdbId) {
        movieService.removeMovie(imdbId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/batch")
    public ResponseEntity<Void> removeMovies(@RequestBody List<String> imdbIds) {
        imdbIds.forEach(movieService::removeMovie);
        return ResponseEntity.ok().build();
    }

}
