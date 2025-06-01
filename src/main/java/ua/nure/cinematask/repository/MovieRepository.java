package ua.nure.cinematask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.cinematask.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
