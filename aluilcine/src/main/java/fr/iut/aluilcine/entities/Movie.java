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

    public String duration;

    public String release_date;

    public String synopsis;

    public Float mark;

    public String category;

    public String image_uri;

    public int totalReview;

    public List<Actor> actors;

    public int schemaVersion;

    public Movie(String id, String title, String duration, String release_date, String synopsis, Float mark, String category, String image_uri, int totalReview, List<Actor> actors, int schemaVersion) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.release_date = release_date;
        this.synopsis = synopsis;
        this.mark = mark;
        this.category = category;
        this.image_uri = image_uri;
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
                ", release_date=" + release_date +
                ", synopsis='" + synopsis + '\'' +
                ", mark=" + mark +
                ", category='" + category + '\'' +
                ", image_uri='" + image_uri + '\'' +
                ", totalReview=" + totalReview +
                ", actors=" + actors +
                ", schemaVersion=" + schemaVersion +
                '}';
    }
}