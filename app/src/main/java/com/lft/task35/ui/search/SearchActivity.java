package com.lft.task35.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lft.task35.R;
import com.lft.task35.data.FilmRealmRepository;
import com.lft.task35.data.model.Film;
import com.lft.task35.ui.list.FilmsActivity;
import com.lft.task35.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.et_search_by_name)
    EditText etSearchByName;
    @BindView(R.id.btn_search_by_name)
    Button btnSearchByName;
    @BindView(R.id.et_search_by_year_start_year)
    EditText etSearchByYearStartYear;
    @BindView(R.id.et_search_by_year_end_year)
    EditText etSearchByYearEndYear;
    @BindView(R.id.btn_search_by_year)
    Button btnSearchByYear;
    @BindView(R.id.et_search_by_director)
    EditText etSearchByDirector;
    @BindView(R.id.btn_search_by_director)
    Button btnSearchByDirector;
    @BindView(R.id.et_search_top_films)
    EditText etSearchTopFilms;
    @BindView(R.id.btn_search_top_films)
    Button btnSearchTopFilms;

    FilmRealmRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_search);
        ButterKnife.bind(this);
        mRepository = new FilmRealmRepository();
    }

    @OnClick({R.id.btn_search_by_name, R.id.btn_search_by_year, R.id.btn_search_by_director, R.id.btn_search_top_films})
    public void onViewClicked(View view) {
        List<Long> longs = new ArrayList<>();
        List<Film> search = new ArrayList<>();
        switch (view.getId()) {
            case R.id.btn_search_by_name:
                if (Utils.isCheckedFields(etSearchByName)) {
                    search = mRepository.search(etSearchByName.getText().toString());

                }
                break;
            case R.id.btn_search_by_year:
                if (Utils.isCheckedFields(etSearchByYearStartYear) && Utils.isCheckedFields(etSearchByYearEndYear)) {
                    search = mRepository.searchInBounds(
                            Integer.parseInt(etSearchByYearStartYear.getText().toString()),
                            Integer.parseInt(etSearchByYearEndYear.getText().toString()));
                    Log.d("DEBUG", "onViewClicked: " + search.size());
                }
                break;
            case R.id.btn_search_by_director:
                break;
            case R.id.btn_search_top_films:
                break;
        }
        for (Film f : search)
            longs.add(f.getId());
        Intent intent = new Intent(this, FilmsActivity.class);
        intent.putExtra(FilmsActivity.IDS, (Serializable) longs);
        startActivity(intent);
    }
}
