package com.tmdb.api.external.service;

import com.tmdb.api.external.model.AccountResponse;
import com.tmdb.api.external.repository.TmdbRepository;
import com.tmdb.api.util.Loggable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AccountService {

    @Inject
    TmdbRepository tmdbRepository;

    @Loggable
    public AccountResponse getAccount(int accountId) {
        try {
            return tmdbRepository.getAccountDetails(accountId);
        } catch (RuntimeException ex) {
            System.out.println("Error fetching account details");
            return null;
        }
    }

}
