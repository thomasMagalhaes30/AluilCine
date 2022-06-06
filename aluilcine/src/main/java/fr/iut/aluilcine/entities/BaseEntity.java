package fr.iut.aluilcine.entities;

import org.springframework.data.annotation.Id;

/**
 * Represente une entite de base.
 */
public abstract class BaseEntity {
    @Id
    protected String id; // org.bson.types.ObjectId ?

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
