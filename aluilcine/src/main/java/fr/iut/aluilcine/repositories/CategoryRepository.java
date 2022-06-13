package fr.iut.aluilcine.repositories;

import fr.iut.aluilcine.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
