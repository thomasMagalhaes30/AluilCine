package fr.iut.aluilcine.repositories;

import fr.iut.aluilcine.entities.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Represente un repository des films
 */
public interface MovieRepository  extends MongoRepository<Movie, String> {


}
