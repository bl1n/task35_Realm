package com.lft.task35.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lft.task35.R;
import com.lft.task35.data.FilmRealmRepository;
import com.lft.task35.ui.add_new.AddNewActivity;
import com.lft.task35.ui.list.FilmsActivity;
import com.lft.task35.ui.search.SearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list)
    Button list;
    @BindView(R.id.btn_add_new)
    Button btnAddNew;
    @BindView(R.id.btn_default_list)
    Button btnDefaultList;
    @BindView(R.id.btn_search)
    Button btnSearch;

    FilmRealmRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        mRepository = new FilmRealmRepository();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.list, R.id.btn_add_new, R.id.btn_default_list, R.id.btn_search})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.list:
                final Intent listIntent = new Intent(this, FilmsActivity.class);
                startActivity(listIntent);
                break;
            case R.id.btn_add_new:
                final Intent addNewIntent = new Intent(this, AddNewActivity.class);
                startActivity(addNewIntent);
                break;
            case R.id.btn_default_list:
                if (mRepository.seedDB())
                    Toast.makeText(this, "Ready", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_search:
                final Intent searchIntent = new Intent(this, SearchActivity.class);
                startActivity(searchIntent);
                break;
        }
    }
}
