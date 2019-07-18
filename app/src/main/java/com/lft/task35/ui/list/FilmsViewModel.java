package com.lft.task35.ui.list;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.lft.task35.data.IFilmRepository;
import com.lft.task35.data.model.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmsViewModel extends ViewModel {
    private static final String ALL_FILMS = "ALL_FILMS";
    private static final String SEARCH_BY_NAME = "SEARCH_BY_NAME";
    private static final String SEARCH_BY_DIRECTOR = "SEARCH_BY_DIRECTOR";
    private static final String SEARCH_BY_YEARS = "SEARCH_BY_YEARS";
    private static final String SEARCH_TOP_FILMS = "SEARCH_TOP_FILMS";

    private IFilmRepository mRepository;

    private MutableLiveData<List<Film>> mData = new MutableLiveData<>();

    public FilmsViewModel(IFilmRepository repository) {
        mRepository = repository;
    }

    public MutableLiveData<List<Film>> getData(String method, String query) {

        switch (method){
            case ALL_FILMS:{
                mData.postValue(mRepository.getAll());
                break;
            }
            case SEARCH_BY_NAME:{
                mData.postValue(mRepository.search(query));
                break;
            }
            case SEARCH_BY_DIRECTOR:{
                mData.postValue(mRepository.searchByDirector(query));
                break;
            }
            case SEARCH_BY_YEARS:{
                String[] years = query.split("/");
                mData.postValue(mRepository.searchInBounds(Integer.parseInt(years[0]),Integer.parseInt(years[1])));
                break;
            }
            case SEARCH_TOP_FILMS:{
                mData.postValue(mRepository.getTopFilms(Integer.parseInt(query)));
                break;
            }
        }

        return mData;


/*
        if(ids == null){
            mData.postValue(mRepository.getAll());
        } else if(ids != null && ids.size() == 0){
            mData.postValue(new ArrayList<>());
        } else if(ids != null && ids.size()>0){
            List<Film> films = new ArrayList<>();
            for (long i : ids) {
                films.add(mRepository.getItem(i));
            }
            mData.postValue(films);
        }
        return mData;*/



    }
}
