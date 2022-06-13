
package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.entities.Review;
import fr.iut.aluilcine.repositories.MovieRepository;
import fr.iut.aluilcine.repositories.MovieRepositoryCustom;
import fr.iut.aluilcine.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public boolean afterAdd(Review entityAdd) {
        movieRepositoryCustom.updateMovieByIdAfterAddReview(entityAdd.getMovieId(), entityAdd.getMark());
        return true;
    }



    @GetMapping("/pageableByMovieId")
    public ResponseEntity<?> getReviewsByMovieIdByPage(@RequestParam(value = "movieId") String movieId,
                                           @RequestParam(value = "page", required = false) Integer page,
                                                       @RequestParam(value = "numberOfReviewsByPage", required = false) Integer numberOfReviewsByPage){

    try{
        if (numberOfReviewsByPage == null) numberOfReviewsByPage = 5;

        if(numberOfReviewsByPage <= 0 || numberOfReviewsByPage > 20)
            return new ResponseEntity<>("Le nombre doit être entre 1 et 20",NOT_ACCEPTABLE);

        if(page == null){
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