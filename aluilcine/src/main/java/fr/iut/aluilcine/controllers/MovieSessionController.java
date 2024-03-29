package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.entities.MovieSession;
import fr.iut.aluilcine.repositories.MovieSessionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

/**
 * CinemaController qui permet la gestion des {@link MovieSession movie session}.
 */
@RestController
@RequestMapping("/movieSessions")
public class MovieSessionController extends BaseController<MovieSession, MovieSessionRepository> {
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
    
    private final ZoneId defaultZoneId = ZoneId.systemDefault();

    /**
     * Obtenir tout les horaires de film avec certaines conditions préalable
     * @param day le jour de l'horaire au format (dd-MM-yyy) par exemple (23-08-2019)
     * @param cinemaId l'identifiant du cinema
     * @param movieId l'identifiant du film
     * @return Une ResponseEntity :
     *                             - Une List de Cinema en cas de succès
     *                             - Un message d'erreur dans le cas d'une DateTimeParseException
     */
    @GetMapping("/condition")
    public ResponseEntity<?> getAll(@RequestParam(value = "date", required = false) String day,
                                    @RequestParam(value = "cinemaId", required = false) String cinemaId,
                                    @RequestParam(value = "movieId", required = false) String movieId)
    {
        // optimisation en retardant le plus l'appel à repository findAll
        // sauf dans le cas particulier où les paramètres de la requête sont null
        try {
            if (day == null && cinemaId == null && movieId == null)
                return new ResponseEntity<>(repository.findAll(), OK);

            if (day == null) {

                // si la requête demande que selon le cinemaID
                if (movieId == null)
                    return new ResponseEntity<>(repository.findByCinemaId(cinemaId), OK);

                // Si la requête demande que selon le movieID
                if (cinemaId == null)
                    return new ResponseEntity<>(repository.findByMovieId(movieId), OK);
            }

            boolean useCinemaId = cinemaId != null && !cinemaId.isBlank();
            boolean useMovieId = (movieId != null && !movieId.isBlank());

            List<MovieSession> moviesessions = null;

            if (useMovieId && useCinemaId) {
                moviesessions = repository.findByCinemaIdAndMovieId(cinemaId, movieId);
            }

            else if (useMovieId) {
                moviesessions = repository.findByMovieId(movieId);
            }

            else if (useCinemaId) {
                moviesessions = repository.findByCinemaId(cinemaId);
            }

            else moviesessions = repository.findAll();

            if (day != null && !day.isBlank()) {
                LocalDate local = LocalDate.parse(day, formatter);

                Date jourDebut = Date.from(local.atStartOfDay(defaultZoneId).toInstant());
                Date jourFin = Date.from(local.minusDays(-1).atStartOfDay(defaultZoneId).toInstant());

                moviesessions.removeIf(movieSession ->
                        movieSession.getDateSchedule().before(jourDebut) ||
                        movieSession.getDateSchedule().after(jourFin));
            }

            return new ResponseEntity<>(moviesessions, OK);
        } 
        catch (DateTimeParseException e)
        {
            return new ResponseEntity<>(e.getMessage(), UNPROCESSABLE_ENTITY);
        }
    }
}
