package com.tmdb.api.external.dto;

import java.util.Objects;

public class AddToWatchListRequest extends AddRequest {

    private boolean watchlist;

    public AddToWatchListRequest(String media_type, long media_id, boolean watchlist) {
        super(media_type, media_id);
        this.watchlist = watchlist;
    }

    public boolean isWatchlist() {
        return watchlist;
    }

    public void setWatchlist(boolean watchlist) {
        this.watchlist = watchlist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddToWatchListRequest that)) return false;
        if (!super.equals(o)) return false;
        return watchlist == that.watchlist;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), watchlist);
    }
}
