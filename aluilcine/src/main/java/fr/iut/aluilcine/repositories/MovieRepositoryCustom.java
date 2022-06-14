package fr.iut.aluilcine.repositories;

import fr.iut.aluilcine.entities.Movie;
import fr.iut.aluilcine.entities.MovieAggregate;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieRepositoryCustom{

    /**
     * Met à jour un film après l'ajout d'un avis
     * @param movieId identifiant du film
     * @param mark note à ajouter
     */
    void updateMovieByIdAfterAddReview(String movieId, int mark);

    /**
     * Met à jour un film après la modification d'un avis
     * @param movieId identifiant du film
     * @param oldMark ancinne note
     * @param newMark nouvelle note
     */
    void updateMovieByIdAfterUpdateReview(String movieId, int oldMark, int newMark);

    /**
     * Met à jour un film après la suppression d'un avis
     * @param movieId identifiant du film
     * @param mark note à retirer
     */
    void updateMovieByIdAfterDeleteReview(String movieId, int mark);

    /**
     * Obtient les notes moyenne par categorie
     * @return Une List de MovieAggregate
     */
    List<MovieAggregate> markAvgByCategories();
}
