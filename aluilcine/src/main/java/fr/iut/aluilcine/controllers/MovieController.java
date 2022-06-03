package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.entities.Movie;
import fr.iut.aluilcine.entities.User;
import fr.iut.aluilcine.repositories.MovieRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class MovieController {

    private MovieRepository repository;

    public MovieController(MovieRepository repository){
        this.repository = repository;
    }

    @GetMapping("/movies")
    public List<Movie> list() {
        return repository.findAll();
    }

}
