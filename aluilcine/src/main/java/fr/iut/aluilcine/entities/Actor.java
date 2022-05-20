package fr.iut.aluilcine.entities;

/**
 * Représente un acteur d'un film.
 */
public class Actor {
    public String name;

    public String firstName;

    public String role;

    public String imageUrl;

    public Actor(String name, String firstName, String role, String imageUrl) {
        this.name = name;
        this.firstName = firstName;
        this.role = role;
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", role='" + role + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
