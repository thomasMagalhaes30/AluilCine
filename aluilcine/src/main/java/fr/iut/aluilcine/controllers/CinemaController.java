package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.entities.Cinema;
import fr.iut.aluilcine.repositories.CinemaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CinemaController qui permet la gestion des {@link Cinema cinemas}.
 */
@RestController
@RequestMapping("/cinemas")
public class CinemaController extends BaseController<Cinema, CinemaRepository> {
}
