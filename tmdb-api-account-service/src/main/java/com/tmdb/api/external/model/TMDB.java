package com.tmdb.api.external.model;

import java.util.Objects;

public class TMDB {
    private Object avatar_path;

    public TMDB() {
    }

    public TMDB(Object avatar_path) {
        this.avatar_path = avatar_path;
    }

    public Object getAvatar_path() {
        return avatar_path;
    }

    public void setAvatar_path(Object avatar_path) {
        this.avatar_path = avatar_path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TMDB tmdb)) return false;
        return Objects.equals(avatar_path, tmdb.avatar_path);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(avatar_path);
    }
}
