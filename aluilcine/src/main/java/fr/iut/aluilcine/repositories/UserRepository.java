package fr.iut.aluilcine.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.iut.aluilcine.entities.User;

/**
 * Represente un repository des utilisateurs
 */
public interface UserRepository extends MongoRepository<User, String> {

}