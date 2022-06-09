package fr.iut.aluilcine.repositories;

import fr.iut.aluilcine.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Represente un repository des utilisateurs
 */
public interface UserRepository extends MongoRepository<User, String> {

}