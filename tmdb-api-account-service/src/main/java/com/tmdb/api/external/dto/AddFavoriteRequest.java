package com.tmdb.api.external.dto;

public class AddFavoriteRequest {
    private String media_type;
    private long media_id;
    private boolean favorite;

    public AddFavoriteRequest() {
    }

    public AddFavoriteRequest(String media_type, long media_id, boolean favorite) {
        this.media_type = media_type;
        this.media_id = media_id;
        this.favorite = favorite;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public long getMedia_id() {
        return media_id;
    }

    public void setMedia_id(long media_id) {
        this.media_id = media_id;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
