
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

    @GetMapping("")
    public ResponseEntity<List<User>> getAll() {
        try {
            return new ResponseEntity<>(repository.findAll(), OK);
        }catch (Exception e){
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOneById(@PathVariable("id") String id){
        try {
            Optional<User> opt = repository.findById(id);
            if (opt.isEmpty()){
                return new ResponseEntity<>(NOT_FOUND);
            }
            return new ResponseEntity<>(opt.get(), OK);
        }catch (Exception e){
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    @PostMapping("")
    public ResponseEntity<?> addOne(@RequestBody User user){
        try {
            // valide l'entite
            Optional<String> optValidation = validateEntity(user);
            if (optValidation.isPresent()) {
                return new ResponseEntity<>(optValidation.get(), UNPROCESSABLE_ENTITY);
            }
            return new ResponseEntity<>(repository.save(user), CREATED);
        }catch (Exception e){
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOne(@PathVariable("id") String id, @RequestBody User user) {
        try {
            // valide l'entite
            Optional<String> optValidation = validateEntity(user);
            if (optValidation.isPresent()) {
                return new ResponseEntity<>(optValidation.get(), UNPROCESSABLE_ENTITY);
            }

            Optional<User> opt = repository.findById(id);
            if (opt.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            User _user = opt.get();
            _user.setPseudo(user.getPseudo());
            _user.setPassword(user.getPassword());
            _user.setEmail(user.getEmail());

            return new ResponseEntity<>(repository.save(_user), HttpStatus.OK);
        }catch (Exception e){
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOne(@PathVariable("id") String id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}