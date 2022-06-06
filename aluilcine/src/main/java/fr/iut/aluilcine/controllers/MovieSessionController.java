package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.entities.MovieSession;
import fr.iut.aluilcine.repositories.MovieSessionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/moviesessions")
public class MovieSessionController extends BaseController<MovieSession, MovieSessionRepository> {

    
}
