package fr.iut.aluilcine.repositories;

import fr.iut.aluilcine.entities.Cinema;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Represente un repository des cinemas
 */
public interface CinemaRepository extends MongoRepository<Cinema, String> {
}
