package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.entities.Cinema;
import fr.iut.aluilcine.entities.Movie;
import fr.iut.aluilcine.entities.MovieSession;
import fr.iut.aluilcine.repositories.CinemaRepository;
import fr.iut.aluilcine.repositories.MovieSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/moviesessions")
public class MovieSessionController extends BaseController<MovieSession, MovieSessionRepository> {

    @Autowired
    private CinemaRepository cinemaRepository;
    
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
    
    private ZoneId defaultZoneId = ZoneId.systemDefault();

    @GetMapping("/condition")
    public ResponseEntity<?> getAll(@RequestParam(value = "date", required = false) String day,
                                    @RequestParam(value = "cinema_id", required = false) String cinemaId,
                                    @RequestParam(value = "movie_id", required = false) String movieId)
    {
        try {
            List<MovieSession> moviesessions = repository.findAll();

            if (day != null && !day.isBlank()) {
                LocalDate local = LocalDate.parse(day, formatter);

                Date jourDebut = Date.from(local.atStartOfDay(defaultZoneId).toInstant());
                Date jourFin = Date.from(local.minusDays(-1).atStartOfDay(defaultZoneId).toInstant());

                moviesessions.removeIf(movieSession ->
                        movieSession.getDate_schedule().before(jourDebut) ||
                        movieSession.getDate_schedule().after(jourFin));
            }

            if (cinemaId != null && !cinemaId.isBlank()) {
                Optional<Cinema> optCinema = cinemaRepository.findById(cinemaId);

                // s'il n'y a pas de cinema, alors on renvoit un tableau vide
                if (optCinema.isEmpty()) {
                    return new ResponseEntity<>("[]",NOT_FOUND);
                }

                moviesessions.removeIf(movieSession -> !movieSession.getCinema_id().equals(cinemaId));
            }

            if (movieId != null && !movieId.isBlank()) {
                Optional<Movie> optMovie = Optional.empty(); // MovieRepository.findById(movie_id);

                if (optMovie.isEmpty()) {
                    return new ResponseEntity<>("[]", NOT_FOUND);
                }

                moviesessions.removeIf(movieSession -> !movieSession.getMovie_id().equals(movieId));
            }

            return new ResponseEntity<>(moviesessions, OK);
        } 
        catch (DateTimeParseException e)
        {
            return new ResponseEntity<>(e.getMessage(), UNPROCESSABLE_ENTITY);
        }
    }
}
