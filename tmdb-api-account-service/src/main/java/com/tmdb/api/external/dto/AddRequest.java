package com.tmdb.api.external.dto;

import java.util.Objects;

public class AddRequest {

    private String media_type;
    private long media_id;

    public AddRequest() {
    }

    public AddRequest(String media_type, long media_id) {
        this.media_type = media_type;
        this.media_id = media_id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddRequest that)) return false;
        return media_id == that.media_id && Objects.equals(media_type, that.media_type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(media_type, media_id);
    }
}
