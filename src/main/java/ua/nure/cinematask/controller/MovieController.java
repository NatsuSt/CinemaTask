package ua.nure.cinematask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.cinematask.model.Movie;
import ua.nure.cinematask.service.MovieService;

import java.util.List;
import java.util.Optional;

@RestController()
public class MovieController {

    private MovieService movieService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<Movie> getMovies() {
        logger.info("getMovies called");
        return movieService.getMovies();
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable(value = "id") long id) {
        logger.info("getMovieById called");
        return movieService.getMovieById(id).map(movie ->
                new ResponseEntity<>(movie, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/movies")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        logger.info("createMovie called");
        return new ResponseEntity<>(movieService.createMovie(movie), HttpStatus.CREATED);
    }
}
