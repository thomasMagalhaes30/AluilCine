package fr.iut.aluilcine.entities;

import org.springframework.data.annotation.Id;

/**
 * Represente un commentaire
 */
public class Review {

    @Id
    public String id;

    public int schemaVersion;

    public String pseudo;

    public String message;

    public int mark;

    public String movieId;

    public Review() {}

    public Review(String id, int schemaVersion, String pseudo, String message, int mark, String movieId) {
        this.id = id;
        this.schemaVersion = schemaVersion;
        this.pseudo = pseudo;
        this.message = message;
        this.mark = mark;
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", schemaVersion=" + schemaVersion +
                ", pseudo='" + pseudo + '\'' +
                ", message='" + message + '\'' +
                ", mark=" + mark +
                ", movieId='" + movieId + '\'' +
                '}';
    }
}
