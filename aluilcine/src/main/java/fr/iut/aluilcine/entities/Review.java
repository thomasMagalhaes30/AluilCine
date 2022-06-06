package fr.iut.aluilcine.entities;

import org.springframework.data.annotation.Id;

/**
 * Represente un commentaire
 */
public class Review extends BaseEntity{
    private int schemaVersion;

    private String pseudo;

    private String message;

    private int mark;

    private String movieId;

    private String userId;

    public Review() {}

    public Review(int schemaVersion, String pseudo, String message, int mark, String movieId, String userId) {
        this.schemaVersion = schemaVersion;
        this.pseudo = pseudo;
        this.message = message;
        this.mark = mark;
        this.movieId = movieId;
        this.userId = userId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
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
