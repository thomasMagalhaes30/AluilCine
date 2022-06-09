package fr.iut.aluilcine.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Represente un utilisateur.
 */
@Document(collection = "user")
public class User extends BaseEntity {
    private int schemaVersion;

    @NotBlank
    @Size(min=2, max = 128)
    private String pseudo;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min=8, max = 128)
    private String password;

    public User(){}

    public User(int schemaVersion, String pseudo, String email, String password) {
        this.schemaVersion = schemaVersion;
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
    }

    public int getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(int schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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
