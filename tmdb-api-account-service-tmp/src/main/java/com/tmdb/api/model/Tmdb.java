package com.tmdb.api.model;

import java.util.Objects;

public class Tmdb {
    private String avatar_path;

    public Tmdb() {
    }

    public Tmdb(String avatar_path) {
        this.avatar_path = avatar_path;
    }

    public String getAvatar_path() {
        return avatar_path;
    }

    public void setAvatar_path(String avatar_path) {
        this.avatar_path = avatar_path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tmdb tmdb)) return false;
        return Objects.equals(avatar_path, tmdb.avatar_path);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(avatar_path);
    }
}
