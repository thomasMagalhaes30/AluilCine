package fr.iut.aluilcine.entities;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.Min;

/**
 * Represente une entite de base.
 */
public abstract class BaseEntity {
    @Id
    protected String id; // org.bson.types.ObjectId ?

    @Min(0)
    protected int schemaVersion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(int schemaVersion) {
        this.schemaVersion = schemaVersion;
    }
}
