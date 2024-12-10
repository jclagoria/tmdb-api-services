package com.tmdb.api.repository;

import com.tmdb.api.config.TMDBClient;
import com.tmdb.api.config.TMDBClientFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class TMDBRepository {

    private final TMDBClient tmdbClient;

    @Inject
    public TMDBRepository(
            @ConfigProperty(name = "tmdb.api.base-url") String baseUrl
    ) {
        this.tmdbClient = TMDBClientFactory.getInstance(baseUrl);
    }

    public TMDBClient getClient() {
        return tmdbClient;
    }

}
