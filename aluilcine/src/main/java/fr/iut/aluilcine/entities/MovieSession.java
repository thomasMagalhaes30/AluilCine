package fr.iut.aluilcine.entities;

import org.springframework.data.annotation.Id;

/**
 * Represente une sceance de cinema
 */
public class MovieSession {

    @Id
    public String id;

    public int schemaVersion;

    public String movieId;

    public String cinemaId;

    public MovieSession() {}

    public MovieSession(String id, int schemaVersion, String movieId, String cinemaId) {
        this.id = id;
        this.schemaVersion = schemaVersion;
        this.movieId = movieId;
        this.cinemaId = cinemaId;
    }

    @Override
    public String toString() {
        return "MovieSession{" +
                "id='" + id + '\'' +
                ", schemaVersion=" + schemaVersion +
                ", movieId='" + movieId + '\'' +
                ", cinemaId='" + cinemaId + '\'' +
                '}';
    }
}
