package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.entities.Movie;
import fr.iut.aluilcine.repositories.MovieRepository;
import fr.iut.aluilcine.repositories.MovieSessionRepository;
import fr.iut.aluilcine.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

/**
 * CinemaController qui permet la gestion des {@link Movie movies}.
 */
@RestController
@RequestMapping("/movies")
public class MovieController extends BaseController<Movie, MovieRepository> {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    MovieSessionRepository movieSessionRepository;

    @Override
    protected Optional<ResponseEntity<?>> beforeDelete(String entityDeleteId) {
        movieSessionRepository.deleteByMovieId(entityDeleteId);
        reviewRepository.deleteByMovieId(entityDeleteId);
        return Optional.empty();
    }

    @GetMapping("/pageableByMark{number}")
    public ResponseEntity<?> top(@PathVariable("number") int number,
                                 @RequestParam(value = "page", required = false) Integer page){
        try {
            if(number <= 0)
                return new ResponseEntity<>("Le nombre doit être supérieur à 0",NOT_ACCEPTABLE);

            if(page == null){
                PageRequest request = PageRequest.of(0,number);
                return new ResponseEntity<>(repository.findByOrderByMarkDesc(request).getContent(), OK);
            }

            if(page < 0)
                return new ResponseEntity<>("Le numero de la page doit être positif",NOT_ACCEPTABLE);

            PageRequest request = PageRequest.of(page,number);
            return new ResponseEntity<>(repository.findByOrderByMarkDesc(request), OK);

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

    @GetMapping("/pageableByCategory/{category}")
    public ResponseEntity<?> movieByCategory(@PathVariable("category") String category,
                                             @RequestParam(value = "page", required = false) Integer page,
                                             @RequestParam(value = "numberOfMovieByPage", required = false) Integer numberOfMovieByPage){
        try {

            if(page == null &&numberOfMovieByPage != null){
                PageRequest request = PageRequest.of(0,numberOfMovieByPage);
                return new ResponseEntity<>(repository.findByCategories(category, request).getContent(), OK);
            }

            if(page == null &&numberOfMovieByPage == null){
                PageRequest request = PageRequest.of(0,20);
                return new ResponseEntity<>(repository.findByCategories(category, request).getContent(), OK);
            }

            if(numberOfMovieByPage <= 0)
                return new ResponseEntity<>("Le nombre de film doit être supérieur à 0",NOT_ACCEPTABLE);

            if(page < 0)
                return new ResponseEntity<>("Le numero de la page doit être positif",NOT_ACCEPTABLE);

            PageRequest request = PageRequest.of(page,numberOfMovieByPage);
            return new ResponseEntity<>(repository.findByCategories(category, request), OK);

        }catch (Exception e){
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

}
