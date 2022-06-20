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

    /**
     * Affiche a l'aide du logger un log d'erreur custom
     * @param e le message d'erreur
     */
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

    /**
     * Obtient les entites
     * @return les entites si ok
     */
    @GetMapping("")
    public ResponseEntity<List<TEntity>> getAll() {
        try {
            return new ResponseEntity<>(repository.findAll(), OK);
        }catch (Exception e){
            customLogError(e.getMessage());
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    /**
     * Obtient une entite a partir de son identifiant
     * @param id identifiant de l'entite a modifier
     * @return l'entite si ok
     */
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

    /**
     * Ajoute une entite
     * @param entity l'entite a ajouter
     * @return l'entite sauvegarde si created
     */
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

    /**
     * Met a jour une entite
     * @param id identifiant de l'entite a modifier
     * @param entity l'entite a utilser pour la mise a jour
     * @return l'entite sauvegarde si ok
     */
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

    /**
     * Supprime une entite a partir de son identifiant
     * @param id identifiant de l'entite a supprimer
     * @return rien si no_content
     */
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

//        ██    ██ ████████ ██ ██      ███████
//        ██    ██    ██    ██ ██      ██
//        ██    ██    ██    ██ ██      ███████
//        ██    ██    ██    ██ ██           ██
//         ██████     ██    ██ ███████ ███████

    /**
     * Méthode appellée après l'ajout de l'entité
     * @param entityAdd l'entité ajouté
     * @return un optional d'une response entity vide si il y a rien a faire, rempli si on souhaite effectué une action
     */
    protected Optional<ResponseEntity<?>> afterAdd(TEntity entityAdd){
        return Optional.empty();
    }

    /**
     * Méthode appellée avant la modification de l'entité
     * @param oldEntity l'entité avant update
     * @param newEntity l'entité après update
     * @return un optional d'une response entity vide si il y a rien a faire, rempli si on souhaite effectué une action
     */
    protected Optional<ResponseEntity<?>> beforeUpdate(TEntity oldEntity,TEntity newEntity){
        return Optional.empty();
    }

    /**
     * Méthode appellée avant la suppression de l'entité
     * @param entityDeleteId l'identifiant de l'entité qui va être supprimé
     * @return un optional d'une response entity vide si il y a rien a faire, rempli si on souhaite effectué une action
     */
    protected Optional<ResponseEntity<?>> beforeDelete(String entityDeleteId){
        return Optional.empty();
    }
}
