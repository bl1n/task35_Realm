package com.lft.task35.ui.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lft.task35.R;
import com.lft.task35.data.FilmRealmRepository;
import com.lft.task35.data.model.Film;
import com.lft.task35.utils.CustomViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FilmsFragment extends Fragment {


    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    Unbinder unbinder;

    private FilmsViewModel mViewModel;
    private FilmsAdapter mAdapter;
    private OnItemClickListener mListener;
    private List<Long> mIDS;

    public static FilmsFragment newInstance(Bundle args) {
        FilmsFragment fragment = new FilmsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public FilmsFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnItemClickListener){
            mListener = (OnItemClickListener) context;
        }else {
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
        if(getArguments()!=null){
            final Bundle arguments = getArguments();
            mIDS = (List<Long>) arguments.getSerializable("IDS");
        }
        else
            mIDS = new ArrayList<>();
        mViewModel.getData(mIDS).observe(this, films-> mAdapter.addData(films));
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnItemClickListener{
        void onClick(long filmId);
    }
}
