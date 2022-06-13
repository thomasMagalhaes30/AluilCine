package fr.iut.aluilcine.controllers;


import fr.iut.aluilcine.entities.BaseEntity;
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
            TEntity entitySave = repository.save(entity);

            // CUSTOM ACTION IN ADD
            Optional<ResponseEntity<?>> optAfterAdd = afterAdd(entitySave);
            if (optAfterAdd.isPresent()){
                return optAfterAdd.get();
            }

            return new ResponseEntity<>(entitySave, CREATED);
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

            // CUSTOM ACTION IN UPDATE
            Optional<ResponseEntity<?>> optBeforeUpdate = beforeUpdate(opt.get(), entity);
            if (optBeforeUpdate.isPresent()){
                return optBeforeUpdate.get();
            }

            return new ResponseEntity<>(repository.save(entity), HttpStatus.OK);
        }catch (Exception e){
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable("id") String id) {
        try {
            // CUSTOM ACTION IN DELETE
            Optional<ResponseEntity<?>> optBeforeDelete = beforeDelete(id);
            if (optBeforeDelete.isPresent()){
                return optBeforeDelete.get();
            }

            repository.deleteById(id);
            return new ResponseEntity<>(NO_CONTENT);
        } catch (Exception e) {
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

//
//
// UTILS
//
//
//


    /**
     * Méthode appellé après l'ajout de l'entité
     * @param entityAdd l'entité ajouté
     * @return un optional d'un response entity vide si rien a faire, rempli si on souhaite effectué une action
     */
    protected Optional<ResponseEntity<?>> afterAdd(TEntity entityAdd){
        return Optional.empty();
    }

    /**
     * Méthode appellé avant la modification de l'entité
     * @param oldEntity l'entité avant update
     * @param newEntity l'entité après update
     */
    protected Optional<ResponseEntity<?>> beforeUpdate(TEntity oldEntity,TEntity newEntity){
        return Optional.empty();
    }

    /**
     * Méthode appellé avant la suppression de l'entité
     * @param entityDeleteId l'identifiant de l'entité qui va être supprimé
     */
    protected Optional<ResponseEntity<?>> beforeDelete(String entityDeleteId){
        return Optional.empty();
    }
}
