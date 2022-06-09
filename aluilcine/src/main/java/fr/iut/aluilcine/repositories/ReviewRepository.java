package fr.iut.aluilcine.repositories;

import fr.iut.aluilcine.entities.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Represente un repository des commentaires
 */
public interface ReviewRepository extends MongoRepository<Review, String> {

}