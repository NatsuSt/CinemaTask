package ua.nure.cinematask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class CinemaTaskApplication {

    public static void main(String[] args) {
//        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(CinemaTaskApplication.class, args);
    }

}
