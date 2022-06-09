package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.entities.Cinema;
import fr.iut.aluilcine.entities.Movie;
import fr.iut.aluilcine.entities.MovieSession;
import fr.iut.aluilcine.repositories.CinemaRepository;
import fr.iut.aluilcine.repositories.MovieSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
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
        // optimisation en retardant le plus l'appel à repository findAll
        // sauf dans le cas particulier où les paramètres de la requête sont null
        try {
            if (day == null && cinemaId == null && movieId == null)
                return new ResponseEntity<>(repository.findAll(), OK);

            boolean useCinemaId = false, useMovieId = false;

            if (cinemaId != null && !cinemaId.isBlank()) {
                Optional<Cinema> optCinema = cinemaRepository.findById(cinemaId);

                // s'il n'y a pas de cinema, alors on renvoit un tableau vide
                if (optCinema.isEmpty()) {
                    return new ResponseEntity<>("[]",NOT_FOUND);
                }

                useCinemaId = true;
            }

            if (movieId != null && !movieId.isBlank()) {
                Optional<Movie> optMovie = Optional.empty(); // MovieRepository.findById(movie_id);

                // pareil : s'il n'y a pas de Movie, alors on renvoit un tableau vide
                if (optMovie.isEmpty()) {
                    return new ResponseEntity<>("[]", NOT_FOUND);
                }

                useMovieId = true;
            }

            List<MovieSession> moviesessions = repository.findAll();

            if (useCinemaId)
                moviesessions.removeIf(movieSession -> !movieSession.getCinema_id().equals(cinemaId));

            if (useMovieId)
                moviesessions.removeIf(movieSession -> !movieSession.getMovie_id().equals(movieId));

            if (day != null && !day.isBlank()) {
                LocalDate local = LocalDate.parse(day, formatter);

                Date jourDebut = Date.from(local.atStartOfDay(defaultZoneId).toInstant());
                Date jourFin = Date.from(local.minusDays(-1).atStartOfDay(defaultZoneId).toInstant());

                moviesessions.removeIf(movieSession ->
                        movieSession.getDate_schedule().before(jourDebut) ||
                        movieSession.getDate_schedule().after(jourFin));
            }

            return new ResponseEntity<>(moviesessions, OK);
        } 
        catch (DateTimeParseException e)
        {
            return new ResponseEntity<>(e.getMessage(), UNPROCESSABLE_ENTITY);
        }
    }
}
