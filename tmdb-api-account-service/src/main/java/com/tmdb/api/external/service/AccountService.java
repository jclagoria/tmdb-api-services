package com.tmdb.api.external.service;

import com.tmdb.api.external.dto.AddFavoriteRequest;
import com.tmdb.api.external.model.DetailUser;
import com.tmdb.api.external.model.TmdbApiResponse;
import com.tmdb.api.external.repository.TmdbRepository;
import com.tmdb.api.util.Loggable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AccountService {

    @Inject
    TmdbRepository tmdbRepository;

    @Loggable
    public DetailUser getAccount(int accountId) {
        try {
            return tmdbRepository.getAccountDetails(accountId);
        } catch (RuntimeException ex) {
            System.out.println("Error fetching account details");
            return null;
        }
    }

    public TmdbApiResponse addFavoriteToAccount(int accountId,
                                                String sessionId,
                                                AddFavoriteRequest request) {
        return tmdbRepository.addFavorite(accountId, sessionId, request);
    }

}
