package com.debdroid.recyclerviewwithdatabinding.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.debdroid.recyclerviewwithdatabinding.model.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by debashispaul on 29/12/2017.
 */

public class MovieItemViewModel extends BaseObservable {
    private Movie movie;

    public MovieItemViewModel(Movie movie) {
        this.movie = movie;
    }

    @Bindable
    public String getMovieName() {
        return movie.getMovieName();
    }

    @Bindable
    public String getMovieGenre() {
        return movie.getMovieGenre();
    }

    @Bindable
    public String getMovieReleaseDate() {
        return movie.getMovieReleaseDate();
    }

    @Bindable
    public String getMoviePosterPath() {
        return movie.getMoviePosterPath();
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        // Notify the listeners that all properties of this instance have changed
        // This is important otherwise the data will not be refreshed in the RecyclerView
        notifyChange();
    }

    // BindingAdapter is applied to methods that are used to manipulate how values with expressions are set to views.
    // 'imageUrl' attribute is used in the view for ImageView (i.e. app:imageUrl="@{movieItemViewModel.moviePosterPath}")
    // the expression receives the url from getMoviePosterPath as String. This adapter will be called when 'imageUrl' is used
    // and the expression returns 'String' which is the case here.
    // ** Note that the method has to be static!!
    @BindingAdapter("imageUrl")
    public static void loadImageUrl (ImageView imageView, String moviePosterURl) {
        Picasso.with(imageView.getContext()).load(moviePosterURl).into(imageView);
    }
}
