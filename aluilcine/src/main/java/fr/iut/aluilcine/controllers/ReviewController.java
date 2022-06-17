
package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.entities.Review;
import fr.iut.aluilcine.repositories.MovieRepositoryCustom;
import fr.iut.aluilcine.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

/**
 * Controller permettant la gestion des {@link Review reviews}.
 */
@RestController
@RequestMapping("/reviews")
public class ReviewController extends BaseController<Review, ReviewRepository>{

    @Autowired
    MovieRepositoryCustom movieRepositoryCustom;

    @Override
    protected Optional<ResponseEntity<?>> afterAdd(Review entityAdd) {
        movieRepositoryCustom.updateMovieByIdAfterAddReview(entityAdd.getMovieId(), entityAdd.getMark());
        return Optional.empty();
    }

    @Override
    protected Optional<ResponseEntity<?>> beforeUpdate(Review oldEntity, Review newEntity) {
        // règle métier
        if (!Objects.equals(oldEntity.getMovieId(), newEntity.getMovieId()))
            return Optional.of(new ResponseEntity<>("movieId de l'ancienne version et de la nouvelle différente (REGLE METIER)", UNPROCESSABLE_ENTITY));

        movieRepositoryCustom.updateMovieByIdAfterUpdateReview(oldEntity.getMovieId(), oldEntity.getMark(), newEntity.getMark());
        return Optional.empty();
    }

    @Override
    protected Optional<ResponseEntity<?>> beforeDelete(String entityDeleteId) {
        Optional<Review> opt = repository.findById(entityDeleteId);
        if (opt.isEmpty())
            return Optional.of(new ResponseEntity<>(NOT_FOUND));
        Review entityDelete = opt.get();
        movieRepositoryCustom.updateMovieByIdAfterDeleteReview(entityDelete.getMovieId(), entityDelete.getMark());
        return Optional.empty();
    }

    /**
     * Obtenir les reviews selon un movieId, on peut aussi avoir une certaine page et avoir
     * @param movieId le movieId
     * @param page la page à récupérer
     * @param numberOfReviewsByPage le nombre de reviews par page
     * @return Une response entity avec des reviews, ou alors du texte
     */
    @GetMapping("/pageableByMovieId")
    public ResponseEntity<?> getReviewsByMovieIdByPage(@RequestParam(value = "movieId") String movieId,
                                                       @RequestParam(value = "page", required = false) Integer page,
                                                       @RequestParam(value = "numberOfReviewsByPage", required = false) Integer numberOfReviewsByPage)
    {
        try{
            if (numberOfReviewsByPage == null) numberOfReviewsByPage = 5;

            if(numberOfReviewsByPage <= 0 || numberOfReviewsByPage > 20)
                return new ResponseEntity<>("Le nombre doit être entre 1 et 20",NOT_ACCEPTABLE);

            if(page == null) {
                PageRequest request = PageRequest.of(0,numberOfReviewsByPage);
                return new ResponseEntity<>(repository.findByMovieId(movieId,request), OK);
            }

            if(page < 0)
                return new ResponseEntity<>("Le numero de la page doit être positif",NOT_ACCEPTABLE);

            PageRequest request = PageRequest.of(page,numberOfReviewsByPage);

            return new ResponseEntity<>(repository.findByMovieId(movieId,request), OK);

        }catch (Exception e){
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }
}