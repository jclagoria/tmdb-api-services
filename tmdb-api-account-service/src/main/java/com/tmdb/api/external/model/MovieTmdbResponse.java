package com.tmdb.api.external.model;

import java.util.List;
import java.util.Objects;

public class MovieTmdbResponse extends BaseTmdbResponse {

    private List<MovieTmdbItem> results;

    public MovieTmdbResponse() {
    }

    public MovieTmdbResponse(long page, long total_pages, long total_results,
                             List<MovieTmdbItem> results) {
        super(page, total_pages, total_results);
        this.results = results;
    }

    public List<MovieTmdbItem> getResults() {
        return results;
    }

    public void setResults(List<MovieTmdbItem> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieTmdbResponse that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), results);
    }
}
