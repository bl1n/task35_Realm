package com.lft.task35.ui.add_new;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lft.task35.R;
import com.lft.task35.data.FilmRealmRepository;
import com.lft.task35.ui.MainActivity;
import com.lft.task35.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewActivity extends AppCompatActivity {

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
    @BindView(R.id.ll_id)
    LinearLayout llId;

    private FilmRealmRepository mRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_film);
        mRepository = new FilmRealmRepository();
        ButterKnife.bind(this);
        llId.setVisibility(View.GONE);
        btnAddNew.setText("Add");
    }

    @OnClick(R.id.btn_add_new)
    public void onViewClicked() {
        if (Utils.isCheckedFields(etName,etDirector,etYear,etRating)) {
            mRepository.createAndInsertFilmFrom(etName.getText().toString(),
                    etDirector.getText().toString(),
                    Integer.parseInt(etYear.getText().toString()),
                    Double.parseDouble(etRating.getText().toString()));
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        } else
            Toast.makeText(this, "Input error", Toast.LENGTH_LONG).show();
    }


//    private boolean isCheckedFields() {
//        return !TextUtils.isEmpty(etName.getText()) && !TextUtils.isEmpty(etDirector.getText())
//                && !TextUtils.isEmpty(etYear.getText()) && !TextUtils.isEmpty(etRating.getText());
//    }
}
