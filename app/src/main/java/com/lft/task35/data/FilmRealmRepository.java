package com.lft.task35.data;

import com.lft.task35.data.model.Film;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;

public class FilmRealmRepository implements IFilmRepository {

    private Realm mRealm;

    private static AtomicLong sPrimaryId;

    public FilmRealmRepository() {
        mRealm = Realm.getDefaultInstance();
        Number max = mRealm.where(Film.class).max("id");
        sPrimaryId = max == null? new AtomicLong(0): new AtomicLong(max.longValue());
    }

    @Override
    public Film getItem(long id) {
        Film film = getRealmAssociatedFilm(id);
        return film != null? mRealm.copyFromRealm(film): null;
    }

    private Film getRealmAssociatedFilm(long id) {
        return mRealm.where(Film.class).equalTo("id", id).findFirst();
    }

    @Override
    public List<Film> getAll() {
        return mRealm.where(Film.class).findAll();
    }

    @Override
    public long insertItem(Film film) {
        film.setId(sPrimaryId.incrementAndGet());
        mRealm.beginTransaction();
        mRealm.copyToRealm(film);
        mRealm.commitTransaction();
        return sPrimaryId.longValue();
    }

    @Override
    public boolean deleteItem(long id) {
        boolean isDeletedSuccessful;
        mRealm.beginTransaction();
        Film film = getRealmAssociatedFilm(id);
        if(film!=null){
            film.deleteFromRealm();
            isDeletedSuccessful = true;
        } else{
            isDeletedSuccessful = false;
        }

        mRealm.commitTransaction();
        return isDeletedSuccessful;

    }

    @Override
    public void updateItem(Film film) {

        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(film);
        mRealm.commitTransaction();
    }

    @Override
    public List<Film> search(String query) {
        List<Film> foundFilms = new ArrayList<>();
        List<Film> filmsInRealm = getAll();
        for(Film film :filmsInRealm){
            if(film.getName().contains(query)){
                foundFilms.add(film);
            }
        }
        return foundFilms;
    }

    @Override
    public List<Film> searchInBounds(int startYear, int endYear) {
        List<Film> foundFilms = new ArrayList<>();
        List<Film> filmsInRealm = getAll();
        for(Film film :filmsInRealm){
            if(film.getYear()>startYear && film.getYear()<endYear){
                foundFilms.add(film);
            }
        }
        return foundFilms;
    }

    @Override
    public List<Film> searchByDirector(String name) {
        List<Film> foundFilms = new ArrayList<>();
        List<Film> filmsInRealm = getAll();
        for(Film film :filmsInRealm){
            if(film.getDirector().contains(name)){
                foundFilms.add(film);
            }
        }
        return foundFilms;
    }

    @Override
    public List<Film> getTopFilms(int count) {
        List<Film> foundFilms = new ArrayList<>();
        List<Film> filmsInRealm = getAll();
        // TODO: 15.07.2019 add compare methods 
        return foundFilms;
    }
}
