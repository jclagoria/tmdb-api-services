package com.tmdb.api.external.model;

import java.util.Objects;

public class Avatar {

    private Gravatar gravatar;
    private TMDB tmdb;

    public Avatar() {
    }

    public Avatar(Gravatar gravatar, TMDB tmdb) {
        this.gravatar = gravatar;
        this.tmdb = tmdb;
    }

    public Gravatar getGravatar() {
        return gravatar;
    }

    public void setGravatar(Gravatar gravatar) {
        this.gravatar = gravatar;
    }

    public TMDB getTmdb() {
        return tmdb;
    }

    public void setTmdb(TMDB tmdb) {
        this.tmdb = tmdb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avatar avatar)) return false;
        return Objects.equals(gravatar, avatar.gravatar) && Objects.equals(tmdb, avatar.tmdb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gravatar, tmdb);
    }
}
