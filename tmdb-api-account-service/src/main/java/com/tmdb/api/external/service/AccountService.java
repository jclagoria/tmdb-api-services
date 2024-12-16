package com.tmdb.api.external.service;

import com.tmdb.api.external.dto.AddFavoriteRequest;
import com.tmdb.api.external.dto.AddToWatchListRequest;
import com.tmdb.api.external.model.*;
import com.tmdb.api.external.repository.TmdbRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AccountService {

    @Inject
    TmdbRepositoryImpl tmdbRepository;

    public DetailUser getAccount(long accountId, String sessionId) {
        try {
            return tmdbRepository.getAccountDetails(accountId, sessionId);
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

    public TvTmdbResponse getRatedTv(long accountId, String sessionId, String language, long page, String sortBy) {
        return tmdbRepository.getRatedTv(accountId, language, page, sessionId, sortBy);
    }

    public TvEpisodeResponse getRatedEpisodeTv(long accountId, String sessionId,
                                               String language, long page, String sortBy) {
        return tmdbRepository.getRatedTvEpisode(accountId, language, page, sessionId, sortBy);
    }

    public MovieTmdbResponse getWatchlistMovies(long accountId, String sessionId,
                                                String language, long page, String sortBy) {
        return tmdbRepository.getWatchlistMovies(accountId, language, page, sessionId, sortBy);
    }

    public TvTmdbResponse getWatchlistTv(long accountId, String sessionId,
                                         String language, long page, String sortBy) {
        return tmdbRepository.getWatchlistTv(accountId, language, page, sessionId, sortBy);
    }

}
