package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.entities.MovieSession;
import fr.iut.aluilcine.repositories.MovieSessionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Date;
import java.util.Optional;
import java.time.ZoneId;
import java.util.ArrayList;

import java.time.format.DateTimeParseException;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/moviesessions")
public class MovieSessionController extends BaseController<MovieSession, MovieSessionRepository> {

    /*
    @GetMapping("")
    public ResponseEntity<List<MovieSession>> getAll(
            @RequestParam("cinema_id") String cinemaId, 
            @RequestParam("movie_id" String movieId)
            )
    {
        
    }
    */
    
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
    
    private ZoneId defaultZoneId = ZoneId.systemDefault();
    
    @GetMapping("/condition")
    public ResponseEntity<?> getAllFromDay(@RequestParam("date") String day)
    {
        try {
            LocalDate local = LocalDate.parse(day,formatter);
        
            Date jourDebut = Date.from(local.atStartOfDay(defaultZoneId).toInstant());
            Date jourFin = Date.from(local.minusDays(-1).atStartOfDay(defaultZoneId).toInstant());
            
            List<MovieSession> moviesessions = repository.findAll();
            List<MovieSession> toSend = new ArrayList<>();
            
            for (MovieSession moviesession : moviesessions)
            {
                Date dateSchedule = moviesession.getDate_schedule();
                
                if (!dateSchedule.before(jourDebut) && !dateSchedule.after(jourFin))
                {
                    toSend.add(moviesession);
                }
            }
            
            return new ResponseEntity<>(toSend, OK);
        } 
        catch (DateTimeParseException e)
        {
            return new ResponseEntity<>(e.getMessage(), UNPROCESSABLE_ENTITY);
        }
    }
}
