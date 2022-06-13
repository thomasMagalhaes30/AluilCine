package fr.iut.aluilcine.repositories;

import fr.iut.aluilcine.entities.MovieSession;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MovieSessionRepository extends MongoRepository<MovieSession, String> {
    @Query("{ 'cinemaId' : ?0 }")
    List<MovieSession> findByCinemaId(String cinemaId);

    @Query("{ 'movieId' : ?0 }")
    List<MovieSession> findByMovieId(String movieId);

    @Query("{'cinemaId': ?0, 'movieId': ?1}")
    List<MovieSession> findByCinemaIdAndMovieId(String cinemaId, String movieId);

    void deleteByCinemaId(String cinemaId);
}
