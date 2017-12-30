package com.debdroid.recyclerviewwithdatabinding.viewmodel;

import android.databinding.BaseObservable;
import android.util.Log;

import com.debdroid.recyclerviewwithdatabinding.model.Movie;
import com.debdroid.recyclerviewwithdatabinding.util.FakeMovieData;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by debashispaul on 29/12/2017.
 */

public class MovieViewModel extends java.util.Observable {
    private final String LOG_CAT = MovieViewModel.class.getSimpleName();
    private List<Movie> movieList;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MovieViewModel () {}

    public List<Movie> getMovieList () {
        movieList = FakeMovieData.getFakeMovieList();
        return movieList;
    }

    public void loadMoreMoviesUsingRx () {

        DisposableObserver<Movie> disposableObserver = Observable.fromArray(FakeMovieData.getMoreFakeMovieList())
                .flatMapIterable(x -> x)
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Movie>() {
                    @Override
                    public void onNext(Movie movie) {
                        Log.v(LOG_CAT, "onNext:Received new movie ->" + movie.getMovieName());
                        movieList.add(movie);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.v(LOG_CAT, "onComplete is called");
                        // This will set the hasChanged flag to true
                        setChanged();
                        // This will call the update method of Observer (i.e. MainActivity in this case)
                        notifyObservers();
                    }
                });

        compositeDisposable.add(disposableObserver);
    }

    public void houseKeeping() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
