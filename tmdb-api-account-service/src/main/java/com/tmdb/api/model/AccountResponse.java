package com.tmdb.api.model;

import java.util.Objects;

public class AccountResponse {

    private Avatar avatar;
    private int id;
    private String iso_639_1;
    private String iso_3166_1;
    private String name;
    private boolean include_Adult;
    private String username;

    public AccountResponse() {
    }

    public AccountResponse(Avatar avatar, int id, String iso_639_1, String iso_3166_1,
                           String name, boolean include_Adult, String username) {
        this.avatar = avatar;
        this.id = id;
        this.iso_639_1 = iso_639_1;
        this.iso_3166_1 = iso_3166_1;
        this.name = name;
        this.include_Adult = include_Adult;
        this.username = username;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInclude_Adult() {
        return include_Adult;
    }

    public void setInclude_Adult(boolean include_Adult) {
        this.include_Adult = include_Adult;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountResponse that)) return false;
        return id == that.id && include_Adult == that.include_Adult
                && Objects.equals(avatar, that.avatar) && Objects.equals(iso_639_1, that.iso_639_1)
                && Objects.equals(iso_3166_1, that.iso_3166_1) && Objects.equals(name, that.name)
                && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(avatar, id, iso_639_1, iso_3166_1, name, include_Adult, username);
    }
}
