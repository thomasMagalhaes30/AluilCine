package fr.iut.aluilcine.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Représente le controlleur de base
 */
public abstract class BaseController <TEntity, TRepository extends MongoRepository<TEntity, String>> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TRepository repository;

    protected void customLogError(String e){
        logger.error(
                String.format("Error in %s => %s\n%s", this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(), e)
        );
    }
}
