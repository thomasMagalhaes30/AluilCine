package fr.iut.aluilcine.entities;


/**
 * Représentation de l'aggregation de donnée pour avoir les note moyenne par genre.
 */
public class MovieAggregate {

    private String id;
    private float mark;

    public MovieAggregate() {}

    public MovieAggregate(String id, float mark) {
        this.id = id;
        this.mark = mark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "MovieAggregate{" +
                "id='" + id + '\'' +
                ", mark=" + mark +
                '}';
    }
}
