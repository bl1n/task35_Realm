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
import io.realm.internal.Util;

public class SearchActivity extends AppCompatActivity {

    public static final String METHOD = "METHOD";
    public static final String QUERY = "QUERY";

    private static final String ALL_FILMS = "ALL_FILMS";
    private static final String SEARCH_BY_NAME = "SEARCH_BY_NAME";
    private static final String SEARCH_BY_DIRECTOR = "SEARCH_BY_DIRECTOR";
    private static final String SEARCH_BY_YEARS = "SEARCH_BY_YEARS";
    private static final String SEARCH_TOP_FILMS = "SEARCH_TOP_FILMS";

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_search);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_search_by_name, R.id.btn_search_by_year, R.id.btn_search_by_director, R.id.btn_search_top_films})
    public void onViewClicked(View view) {
        List<Long> longs = new ArrayList<>();
        List<Film> search = new ArrayList<>();
        Intent intent = new Intent(this, FilmsActivity.class);
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.btn_search_by_name:
                if (Utils.isCheckedFields(etSearchByName)) {
                    bundle.putString(METHOD, SEARCH_BY_NAME);
                    bundle.putString(QUERY, etSearchByName.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else Toast.makeText(this, "Input error", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_search_by_year:
                if (Utils.isCheckedFields(etSearchByYearStartYear) && Utils.isCheckedFields(etSearchByYearEndYear)) {
                    String query = etSearchByYearStartYear.getText().toString()
                            + "/"
                            + etSearchByYearEndYear.getText().toString();
                    bundle.putString(METHOD, SEARCH_BY_YEARS);
                    bundle.putString(QUERY, query);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else Toast.makeText(this, "Input error", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_search_by_director:
                if (Utils.isCheckedFields(etSearchByDirector)) {
                    bundle.putString(METHOD, SEARCH_BY_DIRECTOR);
                    bundle.putString(QUERY, etSearchByDirector.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else Toast.makeText(this, "Input error", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_search_top_films:
                if (Utils.isCheckedFields(etSearchTopFilms)) {
                    bundle.putString(METHOD, SEARCH_TOP_FILMS);
                    bundle.putString(QUERY, etSearchTopFilms.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else Toast.makeText(this, "Input error", Toast.LENGTH_SHORT).show();
                break;
        }


        Log.d("DEBUG", "onViewClicked: " + search.size());


    }
}
