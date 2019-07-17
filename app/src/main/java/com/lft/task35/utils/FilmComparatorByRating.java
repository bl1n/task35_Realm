package com.lft.task35.utils;

import com.lft.task35.data.model.Film;

import java.util.Comparator;

public class FilmComparatorByRating implements Comparator<Film> {
    @Override
    public int compare(Film a, Film b) {
        if(a.getRating()>b.getRating())
            return -1;
        else if(a.getRating() == b.getRating())
            return 0;
        else
            return 1;
    }
}
