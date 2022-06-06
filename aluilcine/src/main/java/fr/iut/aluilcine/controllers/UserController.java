
package fr.iut.aluilcine.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.iut.aluilcine.entities.User;
import fr.iut.aluilcine.repositories.UserRepository;

import javax.validation.ConstraintViolation;

import static org.springframework.http.HttpStatus.*;

/**
 * UserController qui permet la gestion des {@link User users}.
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User, UserRepository>{


}