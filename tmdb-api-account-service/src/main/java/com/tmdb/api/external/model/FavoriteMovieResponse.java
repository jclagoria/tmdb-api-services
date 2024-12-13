package com.tmdb.api.external.model;

import java.util.List;
import java.util.Objects;

public class FavoriteMovieResponse extends FavoriteResponse {

    private List<FavoriteMovieItem> results;

    public FavoriteMovieResponse() {
    }

    public FavoriteMovieResponse(long page, long total_pages, long total_results,
                                 List<FavoriteMovieItem> results) {
        super(page, total_pages, total_results);
        this.results = results;
    }

    public List<FavoriteMovieItem> getResults() {
        return results;
    }

    public void setResults(List<FavoriteMovieItem> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavoriteMovieResponse that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), results);
    }
}
