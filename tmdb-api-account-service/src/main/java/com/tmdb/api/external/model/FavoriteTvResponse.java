package com.tmdb.api.external.model;

import java.util.List;
import java.util.Objects;

public class FavoriteTvResponse extends BaseTmdbResponse {

    private List<FavoriteTvItem> results;

    public FavoriteTvResponse() {
    }

    public FavoriteTvResponse(long page, long total_pages, long total_results, List<FavoriteTvItem> results) {
        super(page, total_pages, total_results);
        this.results = results;
    }

    public List<FavoriteTvItem> getResults() {
        return results;
    }

    public void setResults(List<FavoriteTvItem> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavoriteTvResponse that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), results);
    }
}
