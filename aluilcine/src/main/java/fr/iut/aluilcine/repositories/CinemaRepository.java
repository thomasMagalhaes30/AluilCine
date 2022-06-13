package fr.iut.aluilcine.repositories;

import fr.iut.aluilcine.entities.Cinema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Represente un repository des cinemas
 */
public interface CinemaRepository extends MongoRepository<Cinema, String> {
}
