package com.lft.task35.ui.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lft.task35.R;
import com.lft.task35.data.FilmRealmRepository;
import com.lft.task35.utils.CustomViewModelFactory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FilmsFragment extends Fragment {

    public static final String METHOD = "METHOD";
    public static final String QUERY = "QUERY";
    private static final String ALL_FILMS = "ALL_FILMS";



    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    Unbinder unbinder;
    @BindView(R.id.tv_mock)
    TextView tvMock;

    private FilmsViewModel mViewModel;
    private FilmsAdapter mAdapter;
    private OnItemClickListener mListener;

    public static FilmsFragment newInstance(Bundle bundle) {
        FilmsFragment fragment = new FilmsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public FilmsFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemClickListener) {
            mListener = (OnItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnItemClickListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomViewModelFactory factory = new CustomViewModelFactory(new FilmRealmRepository());
        mViewModel = ViewModelProviders.of(this, factory).get(FilmsViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new FilmsAdapter(mListener);
        String method, query;
        if (getArguments() != null) {
            final Bundle bundle = getArguments();
            method = bundle.getString(METHOD);
            query = bundle.getString(QUERY);
        } else {
            method = ALL_FILMS;
            query = "";
        }


        mViewModel.getData(method,query).observe(this, films -> {
//            if (films != null && films.size() == 0) {
//                initEmptyUi();
//                Log.d("Debug", "onActivityCreated: null list ");
//            }

            mAdapter.addData(films);
        });
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(mAdapter);
    }

    private void initEmptyUi() {
        tvMock.setVisibility(View.VISIBLE);
        mRecycler.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnItemClickListener {
        void onClick(long filmId);
    }
}
