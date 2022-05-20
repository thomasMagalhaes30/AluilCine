package fr.iut.aluilcine.entities;

import jdk.jfr.Timespan;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * Représentation d'un film.
 */
public class Movie{

    @Id
    public String id;

    public String title;

    public Timespan duration;

    public Date releaseDate;

    public String synopsis;

    public Float mark;

    public String category;

    public String imageUrl;

    public int totalReview;

    public List<Actor> actors;

    public int schemaVersion;

    public Movie(String id, String title, Timespan duration, Date releaseDate, String synopsis, Float mark, String category, String imageUrl, int totalReview, List<Actor> actors, int schemaVersion) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.synopsis = synopsis;
        this.mark = mark;
        this.category = category;
        this.imageUrl = imageUrl;
        this.totalReview = totalReview;
        this.actors = actors;
        this.schemaVersion = schemaVersion;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", releaseDate=" + releaseDate +
                ", synopsis='" + synopsis + '\'' +
                ", mark=" + mark +
                ", category='" + category + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", totalReview=" + totalReview +
                ", actors=" + actors +
                ", schemaVersion=" + schemaVersion +
                '}';
    }
}