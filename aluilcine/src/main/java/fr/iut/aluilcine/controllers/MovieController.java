package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.entities.Cinema;
import fr.iut.aluilcine.entities.Movie;
import fr.iut.aluilcine.entities.User;
import fr.iut.aluilcine.repositories.MovieRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * CinemaController qui permet la gestion des {@link Movie movies}.
 */
@RestController
@RequestMapping("/movies")
public class MovieController extends BaseController<Movie, MovieRepository> {
}
