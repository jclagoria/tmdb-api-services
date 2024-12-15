package com.tmdb.api.external.model;

import java.util.List;
import java.util.Objects;

public class TvEpisodeResponse extends BaseTmdbResponse {

    private List<TvEpisodeItem> results;

    public TvEpisodeResponse() {
    }

    public TvEpisodeResponse(long page, long total_pages, long total_results,
                             List<TvEpisodeItem> results) {
        super(page, total_pages, total_results);
        this.results = results;
    }

    public List<TvEpisodeItem> getResults() {
        return results;
    }

    public void setResults(List<TvEpisodeItem> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TvEpisodeResponse that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), results);
    }
}
