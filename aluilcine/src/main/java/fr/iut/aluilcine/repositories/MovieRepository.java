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

    /**
     * Cherche les films par note décroissante (les films les mieux notés en premier)
     * @param pageable pagination
     * @return une page de film
     */
    Page<Movie> findByOrderByMarkDesc(Pageable pageable);

    /**
     * Cherche les films par date de sorti décroissante (les films les plus recent en premier)
     * @param to la date maximum possible (éviter de donner les films pas encore sortie)
     * @param pageable pagination
     * @return une page de film
     */
    Page<Movie> findByReleaseDateBeforeOrderByReleaseDateDesc(Date to, Pageable pageable);

    /**
     * Cherche les films par catégorie
     * @param category nom de la catégorie
     * @param pageable pagination
     * @return une page de film
     */
    @Query("{'categories': ?0}")
    Page<Movie> findByCategories(String category, Pageable pageable);
}
