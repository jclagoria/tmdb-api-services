package com.tmdb.api.external.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FavoriteTvItem extends FavoriteMediaTypeBase {

    private String first_air_date;
    private String name;
    private List<String> origin_country;

    public FavoriteTvItem() {
    }

    public FavoriteTvItem(boolean adult, String backdrop_path, ArrayList<Integer> genre_ids, int id,
                          String original_language, String original_title, String overview, double popularity,
                          String poster_path, double vote_average, int vote_count,
                          String first_air_date, String name, List<String> origin_country) {
        super(adult, backdrop_path, genre_ids, id, original_language,
                original_title, overview, popularity, poster_path,
                vote_average, vote_count);
        this.first_air_date = first_air_date;
        this.name = name;
        this.origin_country = origin_country;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(List<String> origin_country) {
        this.origin_country = origin_country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavoriteTvItem that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(first_air_date, that.first_air_date)
                && Objects.equals(name, that.name)
                && Objects.equals(origin_country, that.origin_country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), first_air_date, name, origin_country);
    }
}