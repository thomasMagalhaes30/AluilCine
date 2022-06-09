
package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.entities.Review;
import fr.iut.aluilcine.repositories.ReviewRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller permettant la gestion des {@link Review reviews}.
 */
@RestController
@RequestMapping("/reviews")
public class ReviewController extends BaseController<Review, ReviewRepository>{

}