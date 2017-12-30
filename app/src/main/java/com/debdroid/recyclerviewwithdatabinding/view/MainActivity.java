package com.debdroid.recyclerviewwithdatabinding.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.debdroid.recyclerviewwithdatabinding.R;
import com.debdroid.recyclerviewwithdatabinding.databinding.ActivityMainBinding;
import com.debdroid.recyclerviewwithdatabinding.viewmodel.MovieViewModel;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {
    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private MovieViewModel mMovieViewModel;
    private ActivityMainBinding mActivityMainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the Data Binding and set the view model
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMovieViewModel = new MovieViewModel();
        mActivityMainBinding.setMovieViewModel(mMovieViewModel);

        // Set the observer
        mMovieViewModel.addObserver(this);

        // Set the RecyclerView
        mRecyclerView = mActivityMainBinding.rvMovieRecyclerView;
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mMovieAdapter = new MovieAdapter();
        mRecyclerView.setAdapter(mMovieAdapter);

        // Load initial movies using regular way
        mMovieAdapter.setMovieList(mMovieViewModel.getMovieList());

        // Load some movies using Reactive Programming (RxAndroid)
        mMovieViewModel.loadMoreMoviesUsingRx();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMovieViewModel.houseKeeping();
    }

    // This update method will be called when the class (MovieViewModel) will call 'notifyObservers()'
    @Override
    public void update(Observable o, Object arg) {
        if( o instanceof MovieViewModel) {
            final MovieAdapter adapter = (MovieAdapter) mActivityMainBinding.rvMovieRecyclerView.getAdapter();
            adapter.notifyDataSetChanged();
        }
    }
}
