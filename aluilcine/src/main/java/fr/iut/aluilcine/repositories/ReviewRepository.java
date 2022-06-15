package fr.iut.aluilcine.repositories;

import fr.iut.aluilcine.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Represente un repository des commentaires
 */
public interface ReviewRepository extends MongoRepository<Review, String> {

    /**
     * Cherche les films par identifiant de film
     * @param movieId identifiant d'un film
     * @param pageable pagination
     * @return une page de review
     */
    Page<Review> findByMovieId(String movieId,Pageable pageable);

    /**
     * Supprime tout les commentaires qui ont le movieId correspondant au paramètre
     * @param movieId identifiant d'un film
     */
    void deleteByMovieId(String movieId);
}