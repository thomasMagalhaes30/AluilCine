package fr.iut.aluilcine.entities;

import org.springframework.data.annotation.Id;

/**
 * Represente un cinema
 */
public class Cinema {
    @Id
    public String id;

    public int schemaVersion;

    public String name;

    public String location;

    public float longitude;

    public float latitude;

    public Cinema() {}

    public Cinema(String id, int schemaVersion, String name, String location, double longitude, double latitude) {
        this.id = id;
        this.schemaVersion = schemaVersion;
        this.name = name;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Cinema{" +
                "id='" + id + '\'' +
                ", schemaVersion=" + schemaVersion +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
