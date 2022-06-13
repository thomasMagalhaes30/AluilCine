package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.entities.Cinema;
import fr.iut.aluilcine.entities.MovieSession;
import fr.iut.aluilcine.entities.Review;
import fr.iut.aluilcine.repositories.CinemaRepository;
import fr.iut.aluilcine.repositories.MovieSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * CinemaController qui permet la gestion des {@link Cinema cinemas}.
 */
@RestController
@RequestMapping("/cinemas")
public class CinemaController extends BaseController<Cinema, CinemaRepository> {

    @Autowired
    MovieSessionRepository movieSessionRepository;

    @Override
    protected Optional<ResponseEntity<?>> beforeDelete(String entityDeleteId) {
        movieSessionRepository.deleteByCinemaId(entityDeleteId);
        return Optional.empty();
    }
}
