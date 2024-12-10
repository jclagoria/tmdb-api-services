package com.tmdb.api.service;

import com.tmdb.api.model.AccountResponse;
import com.tmdb.api.repository.TMDBRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TMDBService {

    @Inject
    TMDBRepository tmdbService;

    public AccountResponse getAccountDetails() {
        return tmdbService.getClient().getAccountDetails();
    }
}
