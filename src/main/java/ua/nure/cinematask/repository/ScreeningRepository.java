package ua.nure.cinematask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.cinematask.model.Screening;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {

}
