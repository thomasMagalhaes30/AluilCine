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
    private Date releaseDate;

    @NotNull
    @NotBlank
    private String synopsis;

    @Min(0)
    @Max(5)
    private float mark;

    @NotNull
    private List<String> categories;

    @NotNull
    @NotBlank
    private String imageUri;

    @NotNull
    @NotBlank
    private String imageLargeUri;

    @Min(0)
    @NotNull
    private int totalReview;

    @NotNull
    private List<Actor> actors;

    public Movie() {
    }

    public Movie(String title, String duration, Date releaseDate, String synopsis, List<String> categories, String imageUri, String imageLargeUri, List<Actor> actors) {
        this.title = title;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.synopsis = synopsis;
        this.categories = categories;
        this.imageUri = imageUri;
        this.imageLargeUri = imageLargeUri;
        this.actors = actors;
        this.mark = 0f;
        this.totalReview = 0;
    }

    public Movie(String title, String duration, Date releaseDate, String synopsis, float mark, List<String> categories, String imageUri, String imageLargeUri, int totalReview, List<Actor> actors) {
        this.title = title;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.synopsis = synopsis;
        this.mark = mark;
        this.categories = categories;
        this.imageUri = imageUri;
        this.imageLargeUri = imageLargeUri;
        this.totalReview = totalReview;
        this.actors = actors;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getImageLargeUri() {
        return imageLargeUri;
    }

    public void setImageLargeUri(String imageLargeUri) {
        this.imageLargeUri = imageLargeUri;
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
                ", id='" + id + '\'' +
                "title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                ", releaseDate=" + releaseDate +
                ", synopsis='" + synopsis + '\'' +
                ", mark=" + mark +
                ", categories=" + categories +
                ", imageUri='" + imageUri + '\'' +
                ", imageLargeUri='" + imageLargeUri + '\'' +
                ", totalReview=" + totalReview +
                ", actors=" + actors +
                '}';
    }
}