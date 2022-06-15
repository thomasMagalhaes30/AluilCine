package fr.iut.aluilcine.repositories;

import fr.iut.aluilcine.entities.MovieSession;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Represente un repository des séances de film
 */
public interface MovieSessionRepository extends MongoRepository<MovieSession, String> {
    /**
     * Cherche les séances de film par identifiant de cinéma
     * @param cinemaId identifiant d'un cinéma
     * @return une list de séance de film
     */
    @Query("{ 'cinemaId' : ?0 }")
    List<MovieSession> findByCinemaId(String cinemaId);

    /**
     * Cherche les séances de film par identifiant de film
     * @param movieId identifiant d'un film
     * @return une list de séance de film
     */
    @Query("{ 'movieId' : ?0 }")
    List<MovieSession> findByMovieId(String movieId);

    /**
     * Cherche les séances de film par identifiant de cinéma et par identifiant de film
     * @param cinemaId identifiant d'un cinéma
     * @param movieId identifiant d'un film
     * @return une list de séance de film
     */
    @Query("{'cinemaId': ?0, 'movieId': ?1}")
    List<MovieSession> findByCinemaIdAndMovieId(String cinemaId, String movieId);

    /**
     * Supprime toutes les séances de film qui ont le cinemaId correspondant au paramètre
     * @param cinemaId identifiant d'un cinéma
     */
    void deleteByCinemaId(String cinemaId);

    /**
     * Supprime toutes les séances de film le movieId correspondant au paramètre
     * @param movieId identifiant d'un film
     */
    void deleteByMovieId(String movieId);
}
