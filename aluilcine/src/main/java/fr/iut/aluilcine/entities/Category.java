package fr.iut.aluilcine.entities;

public class Category extends BaseEntity {
    private String nom;
    private int schemaVersion;

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

    public int getSchemaVersion() {
        return schemaVersion;
    }

    @Override
    public String toString() {
        return "Category{" +
                "nom='" + nom + '\'' +
                ", schemaVersion=" + schemaVersion +
                '}';
    }
}
