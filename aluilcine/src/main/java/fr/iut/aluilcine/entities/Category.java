package fr.iut.aluilcine.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * Represente une catégorie
 */
@Document(collection = "category")
public class Category extends BaseEntity {
    @NotBlank
    @Size(min=2, max = 128)
    private String nom;

    public Category() {}

    public Category(String nom, int schemaVersion) {
        this.nom = nom;
        this.schemaVersion = schemaVersion;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", schemaVersion=" + schemaVersion +
                ", nom='" + nom + '\'' +
                '}';
    }
}
