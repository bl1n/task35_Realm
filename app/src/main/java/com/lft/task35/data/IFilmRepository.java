package com.lft.task35.data;

import com.lft.task35.data.model.Film;

import java.util.List;

public interface IFilmRepository {

    Film getItem(long id);

    List<Film> getAll();

    long insertItem(Film t);

    boolean deleteItem(long id);

    void updateItem(Film film);

    List<Film> search(String query);

    List<Film> searchInBounds(int startYear, int endYear);

    List<Film> searchByDirector(String name);

    List<Film> getTopFilms(int count);
}
