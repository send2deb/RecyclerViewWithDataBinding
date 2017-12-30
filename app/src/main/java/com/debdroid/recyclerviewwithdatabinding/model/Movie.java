package com.debdroid.recyclerviewwithdatabinding.model;


/**
 * Created by debashispaul on 29/12/2017.
 */

public class Movie {
    private String movieName;
    private String movieGenre;
    private String movieReleaseDate;
    private String moviePosterPath;

    public Movie(String movieName, String movieGenre, String movieReleaseDate, String moviePosterPath) {
        this.movieName = movieName;
        this.movieGenre = movieGenre;
        this.movieReleaseDate = movieReleaseDate;
        this.moviePosterPath = moviePosterPath;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMoviePosterPath() {
        return moviePosterPath;
    }

    public void setMoviePosterPath(String moviePosterPath) {
        this.moviePosterPath = moviePosterPath;
    }
}
