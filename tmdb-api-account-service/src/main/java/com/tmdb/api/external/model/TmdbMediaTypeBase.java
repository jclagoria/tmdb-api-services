package com.tmdb.api.external.model;

import java.util.ArrayList;
import java.util.Objects;

public class TmdbMediaTypeBase {

    private boolean adult;
    private String backdrop_path;
    private ArrayList<Integer> genre_ids;
    private int id;
    private String original_language;
    private String overview;
    private double popularity;
    private String poster_path;
    private double vote_average;
    private int vote_count;

    public TmdbMediaTypeBase() {
    }

    public TmdbMediaTypeBase(boolean adult, String backdrop_path, ArrayList<Integer> genre_ids, int id,
                             String original_language, String overview, double popularity,
                             String poster_path, double vote_average, int vote_count) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.genre_ids = genre_ids;
        this.id = id;
        this.original_language = original_language;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(ArrayList<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TmdbMediaTypeBase that)) return false;
        return adult == that.adult && id == that.id && Double.compare(popularity, that.popularity) == 0
                && Double.compare(vote_average, that.vote_average) == 0 && vote_count == that.vote_count
                && Objects.equals(backdrop_path, that.backdrop_path) && Objects.equals(genre_ids, that.genre_ids)
                && Objects.equals(original_language, that.original_language)
                && Objects.equals(overview, that.overview) && Objects.equals(poster_path, that.poster_path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adult, backdrop_path, genre_ids, id, original_language,
                overview, popularity, poster_path, vote_average, vote_count);
    }
}
