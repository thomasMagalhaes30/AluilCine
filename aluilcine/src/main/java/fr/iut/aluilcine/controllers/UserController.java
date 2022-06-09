
package fr.iut.aluilcine.controllers;

import fr.iut.aluilcine.entities.User;
import fr.iut.aluilcine.repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController qui permet la gestion des {@link User users}.
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User, UserRepository>{


}