package fr.iut.aluilcine.repositories;

import fr.iut.aluilcine.entities.Cinema;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieSessionRepository extends MongoRepository<Cinema, String> {
}
