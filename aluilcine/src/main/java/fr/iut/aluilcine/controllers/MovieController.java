package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.entities.Movie;
import fr.iut.aluilcine.repositories.MovieRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CinemaController qui permet la gestion des {@link Movie movies}.
 */
@RestController
@RequestMapping("/movies")
public class MovieController extends BaseController<Movie, MovieRepository> {
}
