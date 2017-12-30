package com.debdroid.recyclerviewwithdatabinding.viewmodel;

import android.databinding.BaseObservable;

import com.debdroid.recyclerviewwithdatabinding.model.Movie;
import com.debdroid.recyclerviewwithdatabinding.util.FakeMovieData;

import java.util.List;

/**
 * Created by debashispaul on 29/12/2017.
 */

public class MovieViewModel extends BaseObservable {

    public MovieViewModel () {}

    public List<Movie> getMovieList () {
        List<Movie> movieList = FakeMovieData.getFakeMovieList();
        return movieList;
    }
}
