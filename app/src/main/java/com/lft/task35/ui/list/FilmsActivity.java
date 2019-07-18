package com.lft.task35.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.lft.task35.common.SingleFragmentActivity;
import com.lft.task35.ui.update_film.UpdateDeleteFilmActivity;

public class FilmsActivity extends SingleFragmentActivity implements FilmsFragment.OnItemClickListener {


    public static final String FILM_ID = "FILM_ID";

    @Override
    protected Fragment getFragment() {
        Bundle bundle = getIntent().getExtras();
        return FilmsFragment.newInstance(bundle);
    }

    @Override
    public void onClick(long filmId) {
        Intent intent = new Intent(this, UpdateDeleteFilmActivity.class);
        intent.putExtra(FILM_ID, filmId);
        startActivity(intent);
        Log.d("Debug", "onClick: " + filmId);
    }
}
