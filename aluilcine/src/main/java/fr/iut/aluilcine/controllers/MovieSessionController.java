package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.entities.MovieSession;
import fr.iut.aluilcine.repositories.MovieSessionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/moviesessions")
public class MovieSessionController extends BaseController<MovieSession, MovieSessionRepository> {

    @GetMapping("")
    public ResponseEntity<List<MovieSession>> getAll() {
        try {
            return new ResponseEntity<>(repository.findAll(), OK);
        }catch (Exception e){
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieSession> getOneById(@PathVariable("id") String id) {
        try {
            Optional<MovieSession> opt = repository.findById(id);
            if (opt.isEmpty()){
                return new ResponseEntity<>(NOT_FOUND);
            }
            return new ResponseEntity<>(opt.get(), OK);
        }catch (Exception e){
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    @PostMapping("")
    public ResponseEntity<?> post(@RequestBody MovieSession movieSession) {
        try {

            Optional<String> optValidation = validateEntity(movieSession);
            if (optValidation.isPresent()){
                return new ResponseEntity<>(optValidation.get(), UNPROCESSABLE_ENTITY);
            }

            return new ResponseEntity<>(repository.save(movieSession), CREATED);
        } catch (Exception e) {
            customLogError(e.getMessage());
        }

        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable("id") String id, @RequestBody MovieSession movieSession) {
        try {
            Optional<String> optValidation = validateEntity(movieSession);
            if (optValidation.isPresent()) {
                return new ResponseEntity<>(optValidation.get(), UNPROCESSABLE_ENTITY);
            }

            Optional<MovieSession> opt = repository.findById(id);
            if (opt.isEmpty()) {
                return new ResponseEntity<>(NOT_FOUND);
            }

            MovieSession movieSession1 = opt.get();
            movieSession1.setMovie_id(movieSession.getMovie_id());
            movieSession1.setCinema_id(movieSession.getCinema_id());
            movieSession1.setDate_schedule(movieSession.getDate_schedule());
            movieSession1.setSchema_version(movieSession.getSchema_version());

            return new ResponseEntity<>(repository.save(movieSession1), OK);
        } catch (Exception e) {
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(NO_CONTENT);
        } catch (Exception e) {
            customLogError(e.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
