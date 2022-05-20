package fr.iut.aluilcine.entities;

import org.springframework.data.annotation.Id;

/**
 * Represente un utilisateur.
 */
public class User {

    @Id
    public String id;

    public int schemaVersion;

    public String pseudo;

    public String email;

    public String password;

    public User(){}

    public User(String id, int schemaVersion, String pseudo, String email, String password) {
        this.id = id;
        this.schemaVersion = schemaVersion;
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", schemaVersion=" + schemaVersion +
                ", pseudo='" + pseudo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
