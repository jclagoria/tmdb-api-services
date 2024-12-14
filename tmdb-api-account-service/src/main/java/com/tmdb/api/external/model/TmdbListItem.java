package com.tmdb.api.external.model;

import java.util.Objects;

public class TmdbListItem {

    private String description;
    private long favorite_count;
    private long in;
    private long item_count;
    private String iso_639_1;
    private String list_type;
    private String name;
    private String poster_path;

    public TmdbListItem() {
    }

    public TmdbListItem(String description, long favorite_count, long in, long item_count,
                        String iso_639_1, String list_type, String name, String poster_path) {
        this.description = description;
        this.favorite_count = favorite_count;
        this.in = in;
        this.item_count = item_count;
        this.iso_639_1 = iso_639_1;
        this.list_type = list_type;
        this.name = name;
        this.poster_path = poster_path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(long favorite_count) {
        this.favorite_count = favorite_count;
    }

    public long getIn() {
        return in;
    }

    public void setIn(long in) {
        this.in = in;
    }

    public long getItem_count() {
        return item_count;
    }

    public void setItem_count(long item_count) {
        this.item_count = item_count;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getList_type() {
        return list_type;
    }

    public void setList_type(String list_type) {
        this.list_type = list_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TmdbListItem that)) return false;
        return favorite_count == that.favorite_count && in == that.in
                && item_count == that.item_count && Objects.equals(description, that.description)
                && Objects.equals(iso_639_1, that.iso_639_1) && Objects.equals(list_type, that.list_type)
                && Objects.equals(name, that.name) && Objects.equals(poster_path, that.poster_path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, favorite_count, in, item_count, iso_639_1, list_type, name, poster_path);
    }
}
