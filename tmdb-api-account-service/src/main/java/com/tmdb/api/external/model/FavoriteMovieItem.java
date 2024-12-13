package com.tmdb.api.external.model;

import java.util.ArrayList;
import java.util.Objects;

public class FavoriteMovieItem extends FavoriteMediaTypeBase {

    public String release_date;
    public String title;
    private boolean video;

    public FavoriteMovieItem() {
    }

    public FavoriteMovieItem(boolean adult, String backdrop_path, ArrayList<Integer> genre_ids, int id,
                             String original_language, String original_title, String overview,
                             double popularity, String poster_path, double vote_average, int vote_count,
                             String release_date, String title, boolean video) {
        super(adult, backdrop_path, genre_ids, id, original_language, original_title,
                overview, popularity, poster_path, vote_average, vote_count);
        this.release_date = release_date;
        this.title = title;
        this.video = video;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavoriteMovieItem that)) return false;
        if (!super.equals(o)) return false;
        return video == that.video && Objects.equals(release_date, that.release_date)
                && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), release_date, title, video);
    }
}