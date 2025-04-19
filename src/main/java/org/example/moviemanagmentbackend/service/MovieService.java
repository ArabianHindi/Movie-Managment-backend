package org.example.moviemanagmentbackend.service;

import lombok.RequiredArgsConstructor;
import org.example.moviemanagmentbackend.entity.Movie;
import org.example.moviemanagmentbackend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final RestTemplate restTemplate;

    @Value("${omdb.api.url}")
    private String omdbApiUrl;

    @Value("${omdb.api.key}")
    private String omdbApiKey;

    public Page<Movie> searchMovies(String title, Pageable pageable) {
        return movieRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    public Page<Movie> getAllMovies(Pageable pageable) {
        return movieRepository.findAllMovies(pageable);
    }

    public Movie getMovieDetails(String imdbId) {
        return movieRepository.findByImdbId(imdbId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public Movie addMovie(String imdbId) {
        if (movieRepository.existsById(imdbId)) {
            throw new RuntimeException("Movie already exists");
        }

        Map<String, String> params = new HashMap<>();
        params.put("i", imdbId);
        params.put("apikey", omdbApiKey);

        ResponseEntity<Movie> response = restTemplate.getForEntity(
                omdbApiUrl + "?i={i}&apikey={apikey}",
                Movie.class,
                params
        );


        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            Movie movie = response.getBody();
            movie.setImdbId(imdbId);

            return movieRepository.save(movie);
        }
        throw new RuntimeException("Failed to fetch movie from OMDB API");
    }

    public void removeMovie(String imdbId) {
        movieRepository.deleteById(imdbId);
    }
}