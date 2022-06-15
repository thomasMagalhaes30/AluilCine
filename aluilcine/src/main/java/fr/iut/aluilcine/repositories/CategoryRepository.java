package fr.iut.aluilcine.repositories;

import fr.iut.aluilcine.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Represente un repository des catégories
 */
public interface CategoryRepository extends MongoRepository<Category, String> {
}
