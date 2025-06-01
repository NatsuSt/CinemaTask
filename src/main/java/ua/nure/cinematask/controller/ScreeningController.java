package ua.nure.cinematask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.cinematask.model.Screening;
import ua.nure.cinematask.service.ScreeningService;

import java.util.List;
import java.util.Optional;

@RestController
public class ScreeningController {

    private ScreeningService screeningService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public void setScreeningService(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @GetMapping("/screenings")
    public List<Screening> getScreenings() {
        logger.info("getScreenings called");
        return screeningService.getScreening();
    }

    @GetMapping("/screenings/{id}")
    public ResponseEntity<?> getScreening(@PathVariable int id) {
        Optional<Screening> screening = screeningService.getScreeningById(id);
        logger.info("getScreening called");
        if (screening.isPresent()) {
            return ResponseEntity.ok(screening.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/screenings")
    public Screening createScreening(@RequestBody Screening screening) {
        logger.info("createScreening called");
        return screeningService.createScreening(screening);
    }
}
