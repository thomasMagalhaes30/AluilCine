package fr.iut.aluilcine.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Représentation d'un film.
 */
@Document(collection = "movie")
public class Movie extends BaseEntity{

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String duration;

    @NotNull
    private Date release_date;

    @NotNull
    @NotBlank
    private String synopsis;

    @Min(0)
    @Max(5)
    private Float mark;

    @NotNull
    private List<String> categories;

    @NotNull
    @NotBlank
    private String image_uri;

    @NotNull
    @NotBlank
    private String image_large_uri;

    @Min(0)
    @NotNull
    private int totalReview;

    @NotNull
    private List<Actor> actors;

    public Movie(String title, String duration, Date release_date, String synopsis, Float mark, List<String> categories, String image_uri, String image_large_uri, int totalReview, List<Actor> actors, int schemaVersion) {
        this.title = title;
        this.duration = duration;
        this.release_date = release_date;
        this.synopsis = synopsis;
        this.mark = mark;
        this.categories = categories;
        this.image_uri = image_uri;
        this.image_large_uri = image_large_uri;
        this.totalReview = totalReview;
        this.actors = actors;
        this.schemaVersion = schemaVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Float getMark() {
        return mark;
    }

    public void setMark(Float mark) {
        this.mark = mark;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }

    public String getImage_large_uri() {
        return image_large_uri;
    }

    public void setImage_large_uri(String image_large_uri) {
        this.image_large_uri = image_large_uri;
    }

    public int getTotalReview() {
        return totalReview;
    }

    public void setTotalReview(int totalReview) {
        this.totalReview = totalReview;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", schema_version=" + schemaVersion +
                ", title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                ", release_date='" + release_date + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", mark=" + mark +
                ", category=" + categories +
                ", image_uri='" + image_uri + '\'' +
                ", image_large_uri='" + image_large_uri + '\'' +
                ", totalReview=" + totalReview +
                ", actors=" + actors +
                '}';
    }
}