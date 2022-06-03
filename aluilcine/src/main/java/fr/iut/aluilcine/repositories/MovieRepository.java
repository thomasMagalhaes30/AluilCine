package fr.iut.aluilcine.repositories;

import fr.iut.aluilcine.entities.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository  extends MongoRepository<Movie, String> {


}
