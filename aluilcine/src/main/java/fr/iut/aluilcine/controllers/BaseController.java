package fr.iut.aluilcine.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.validation.*;
import java.util.Optional;

/**
 * Représente le controlleur de base
 */
public abstract class BaseController <TEntity, TRepository extends MongoRepository<TEntity, String>> {

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
}
