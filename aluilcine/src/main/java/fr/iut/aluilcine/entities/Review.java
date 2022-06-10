package fr.iut.aluilcine.entities;

import javax.validation.constraints.*;

/**
 * Represente un commentaire
 */
public class Review extends BaseEntity{

    @NotBlank
    @Size(min=2, max = 128)
    private String pseudo;

    @NotBlank
    private String message;
    
    @Min(0)
    @Max(5)
    private int mark;

    @NotNull
    @NotBlank
    private String movieId;

    public Review() {}

    public Review(int schemaVersion, String pseudo, String message, int mark, String movieId) {
        this.schemaVersion = schemaVersion;
        this.pseudo = pseudo;
        this.message = message;
        this.mark = mark;
        this.movieId = movieId;
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

    @Override
    public java.lang.String toString() {
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
