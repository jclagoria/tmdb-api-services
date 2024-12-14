package com.tmdb.api.external.service;

import com.tmdb.api.external.dto.AddFavoriteRequest;
import com.tmdb.api.external.dto.AddToWatchListRequest;
import com.tmdb.api.external.model.*;
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

    public TmdbApiResponse addToWatchList(int accountId,
                                          String sessionId,
                                          AddToWatchListRequest request) {
        return tmdbRepository.addToWatchList(accountId, sessionId, request);
    }

    public MovieTmdbResponse getFavoriteMovies(long accountId, String sessionId, String language,
                                               long page, String sortBy ) {
        return tmdbRepository.favoriteMovies(accountId, sessionId, language, page, sortBy);
    }

    public TvTmdbResponse getFavoriteTv(long accountId, String sessionId, String language,
                                        long page, String sortBy) {
        return tmdbRepository.favoriteTv(accountId, sessionId, language, page, sortBy);
    }

    public TmdbListResponse getListTmdb(long accountId, long page, String sessionId) {
        return  tmdbRepository.getListTmdb(accountId, page, sessionId);
    }

    public MovieTmdbResponse getRatedMovies(long accountId, String sessionId,
                                            String language, long page, String sortBy) {
        return tmdbRepository.getRatedMovies(accountId, language, page, sessionId, sortBy);
    }

}
