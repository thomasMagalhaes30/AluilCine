package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.repositories.MovieSessionRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieSessionController {

    final MovieSessionRepository repository;

    public MovieSessionController(MovieSessionRepository repository) {
        this.repository = repository;
    }
}
