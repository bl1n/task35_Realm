package com.lft.task35.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Film extends RealmObject {
    @PrimaryKey
    private long id;

    private String name;

    private int year;

    private String director;

    private Double rating;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", director='" + director + '\'' +
                ", rating=" + rating +
                '}';
    }

}
