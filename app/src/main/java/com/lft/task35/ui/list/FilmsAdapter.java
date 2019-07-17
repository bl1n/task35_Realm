package com.lft.task35.ui.list;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lft.task35.R;
import com.lft.task35.data.model.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmsAdapter extends ListAdapter<Film, FilmViewHolder> {

    private List<Film> mFilms = new ArrayList<>();
    private final FilmsFragment.OnItemClickListener mListener;

    private static final DiffUtil.ItemCallback<Film> DIFF_CALLBACK = new DiffUtil.ItemCallback<Film>() {
        @Override
        public boolean areItemsTheSame(@NonNull Film oldItem, @NonNull Film newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Film oldItem, @NonNull Film newItem) {
            return oldItem.equals(newItem);
        }
    };

    FilmsAdapter(FilmsFragment.OnItemClickListener listener) {

        super(DIFF_CALLBACK);
        mListener= listener;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new FilmViewHolder(LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.li_film, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder filmViewHolder, int i) {
        final Film film = mFilms.get(i);
        filmViewHolder.bind(film);
        filmViewHolder.setListener(mListener);
    }

    @Override
    public int getItemCount() {
        return mFilms.size();
    }

    public void addData(List<Film> films){
        if(films != null){
            mFilms.addAll(films);
            notifyDataSetChanged();
        } else{
            Film film = new Film();
            film.setId(0);
            film.setName("No data");
            film.setYear(0);
            film.setDirector("");
            film.setRating(0.0);
            mFilms.add(film);
            notifyDataSetChanged();
        }
    }
}
