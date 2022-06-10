package fr.iut.aluilcine.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Represente une sceance de cinema
 */
@Document(collection = "movieSessions")
public class MovieSession extends BaseEntity{

    @Min(0)
    private int schemaVersion;

    @NotNull
    @NotBlank
    private String movieId;

    @NotNull
    @NotBlank
    private String cinemaId;

    @NotNull
    private Date dateSchedule;

    public MovieSession() {}

    public MovieSession(int schemaVersion, String movieId, String cinemaId, Date date_schedule) {
        this.schemaVersion = schemaVersion;
        this.movieId = movieId;
        this.cinemaId = cinemaId;
        this.dateSchedule = date_schedule;
    }

    public int getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(int schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Date getDateSchedule() {
        return dateSchedule;
    }

    public void setDateSchedule(Date dateSchedule) {
        this.dateSchedule = dateSchedule;
    }

    @Override
    public String toString() {
        return "MovieSession{" +
                "id='" + id + '\'' +
                ", schema_version=" + schemaVersion +
                ", movie_id='" + movieId + '\'' +
                ", cinema_id='" + cinemaId + '\'' +
                ", date_schedule='" + dateSchedule + '\'' +
                '}';
    }
}
