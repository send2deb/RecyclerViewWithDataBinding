package com.debdroid.recyclerviewwithdatabinding.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.debdroid.recyclerviewwithdatabinding.R;
import com.debdroid.recyclerviewwithdatabinding.databinding.SingleMovieItemBinding;
import com.debdroid.recyclerviewwithdatabinding.model.Movie;
import com.debdroid.recyclerviewwithdatabinding.viewmodel.MovieItemViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by debashispaul on 29/12/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {
    private List<Movie> mMovieList;
    private final String LOG_TAG = MovieAdapter.class.getSimpleName();

    public MovieAdapter () {
        mMovieList = Collections.emptyList();
    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v(LOG_TAG, "onCreateViewHolder is called");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SingleMovieItemBinding singleMovieItemBinding = DataBindingUtil.inflate(inflater,
                R.layout.single_movie_item, parent, false);
        return new MovieAdapterViewHolder(singleMovieItemBinding);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        Log.v(LOG_TAG, "onBindViewHolder: Position ->" + position);
        final Movie movie = mMovieList.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
//        Log.v(LOG_TAG, "getItemCount: Count -> " + mMovieList.size());
        return mMovieList.size();
    }

    // Method to set the Movie list of the adapter
    public void setMovieList(List<Movie> movieList) {
        mMovieList = movieList;
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder {

        private final SingleMovieItemBinding singleMovieItemBinding;

        public MovieAdapterViewHolder(SingleMovieItemBinding singleMovieItemBinding) {
            super(singleMovieItemBinding.getRoot());
            this.singleMovieItemBinding = singleMovieItemBinding;
        }

        public void bind (Movie movie) {
            // Check if the MovieItemViewModel exists or not - then take action
            if( singleMovieItemBinding.getMovieItemViewModel() == null) {
                // Does not exist, so create a new one
                Log.v(LOG_TAG, "bind is called: ItemViewModel is null");
                MovieItemViewModel movieItemViewModel = new MovieItemViewModel(movie);
                singleMovieItemBinding.setMovieItemViewModel(movieItemViewModel);
            } else {
                // MovieItemViewModel exists, so set the movie
                Log.v(LOG_TAG, "bind is called: ItemViewModel is NOT null");
                singleMovieItemBinding.getMovieItemViewModel().setMovie(movie);
                // TODO Following line is experimental / Testing purpose
//                singleMovieItemBinding.executePendingBindings();
            }
        }
    }
}
