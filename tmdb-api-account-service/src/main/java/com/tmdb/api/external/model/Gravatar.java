package com.tmdb.api.external.model;

import java.util.Objects;

public class Gravatar {
    private String hash;

    public Gravatar() {
    }

    public Gravatar(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gravatar gravatar)) return false;
        return Objects.equals(hash, gravatar.hash);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(hash);
    }
}
