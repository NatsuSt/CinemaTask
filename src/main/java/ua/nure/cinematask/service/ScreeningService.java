package ua.nure.cinematask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.nure.cinematask.model.Movie;
import ua.nure.cinematask.model.Screening;
import ua.nure.cinematask.repository.MovieRepository;
import ua.nure.cinematask.repository.ScreeningRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ScreeningService(ScreeningRepository screeningRepository, MovieRepository movieRepository) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
    }

    public List<Screening> getScreening() {
        return screeningRepository.findAll();
    }

    public Optional<Screening> getScreeningById(long id) {
        return screeningRepository.findById(id);
    }

    public Screening createScreening(Screening screening) {
        Movie movie = movieRepository.findById(screening.getMovieId().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie ID not found"));

        screening.setMovieId(movie);
        return screeningRepository.save(screening);
    }
}
