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

    public String userId;

    public Review() {}

    public Review(String id, int schemaVersion, String pseudo, String message, int mark, String movieId, String userId) {
        this.id = id;
        this.schemaVersion = schemaVersion;
        this.pseudo = pseudo;
        this.message = message;
        this.mark = mark;
        this.movieId = movieId;
        this.userId = userId;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", schemaVersion=" + schemaVersion +
                ", pseudo='" + pseudo + '\'' +
                ", message='" + message + '\'' +
                ", mark=" + mark +
                ", movieId='" + movieId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
