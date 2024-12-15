package com.tmdb.api.external.model;

import java.util.Objects;

public class TvEpisodeItem {

    private String air_date;
    private int episode_number;
    private String episode_type;
    private int id;
    private String name;
    private String overview;
    private String production_code;
    private int runtime;
    private int season_number;
    private int show_id;
    private String still_path;
    private double vote_average;
    private int vote_count;
    private int rating;

    public TvEpisodeItem() {
    }

    public TvEpisodeItem(String air_date, int episode_number, String episode_type, int id, String name,
                         String overview, String production_code, int runtime, int season_number, int show_id,
                         String still_path, double vote_average, int vote_count, int rating) {
        this.air_date = air_date;
        this.episode_number = episode_number;
        this.episode_type = episode_type;
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.production_code = production_code;
        this.runtime = runtime;
        this.season_number = season_number;
        this.show_id = show_id;
        this.still_path = still_path;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.rating = rating;
    }

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public int getEpisode_number() {
        return episode_number;
    }

    public void setEpisode_number(int episode_number) {
        this.episode_number = episode_number;
    }

    public String getEpisode_type() {
        return episode_type;
    }

    public void setEpisode_type(String episode_type) {
        this.episode_type = episode_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getProduction_code() {
        return production_code;
    }

    public void setProduction_code(String production_code) {
        this.production_code = production_code;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getSeason_number() {
        return season_number;
    }

    public void setSeason_number(int season_number) {
        this.season_number = season_number;
    }

    public int getShow_id() {
        return show_id;
    }

    public void setShow_id(int show_id) {
        this.show_id = show_id;
    }

    public String getStill_path() {
        return still_path;
    }

    public void setStill_path(String still_path) {
        this.still_path = still_path;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TvEpisodeItem that)) return false;
        return episode_number == that.episode_number && id == that.id
                && runtime == that.runtime && season_number == that.season_number
                && show_id == that.show_id && Double.compare(vote_average, that.vote_average) == 0
                && vote_count == that.vote_count && rating == that.rating
                && Objects.equals(air_date, that.air_date) && Objects.equals(episode_type, that.episode_type)
                && Objects.equals(name, that.name) && Objects.equals(overview, that.overview)
                && Objects.equals(production_code, that.production_code) && Objects.equals(still_path, that.still_path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(air_date, episode_number, episode_type, id, name, overview,
                production_code, runtime, season_number, show_id, still_path, vote_average, vote_count, rating);
    }
}
