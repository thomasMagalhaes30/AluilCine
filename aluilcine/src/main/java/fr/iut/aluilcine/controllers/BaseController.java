package fr.iut.aluilcine.controllers;


import fr.iut.aluilcine.entities.BaseEntity;
import fr.iut.aluilcine.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

/**
 * Représente le controlleur de base
 */
public abstract class BaseController <TEntity extends BaseEntity, TRepository extends MongoRepository<TEntity, String>> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TRepository repository;

    protected static final Validator validator;

    static {
        Configuration<?> config = Validation.byDefaultProvider().configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        validator = factory.getValidator();
        factory.close();
    }

    /**
     * Valide ou non une entité
     * @param entity l'entité à valider
     * @return un optional de string, vide si l'entité est valide
     */
    protected Optional<String> validateEntity(TEntity entity){
        Optional<ConstraintViolation<TEntity>> opt = validator.validate(entity).stream().findAny();
        if (opt.isEmpty()){
            return Optional.empty();
        }
        ConstraintViolation<TEntity> violation = opt.get();
        return Optional.of(String.format("%s %s\n",violation.getPropertyPath(), violation.getMessage()));
    }

    protected void customLogError(String e){
        logger.error(
            String.format("Error in %s => %s\n%s", this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(), e)
        );
    }

//          ██████ ██████  ██    ██ ██████
//         ██      ██   ██ ██    ██ ██   ██
//         ██      ██████  ██    ██ ██   ██
//         ██      ██   ██ ██    ██ ██   ██
//          ██████ ██   ██  ██████  ██████

    @GetMapping("")
    public ResponseEntity<List<TEntity>> getAll() {
        try {
            return new ResponseEntity<>(repository.findAll(), OK);
        }catch (Exception e){
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TEntity> getOneById(@PathVariable("id") String id){
        try {
            Optional<TEntity> opt = repository.findById(id);
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
    public ResponseEntity<?> addOne(@RequestBody TEntity entity){
        try {
            // valide l'entite
            Optional<String> optValidation = validateEntity(entity);
            if (optValidation.isPresent()) {
                return new ResponseEntity<>(optValidation.get(), UNPROCESSABLE_ENTITY);
            }
            return new ResponseEntity<>(repository.save(entity), CREATED);
        }catch (Exception e){
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOne(@PathVariable("id") String id, @RequestBody TEntity entity) {
        try {
            // valide l'entite
            Optional<String> optValidation = validateEntity(entity);
            if (optValidation.isPresent()) {
                return new ResponseEntity<>(optValidation.get(), UNPROCESSABLE_ENTITY);
            }

            Optional<TEntity> opt = repository.findById(id);
            if (opt.isEmpty()){
                return new ResponseEntity<>(NOT_FOUND);
            }
            // s'assure qu'on est le même id dans l'objet qu'en paramètre
            entity.setId(opt.get().getId());

            return new ResponseEntity<>(repository.save(entity), HttpStatus.OK);
        }catch (Exception e){
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOne(@PathVariable("id") String id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(NO_CONTENT);
        } catch (Exception e) {
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }
}
