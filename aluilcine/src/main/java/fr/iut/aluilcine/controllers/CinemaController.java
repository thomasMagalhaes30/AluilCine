package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.entities.Cinema;
import fr.iut.aluilcine.repositories.CinemaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

/**
 * CinemaController qui permet la gestion des {@link Cinema cinemas}.
 */
@RestController
@RequestMapping("/cinemas")
public class CinemaController extends BaseController<Cinema, CinemaRepository> {

    private static class CinemaDistance {
        public Cinema cinema;
        public double rayon;

        public CinemaDistance(Cinema c, double rayon) {
            this.cinema = c;
            this.rayon = rayon;
        }
    }

    // latitude et longitude sont obligatoire
    // on doit prendre les 5 les plus proches de la latitude et longitude données
    /**
     * Récupère une liste de cinema selon une latitude et une longitude
     * @param latitude latitude donnée
     * @param longitude longitude donnée
     * @return Une liste de cinema trié par odre croissant de distance du point donné
     */
    @GetMapping("/location")
    public ResponseEntity<?> getAllByLocation(@RequestParam("latitude") float latitude,
                                              @RequestParam("longitude") float longitude,
                                              @RequestParam(value = "limit", required = false) Integer limit) {
        if (limit == null)
            limit = 5; // par défaut la limite est 5

        logger.info("latitude : " + latitude  + ", longitude : " + longitude);

        List<Cinema> cinemas = repository.findAll();
        List<CinemaDistance> cinemaDistances = new ArrayList<>();

        for (Cinema c:
             cinemas) {
            float cLatitude = c.getLatitude();
            float cLongitude = c.getLongitude();

            double rayon = Math.sqrt(Math.pow(cLatitude-latitude,2) - Math.pow(cLongitude-longitude,2));

            logger.info("Rayon calculé pour " + c.getName() + " est de : " + rayon);
            cinemaDistances.add(new CinemaDistance(c, rayon));
        }

        cinemaDistances.sort(Comparator.comparingDouble(o -> o.rayon));
        cinemas = cinemaDistances.stream()
                .map(p -> p.cinema)
                .limit((limit <= 0) ? 5 : limit)
                .collect(Collectors.toList());

        return new ResponseEntity<>(cinemas,OK);
    }
}


