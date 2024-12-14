package com.tmdb.api.external.model;

import java.util.List;
import java.util.Objects;

public class TmdbListResponse extends BaseTmdbResponse {

    private List<TmdbListItem> results;

    public TmdbListResponse() {
    }

    public TmdbListResponse(long page, long total_pages,
                            long total_results, List<TmdbListItem> results) {
        super(page, total_pages, total_results);
        this.results = results;
    }

    public List<TmdbListItem> getResults() {
        return results;
    }

    public void setResults(List<TmdbListItem> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TmdbListResponse that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), results);
    }
}
