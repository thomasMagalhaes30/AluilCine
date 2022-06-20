package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.entities.Movie;
import fr.iut.aluilcine.repositories.*;
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

    @Autowired
    MovieRepositoryCustom movieRepositoryCustom;

    @Override
    protected Optional<ResponseEntity<?>> beforeDelete(String entityDeleteId) {
        movieSessionRepository.deleteByMovieId(entityDeleteId);
        reviewRepository.deleteByMovieId(entityDeleteId);
        return Optional.empty();
    }

    /**
     * Obtient les films avec la meilleure note
     * @param number Nombre de film à retourné
     * @param page Paramètre non obligatoire, défini la page désiré
     * @return Une ResponseEntity :
     *                             - Une List de Movie en cas de succès si le page est null
     *                             - Une Pageable de Movie en cas de succès
     *                             - Un message d'erreur dans le cas où number est inférieur à 0
     *                             - Un message d'erreur dans le cas où page est inférieur à 0
     */
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

    /**
     * Obtient les derniers films sortie
     * @param number Nombre de film à retourné. Il doit être entre 0 et 20.
     * @return Une ResponseEntity :
     *                             - Une List de Movie en cas de succès
     *                             - Un message d'erreur dans le cas où number est inférieur ou égale à 0 et
     *                                  supérieur à 20
     */
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

    /**
     *  Obtient les films ayant une certaines catégories
     * @param category Catégorie rechercher
     * @param page Paramètre non obligatoire, défini la page désiré
     * @param numberOfMovieByPage Paramètre non obligatoire, défini le nombre de film par page
     * @return Une ResponseEntity :
     *                             - Une List de Movie en cas de succès si le page est null et/ou le
     *                                  numberOfMovieByPage est null
     *                             - Une Pageable de Movie en cas de succès
     *                             - Un message d'erreur dans le cas où numberOfMovieByPage est inférieur à 0
     *                             - Un message d'erreur dans le cas où page est inférieur à 0
     */
    @GetMapping("/pageableByCategory/{category}")
    public ResponseEntity<?> movieByCategory(@PathVariable("category") String category,
                                             @RequestParam(value = "page", required = false) Integer page,
                                             @RequestParam(value = "numberOfMovieByPage", required = false) Integer numberOfMovieByPage){
        try {

            if(page == null && numberOfMovieByPage != null){
                PageRequest request = PageRequest.of(0,numberOfMovieByPage);
                return new ResponseEntity<>(repository.findByCategories(category, request).getContent(), OK);
            }

            if(page == null && numberOfMovieByPage == null){
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

    /**
     * Obtient les notes moyenne par categorie
     * @return Une ResponseEntity :
     *                             - Une List de MovieAggregate
     *                             - Un message d'erreur en cas d'erreur
     */
    @GetMapping("/markAvgByCategories")
    public ResponseEntity<?> markAvgByCategories() {
        try {
            return new ResponseEntity<>(movieRepositoryCustom.markAvgByCategories(), OK);
        }catch (Exception e){
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    /**
     * Obtient les films à partir d'une recherche sur le titre du film
     * @param searchTitle titre recherché
     * @return une liste de film
     */
    @GetMapping("/searchByTitle/{searchTitle}")
    public ResponseEntity<?> findByTitle(@PathVariable("searchTitle") String searchTitle){
        try {
            System.out.println(searchTitle);
            return new ResponseEntity<>(repository.findMoviesByTitleContainsIgnoreCase(searchTitle), OK);
        }catch (Exception e){
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }
}
