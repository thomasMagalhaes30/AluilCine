package fr.iut.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.iut.aluilcine.entities.User;

public interface UserRepository extends MongoRepository<User, String> {

}