package com.tmdb.api.external.dto;

import java.util.Objects;

public class AddFavoriteRequest extends AddRequest{

    private boolean favorite;

    public AddFavoriteRequest(String media_type, long media_id, boolean favorite) {
        super(media_type, media_id);
        this.favorite = favorite;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddFavoriteRequest that)) return false;
        if (!super.equals(o)) return false;
        return favorite == that.favorite;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), favorite);
    }
}
