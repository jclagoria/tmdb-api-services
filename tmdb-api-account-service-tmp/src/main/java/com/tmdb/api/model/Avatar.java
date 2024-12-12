package com.tmdb.api.model;

import java.util.Objects;

public class Avatar {
    private Gravatar gravatar;
    private Tmdb tmdb;

    public Avatar() {
    }

    public Avatar(Gravatar gravatar, Tmdb tmdb) {
        this.gravatar = gravatar;
        this.tmdb = tmdb;
    }

    public Gravatar getGravatar() {
        return gravatar;
    }

    public void setGravatar(Gravatar gravatar) {
        this.gravatar = gravatar;
    }

    public Tmdb getTmdb() {
        return tmdb;
    }

    public void setTmdb(Tmdb tmdb) {
        this.tmdb = tmdb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avatar avatar)) return false;
        return Objects.equals(gravatar, avatar.gravatar)
                && Objects.equals(tmdb, avatar.tmdb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gravatar, tmdb);
    }
}
