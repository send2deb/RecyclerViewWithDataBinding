package com.debdroid.recyclerviewwithdatabinding.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.debdroid.recyclerviewwithdatabinding.R;
import com.debdroid.recyclerviewwithdatabinding.databinding.ActivityMainBinding;
import com.debdroid.recyclerviewwithdatabinding.viewmodel.MovieViewModel;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the Data Binding and set the view model
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MovieViewModel movieViewModel = new MovieViewModel();
        activityMainBinding.setMovieViewModel(movieViewModel);

        mRecyclerView = activityMainBinding.rvMovieRecyclerView;
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mMovieAdapter = new MovieAdapter();
        mMovieAdapter.setMovieList(movieViewModel.getMovieList());
        mRecyclerView.setAdapter(mMovieAdapter);
    }
}
