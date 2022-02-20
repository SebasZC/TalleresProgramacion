package com.example.springbootvuepostgresql.model;

import javax.persistence.*;
@Entity
@Table(name = "employees")
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String title;
    @Column(name = "lastName")
    private String description;
    @Column(name = "Activated")
    private boolean published;
    public Tutorial() {
    }
    public Tutorial(String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }
    public long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isPublished() {
        return published;
    }
    public void setPublished(boolean isPublished) {
        this.published = isPublished;
    }
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + title + ", lastName=" + description + ", activated=" + published + "]";
    }
}