package fr.iut.aluilcine.controllers;


import fr.iut.aluilcine.entities.Category;
import fr.iut.aluilcine.entities.User;
import fr.iut.aluilcine.repositories.CategoryRepository;
import fr.iut.aluilcine.repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CategoryController qui permet la gestion des {@link Category de Movies}.
 */
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController<Category, CategoryRepository> {


}
