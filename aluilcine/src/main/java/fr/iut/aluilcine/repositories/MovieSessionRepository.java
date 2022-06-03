package fr.iut.aluilcine.repositories;

import fr.iut.aluilcine.entities.MovieSession;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieSessionRepository extends MongoRepository<MovieSession, String> {
}
