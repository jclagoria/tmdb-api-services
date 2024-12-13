package com.tmdb.api.external.model;

import java.util.ArrayList;
import java.util.Objects;

public class FavoriteResponse {

    public long page;
    public long total_pages;
    public long total_results;

    public FavoriteResponse() {
    }

    public FavoriteResponse(long page, long total_pages, long total_results) {
        this.page = page;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(long total_pages) {
        this.total_pages = total_pages;
    }

    public long getTotal_results() {
        return total_results;
    }

    public void setTotal_results(long total_results) {
        this.total_results = total_results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavoriteResponse that)) return false;
        return page == that.page && total_pages == that.total_pages
                && total_results == that.total_results;
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, total_pages, total_results);
    }
}
