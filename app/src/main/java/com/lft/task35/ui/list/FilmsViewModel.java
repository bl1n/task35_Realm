package com.lft.task35.ui.list;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.lft.task35.data.IFilmRepository;
import com.lft.task35.data.model.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmsViewModel extends ViewModel {

    private IFilmRepository mRepository;

    private MutableLiveData<List<Film>> mData = new MutableLiveData<>();

    public FilmsViewModel(IFilmRepository repository) {
        mRepository = repository;
    }

    public MutableLiveData<List<Film>> getData(@Nullable List<Long> ids) {
        if (ids != null && ids.size() > 0) {
            List<Film> films = new ArrayList<>();
            for(long i:ids){
                films.add(mRepository.getItem(i));
            }
            mData.postValue(films);
        } else
            mData.postValue(mRepository.getAll());
        return mData;
    }
}
