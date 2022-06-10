package fr.iut.aluilcine.controllers;


import fr.iut.aluilcine.entities.Movie;
import fr.iut.aluilcine.repositories.MovieRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

/**
 * CinemaController qui permet la gestion des {@link Movie movies}.
 */
@RestController
@RequestMapping("/movies")
public class MovieController extends BaseController<Movie, MovieRepository> {

    @GetMapping("/top{number}")
    public ResponseEntity<?> top(@PathVariable("number") int number){
        try {
            if(number <= 0 || number > 20)
                return new ResponseEntity<>("Le nombre doit être entre 1 et 20",NOT_ACCEPTABLE);

            PageRequest request = PageRequest.of(0,number);
            return new ResponseEntity<>(repository.findByOrderByMarkDesc(request).getContent(), OK);

        }catch (Exception e){
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }
}
