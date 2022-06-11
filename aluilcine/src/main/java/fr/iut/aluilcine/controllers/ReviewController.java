
package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.entities.Review;
import fr.iut.aluilcine.repositories.MovieRepository;
import fr.iut.aluilcine.repositories.MovieRepositoryCustom;
import fr.iut.aluilcine.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}