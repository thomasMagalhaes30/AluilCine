package fr.iut.aluilcine.controllers;


import fr.iut.aluilcine.entities.Movie;
import fr.iut.aluilcine.repositories.MovieRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.springframework.http.HttpStatus.*;

/**
 * CinemaController qui permet la gestion des {@link Movie movies}.
 */
@RestController
@RequestMapping("/movies")
public class MovieController extends BaseController<Movie, MovieRepository> {

    @GetMapping("/top{number}")
    public ResponseEntity<?> top(@PathVariable("number") int number,
                                 @RequestParam(value = "page", required = false) Integer page){
        try {
            if(number <= 0 || number > 20)
                return new ResponseEntity<>("Le nombre doit être entre 1 et 20",NOT_ACCEPTABLE);

            if(page == null){
                PageRequest request = PageRequest.of(0,number);
                return new ResponseEntity<>(repository.findByOrderByMarkDesc(request).getContent(), OK);
            }

            if(page < 0)
                return new ResponseEntity<>("Le numero de la page doit être positif",NOT_ACCEPTABLE);

            PageRequest request = PageRequest.of(page,number);
            return new ResponseEntity<>(repository.findByOrderByMarkDesc(request).getContent(), OK);

        }catch (Exception e){
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/last{number}MovieReleased")
    public ResponseEntity<?> lastMovieReleased(@PathVariable("number") int number){
        try {
            if(number <= 0 || number > 20)
                return new ResponseEntity<>("Le nombre doit être entre 1 et 20",NOT_ACCEPTABLE);

            PageRequest request = PageRequest.of(0,number);
            return new ResponseEntity<>(repository.findByReleaseDateBeforeOrderByReleaseDateDesc(new Date(),request).getContent(), OK);

        }catch (Exception e){
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

}
