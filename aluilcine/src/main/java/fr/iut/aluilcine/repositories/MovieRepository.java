package fr.iut.aluilcine.repositories;

import fr.iut.aluilcine.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.Date;
import java.util.List;

/**
 * Represente un repository des films
 */
public interface MovieRepository extends MongoRepository<Movie, String> {

    Page<Movie> findByOrderByMarkDesc(Pageable pageable);

    Page<Movie> findByReleaseDateBeforeOrderByReleaseDateDesc(Date to, Pageable pageable);

}
