package com.lft.task35.data;

import android.util.Log;

import com.lft.task35.data.model.Film;
import com.lft.task35.utils.FilmComparatorByRating;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
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
            if(film.getName().toLowerCase().contains(query.toLowerCase())){
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
            if(film.getYear()>= startYear && film.getYear() <=endYear){
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
            if(film.getDirector().toLowerCase().contains(name.toLowerCase())){
                foundFilms.add(film);
            }
        }
        return foundFilms;
    }

    @Override
    public List<Film> getTopFilms(int count) {
        Comparator<Film> filmComparator = new FilmComparatorByRating();
        TreeSet<Film> filmTreeSet = new TreeSet<>(filmComparator);
        filmTreeSet.addAll(getAll());
        List<Film> topFilms = new ArrayList<>();
        if(count>filmTreeSet.size())
            count = filmTreeSet.size();
        for(int i = 0; i<count; i++){
            topFilms.add((Film) filmTreeSet.toArray()[i]);
        }
        return  topFilms;
    }

    public long createAndInsertFilmFrom(String name, String director, int year, double rating){
        Film film = new Film();
        film.setName(name);
        film.setDirector(director);
        film.setYear(year);
        film.setRating(rating);
        return insertItem(film);
    }

    public boolean seedDB(){
        try{
            final List<Film> all = getAll();
            for(Film f:all)
                deleteItem(f.getId());
            createAndInsertFilmFrom("Зеленая миля", "Дарабонт", 1999, 9.13);
            createAndInsertFilmFrom("Форрест Гамп", "Земекис", 1994, 9.013);
            createAndInsertFilmFrom("Список Шиндлера", "Спилберг", 1993, 8.88);
            createAndInsertFilmFrom("Начало", "Нолан", 2010, 8.77);
            createAndInsertFilmFrom("1+1", "Накаш", 2011, 8.83);
            createAndInsertFilmFrom("Побег из Шоушенка", "Дарабонт", 1994, 9.19);
            createAndInsertFilmFrom("Иван Васильевич меняет профессию", "Гайдай", 1973, 8.71);
            createAndInsertFilmFrom("Жизнь прекрасна", "Бениньи", 1997, 8.67);
            createAndInsertFilmFrom("Леон", "Бессон", 1994, 8.77);
            createAndInsertFilmFrom("Король лев", "Аллерс", 1994, 8.76);
            createAndInsertFilmFrom("Достучаться до небес", "Ян", 1997, 8.66);
            return true;
        } catch (Exception e){
            Log.e("Debug", "seedDB: ", e);
            return false;
        }


    }


}
