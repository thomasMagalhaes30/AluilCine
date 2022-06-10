package fr.iut.aluilcine.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

/**
 * Represente un cinema
 */
@Document(collection = "cinema")
public class Cinema extends BaseEntity{

    @Min(0)
    private int schemaVersion;

    @NotBlank
    @NotNull
    @Size(min=2,max=128)
    private String name;

    @NotBlank
    @NotNull
    @Size(min=2,max=128)
    private String location;

    @NotNull
    @Min(-180)
    @Max(180)
    private float longitude;

    @NotNull
    @Min(-90)
    @Max(90)
    private float latitude;

    public Cinema() {}

    public Cinema(int schemaVersion, String name, String location, float longitude, float latitude) {
        this.schemaVersion = schemaVersion;
        this.name = name;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(int schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Cinema{" +
                ", schemaVersion=" + schemaVersion +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
