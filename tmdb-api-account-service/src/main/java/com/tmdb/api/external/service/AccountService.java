package com.tmdb.api.external.service;

import com.tmdb.api.exception.AccountServiceException;
import com.tmdb.api.external.dto.AddFavoriteRequest;
import com.tmdb.api.external.dto.AddToWatchListRequest;
import com.tmdb.api.external.model.*;
import com.tmdb.api.external.repository.TmdbRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.function.Supplier;

@ApplicationScoped
public class AccountService {

    private static final Logger LOGGER = Logger.getLogger(AccountService.class);

    @Inject
    TmdbRepositoryImpl tmdbRepository;

    public DetailUser getAccount(long accountId, String sessionId) {
        validateAccountId(accountId);

        return executeWithErrorHandling(
                () -> tmdbRepository.getAccountDetails(accountId, sessionId),
                "Error fetching account details for accountId: " + accountId);
    }

    public TmdbApiResponse addFavoriteToAccount(int accountId,
                                                String sessionId,
                                                AddFavoriteRequest request) {
        validateAccountId(accountId);

        return executeWithErrorHandling(
                () -> tmdbRepository.addFavorite(accountId, sessionId, request),
                "Error fetching favorites for accountId: " + accountId);
    }

    public TmdbApiResponse addToWatchList(int accountId,
                                          String sessionId,
                                          AddToWatchListRequest request) {
        validateAccountId(accountId);

        return executeWithErrorHandling(
                () -> tmdbRepository.addToWatchList(accountId, sessionId, request),
                "Error fetching watch list for accountId: " + accountId);
    }

    public MovieTmdbResponse fetchMovies(String resourceType, long accountId, String sessionId,
                                         String language, long page, String sortBy) {
        validateAccountId(accountId);

        return executeWithErrorHandling(
                () -> {
                    switch (resourceType.toLowerCase()) {
                        case "favorites":
                            return tmdbRepository.favoriteMovies(accountId, sessionId, language, page, sortBy);
                        case "rated":
                            return tmdbRepository.getRatedMovies(accountId, language, page, sessionId, sortBy);
                        case "watchlist":
                            return tmdbRepository.getWatchlistMovies(accountId, language, page, sessionId, sortBy);
                        default:
                            throw new UnsupportedOperationException("Unsupported resource type: " + resourceType);
                    }
                },
                "Error fetching movies for resourceType: " + resourceType
        );
    }

    public TvTmdbResponse fetchTvShows(String resourceType, long accountId, String sessionId,
                                       String language, long page, String sortBy) {
        validateAccountId(accountId);

        return executeWithErrorHandling(
                () -> {
                    switch (resourceType.toLowerCase()) {
                        case "favorites":
                            return tmdbRepository.favoriteTv(accountId, sessionId, language, page, sortBy);
                        case "rated":
                            return tmdbRepository.getRatedTv(accountId, language, page, sessionId, sortBy);
                        case "watchlist":
                            return tmdbRepository.getWatchlistTv(accountId, language, page, sessionId, sortBy);
                        default:
                            throw new UnsupportedOperationException("Unsupported resource type: " + resourceType);
                    }
                },
                "Error fetching TV shows for resourceType: " + resourceType
        );
    }

    public TvEpisodeResponse fetchRatedTvEpisodes(long accountId, String sessionId,
                                                  String language, long page, String sortBy) {
        validateAccountId(accountId);

        return executeWithErrorHandling(
                () -> tmdbRepository.getRatedTvEpisode(accountId, language, page, sessionId, sortBy),
                "Error fetching rated TV episodes for account: " + accountId
        );
    }

    public TmdbListResponse getListTmdb(long accountId, long page, String sessionId) {
        validateAccountId(accountId);

        return executeWithErrorHandling(
                () -> tmdbRepository.getListTmdb(accountId, page, sessionId),
                "Error fetching TMDB lists for account: " + accountId
        );
    }


    // Validation Helper Methods
    private void validateAccountId(long accountId) {
        if (accountId <= 0) {
            throw new IllegalArgumentException("Account ID must be greater than 0");
        }
    }

    // Error Handling Utility Method
    private <T> T executeWithErrorHandling(Supplier<T> supplier, String errorMessage) {
        try {
            return supplier.get();
        } catch (RuntimeException ex) {
            LOGGER.error(errorMessage, ex);
            throw new AccountServiceException(errorMessage, ex);
        }
    }

}
