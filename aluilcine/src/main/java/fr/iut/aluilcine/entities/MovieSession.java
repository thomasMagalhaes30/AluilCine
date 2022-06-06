package fr.iut.aluilcine.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Represente une sceance de cinema
 */
@Document(collection = "moviessession")
public class MovieSession extends BaseEntity{

    @Min(0)
    private int schema_version;

    @NotNull
    @NotBlank
    private String movie_id;

    @NotNull
    @NotBlank
    private String cinema_id;

    @NotNull
    private Date date_schedule;

    public MovieSession() {}

    public MovieSession(int schemaVersion, String movieId, String cinemaId, Date date_schedule) {
        this.schema_version = schemaVersion;
        this.movie_id = movieId;
        this.cinema_id = cinemaId;
        this.date_schedule = date_schedule;
    }

    public int getSchema_version() {
        return schema_version;
    }

    public void setSchema_version(int schema_version) {
        this.schema_version = schema_version;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(String cinema_id) {
        this.cinema_id = cinema_id;
    }

    public Date getDate_schedule() {
        return date_schedule;
    }

    public void setDate_schedule(Date date_schedule) {
        this.date_schedule = date_schedule;
    }

    @Override
    public String toString() {
        return "MovieSession{" +
                "id='" + id + '\'' +
                ", schema_version=" + schema_version +
                ", movie_id='" + movie_id + '\'' +
                ", cinema_id='" + cinema_id + '\'' +
                ", date_schedule='" + date_schedule + '\'' +
                '}';
    }
}
