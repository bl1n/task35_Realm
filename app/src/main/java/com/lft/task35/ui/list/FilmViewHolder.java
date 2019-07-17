package com.lft.task35.ui.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lft.task35.R;
import com.lft.task35.data.model.Film;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilmViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_director)
    TextView tvDirector;
    @BindView(R.id.tv_rating)
    TextView tvRating;

    private View mView;
    private long mFilmId;

    public FilmViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        ButterKnife.bind(this, itemView);
    }

    public void bind(Film film){
        mFilmId = film.getId();
        tvId.setText(String.valueOf(film.getId()));
        tvName.setText(film.getName());
        tvYear.setText(String.valueOf(film.getYear()));
        tvDirector.setText(film.getDirector());
        tvRating.setText(String.valueOf(film.getRating()));
    }

    public void setListener(FilmsFragment.OnItemClickListener listener) {
        mView.setOnClickListener(v -> {
            if(null != listener){
                listener.onClick(mFilmId);
            }
        });
    }
}
