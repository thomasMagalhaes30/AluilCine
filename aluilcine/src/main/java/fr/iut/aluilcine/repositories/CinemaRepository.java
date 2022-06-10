package fr.iut.aluilcine.repositories;

import fr.iut.aluilcine.entities.Cinema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Represente un repository des cinemas
 */
public interface CinemaRepository extends MongoRepository<Cinema, String> {
    @Query("{ latitude : { $gte : ?0 , $lte : ?1 } , longitude : { $gte : ?2 , $lte : ?3}}")
    List<Cinema> getAllByLocationInternal(float latitudeMin, float latitudeMax, float longitudeMin , float longitudeMax);
}
