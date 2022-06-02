
package fr.iut.aluilcine.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.iut.aluilcine.entities.User;
import fr.iut.aluilcine.repositories.UserRepository;

/**
 * UserController qui permet la gestion des {@link User users}.
 */
@RestController
public class UserController {
     
    final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    public List<User> list() {
        return repository.findAll();
    }
}