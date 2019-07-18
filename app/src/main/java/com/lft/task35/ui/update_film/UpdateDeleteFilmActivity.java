package com.lft.task35.ui.update_film;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lft.task35.R;
import com.lft.task35.data.FilmRealmRepository;
import com.lft.task35.data.model.Film;
import com.lft.task35.ui.MainActivity;
import com.lft.task35.ui.list.FilmsActivity;
import com.lft.task35.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateDeleteFilmActivity extends AppCompatActivity {
    @BindView(R.id.et_id)
    EditText etId;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_year)
    EditText etYear;
    @BindView(R.id.et_director)
    EditText etDirector;
    @BindView(R.id.et_rating)
    EditText etRating;
    @BindView(R.id.btn_add_new)
    Button btnAddNew;
    @BindView(R.id.btn_delete)
    Button btnDelete;

    private long mFilmId;
    private FilmRealmRepository mRepository;
// TODO: 18.07.2019  add delete button


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_film);
        ButterKnife.bind(this);
        mFilmId = getIntent().getLongExtra(FilmsActivity.FILM_ID, 1);
        mRepository = new FilmRealmRepository();
        btnAddNew.setText(getResources().getText(R.string.update));
        btnDelete.setVisibility(View.VISIBLE);

        bindFields();
    }

    private void bindFields() {
        Film film = mRepository.getItem(mFilmId);
        etId.setText(String.valueOf(film.getId()));
        etName.setText(film.getName());
        etYear.setText(String.valueOf(film.getYear()));
        etDirector.setText(film.getDirector());
        etRating.setText(String.valueOf(film.getRating()));
    }


    @OnClick({R.id.btn_add_new, R.id.btn_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add_new:
                if (Utils.isCheckedFields(etName, etDirector, etYear, etRating)) {
                    Film film = mRepository.getItem(mFilmId);
                    film.setName(etName.getText().toString());
                    film.setDirector(etDirector.getText().toString());
                    film.setRating(Double.parseDouble(etRating.getText().toString()));
                    film.setYear(Integer.parseInt(etYear.getText().toString()));
                    mRepository.updateItem(film);
                    Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                } else
                    Toast.makeText(this, "Input error", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delete:{
                mRepository.deleteItem(Long.parseLong(etId.getText().toString()));
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                break;
            }

        }
    }
}
