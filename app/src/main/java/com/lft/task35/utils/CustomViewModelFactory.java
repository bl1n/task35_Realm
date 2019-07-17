package com.lft.task35.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.lft.task35.data.IFilmRepository;
import com.lft.task35.ui.list.FilmsViewModel;

public class CustomViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private IFilmRepository mRepository;

    public CustomViewModelFactory(IFilmRepository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(FilmsViewModel.class)){
            return (T) new FilmsViewModel(mRepository);
        }
        throw new IllegalArgumentException("Wrong ViewModel class");
    }
}
