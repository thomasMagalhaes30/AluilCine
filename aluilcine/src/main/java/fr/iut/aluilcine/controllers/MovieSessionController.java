package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.entities.MovieSession;
import fr.iut.aluilcine.repositories.MovieSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/moviessession")
public class MovieSessionController {

    @Autowired
    MovieSessionRepository repository;

    @GetMapping("")
    public List<MovieSession> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieSession> getOneById(@PathVariable("id") String id) {
        Optional<MovieSession> opt = repository.findById(id);
        if (opt.isEmpty()){
            return new ResponseEntity<>(NOT_FOUND);
        }

        return new ResponseEntity<>(opt.get(), OK);
    }

    @PostMapping("")
    public ResponseEntity<MovieSession> post(@RequestBody MovieSession movieSession) {
        if (movieSession.getMovie_id() == null || movieSession.getCinema_id() == null || movieSession.getCinema_id().isEmpty() || movieSession.getMovie_id().isEmpty()) {
            return new ResponseEntity<>(UNPROCESSABLE_ENTITY);
        }

        try {
            MovieSession movieSessionSaved = repository.save(movieSession);
            return new ResponseEntity<>(movieSessionSaved, CREATED);
        } catch (Exception e) {
            // TODO: 03/06/2022 LOG
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieSession> put(@RequestBody MovieSession movieSession){
        if (movieSession.getMovie_id() == null || movieSession.getCinema_id() == null || movieSession.getCinema_id().isEmpty() || movieSession.getMovie_id().isEmpty()) {
            return new ResponseEntity<>(UNPROCESSABLE_ENTITY);
        }

        repository.save(movieSession);

        return new ResponseEntity<>(movieSession,OK);
    }
}
