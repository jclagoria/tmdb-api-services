package com.tmdb.api.external.model;

import java.util.List;
import java.util.Objects;

public class TvTmdbResponse extends BaseTmdbResponse {

    private List<TvTmdbItem> results;

    public TvTmdbResponse() {
    }

    public TvTmdbResponse(long page, long total_pages, long total_results, List<TvTmdbItem> results) {
        super(page, total_pages, total_results);
        this.results = results;
    }

    public List<TvTmdbItem> getResults() {
        return results;
    }

    public void setResults(List<TvTmdbItem> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TvTmdbResponse that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), results);
    }
}
