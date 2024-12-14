package com.tmdb.api.external.repository;

import com.tmdb.api.external.dto.AddFavoriteRequest;
import com.tmdb.api.external.dto.AddToWatchListRequest;
import com.tmdb.api.external.model.*;
import com.tmdb.api.external.util.ApiRequestBuilder;
import com.tmdb.api.external.util.ApiRequestBuilderFactory;
import com.tmdb.api.util.Loggable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;

@ApplicationScoped
public class TmdbRepository {

    @Inject
    ApiRequestBuilderFactory apiRequestBuilderFactory;

    @Inject
    WebTarget basedTarget;

    @ConfigProperty(name = "tmdb.api.token")
    String API_TOKEN;

    @Loggable
    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    @Fallback(fallbackMethod = "getAccountDetailsFallback")
    public DetailUser getAccountDetails(int accountId) {

        WebTarget target = basedTarget.path("/account/" + accountId);

        ApiRequestBuilder builder = apiRequestBuilderFactory.newBuilder(target)
                .addHeader("accept", MediaType.APPLICATION_JSON)
                .addHeader("Authorization", "Bearer " + API_TOKEN);
        Response response = builder.build().get();

        if (response.getStatus() == 200) {
            return response.readEntity(DetailUser.class);
        } else {
            throw new RuntimeException("Failed to fetch account details: HTTP "+response.getStatus());
        }

    }

    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    public MovieTmdbResponse favoriteMovies(long accountId, String sessionId,
                                            String language, long page, String sortBy) {
        WebTarget target = basedTarget.path("/account/" + accountId + "/favorite/movies");

        if (sessionId != null && !sessionId.isEmpty()) {
            target = target.queryParam("sessionId", sessionId);
        }

        target = target.queryParam("language", (language != null && !language.isEmpty()) ? language : "en-US" );
        target = target.queryParam("page", page > 0 ? page : 1 );
        target = target.queryParam("sortBy", (sortBy != null && !sortBy.isEmpty()) ? sortBy : "created_at.asc" );

        ApiRequestBuilder builder = apiRequestBuilderFactory.newBuilder(target)
                .addHeader("accept", MediaType.APPLICATION_JSON)
                .addHeader("Authorization", "Bearer " + API_TOKEN);

        Response response = builder.build().get();

        if (response.getStatus() == 200) {
            return response.readEntity(MovieTmdbResponse.class);
        } else {
            throw new RuntimeException("Failed to fetch movies: HTTP " + response.getStatus());
        }
    }

    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    public TvTmdbResponse favoriteTv(long accountId, String sessionId,
                                     String language, long page, String sortBy) {
        WebTarget target = basedTarget.path("/account/" + accountId + "/favorite/tv");

        if (sessionId != null && !sessionId.isEmpty()) {
            target = target.queryParam("sessionId", sessionId);
        }

        target = target.queryParam("language", (language != null && !language.isEmpty()) ? language : "en-US" );
        target = target.queryParam("page", page > 0 ? page : 1 );
        target = target.queryParam("sortBy", (sortBy != null && !sortBy.isEmpty()) ? sortBy : "created_at.asc" );

        ApiRequestBuilder builder = apiRequestBuilderFactory.newBuilder(target)
                .addHeader("accept", MediaType.APPLICATION_JSON)
                .addHeader("Authorization", "Bearer " + API_TOKEN);

        Response response = builder.build().get();

        if (response.getStatus() == 200) {
            return response.readEntity(TvTmdbResponse.class);
        } else {
            throw new RuntimeException("Failed to fetch tv: HTTP " + response.getStatus());
        }

    }

    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    public TmdbListResponse getListTmdb(long accountId, long page, String sessionId) {
        WebTarget target = basedTarget.path("/account/" + accountId + "/lists");

        if (sessionId != null && !sessionId.isEmpty()) {
            target = target.queryParam("sessionId", sessionId);
        }

        target = target.queryParam("page", page > 0  ? page : 1 );

        ApiRequestBuilder builder = apiRequestBuilderFactory.newBuilder(target)
                .addHeader("accept", MediaType.APPLICATION_JSON)
                .addHeader("Authorization", "Bearer " + API_TOKEN);

        Response response = builder.build().get();

        if (response.getStatus() == 200) {
            return response.readEntity(TmdbListResponse.class);
        } else {
            throw new RuntimeException("Failed to fetch tmdb: HTTP " + response.getStatus());
        }
    }

    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    public MovieTmdbResponse getRatedMovies(long accountId, String language,
                                            long page, String sessionId, String sortBy) {
        WebTarget target = basedTarget.path("/account/" + accountId + "/rated/movies");

        if (sessionId != null && !sessionId.isEmpty()) {
            target = target.queryParam("sessionId", sessionId);
        }

        target = target.queryParam("language", (language != null && !language.isEmpty()) ? language : "en-US" );
        target = target.queryParam("page", page > 0 ? page : 1 );
        target = target.queryParam("sortBy", (sortBy != null && !sortBy.isEmpty()) ? sortBy : "created_at.asc" );

        ApiRequestBuilder builder = apiRequestBuilderFactory.newBuilder(target)
                .addHeader("accept", MediaType.APPLICATION_JSON)
                .addHeader("Authorization", "Bearer " + API_TOKEN);

        Response response = builder.build().get();

        if (response.getStatus() == 200) {
            return response.readEntity(MovieTmdbResponse.class);
        } else {
            throw new RuntimeException("Failed to fetch rated movies: HTTP " + response.getStatus());
        }
    }

    @Loggable
    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    @Fallback(fallbackMethod = "getAddFavoritesFallback")
    public TmdbApiResponse addFavorite(int accountId, String sessionId, AddFavoriteRequest request) {
        try {
            String url = "/account/" + accountId + "/favorite";
            WebTarget target = basedTarget.path(url);

            if (sessionId != null && !sessionId.isEmpty()) {
                target = target.queryParam("session_id", sessionId); // Ensure this matches TMDB's API spec
            }

            ApiRequestBuilder builder = new ApiRequestBuilder(target)
                    .addHeader("accept", MediaType.APPLICATION_JSON)
                    .addHeader("content-type", MediaType.APPLICATION_JSON)
                    .addHeader("Authorization", "Bearer " + API_TOKEN); // API_TOKEN should come from a secure config

            try (Response response = builder.build().post(Entity.entity(request, MediaType.APPLICATION_JSON))) {

                if (response.getStatus() == 201 || response.getStatus() == 200) {
                    return response.readEntity(TmdbApiResponse.class);
                } else {
                    String errorResponse = response.readEntity(String.class); // Read error response for debugging
                    throw new RuntimeException("Failed to add favorite: HTTP " + response.getStatus() + ". Response: " + errorResponse);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error adding favorite: " + e.getMessage(), e);
        }
    }

    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    public TmdbApiResponse addToWatchList(int accountId, String sessionId, AddToWatchListRequest request) {
        try {
            String url = "/account/" + accountId + "/watchlist";
            WebTarget target = basedTarget.path(url);

            if (sessionId != null && !sessionId.isEmpty()) {
                target = target.queryParam("session_id", sessionId);
            }

            ApiRequestBuilder builder = new ApiRequestBuilder(target)
                    .addHeader("accept", MediaType.APPLICATION_JSON)
                    .addHeader("content-type", MediaType.APPLICATION_JSON)
                    .addHeader("Authorization", "Bearer " + API_TOKEN);

            try (Response response = builder.build().post(Entity.entity(request, MediaType.APPLICATION_JSON))) {
                if (response.getStatus() == 201 || response.getStatus() == 200) {
                    return response.readEntity(TmdbApiResponse.class);
                } else {
                    String errorResponse = response.readEntity(String.class); // Read error response for debugging
                    throw new RuntimeException("Failed to add favorite: HTTP " + response.getStatus() + ". Response: " + errorResponse);
                }
            }

        } catch (Exception ex) {
            throw new RuntimeException("Error adding watch list: " + ex.getMessage(), ex);
        }
    }



    public DetailUser getAccountDetailsFallback(int accountId) {
        return new DetailUser(); // Return a safe default object
    }

    public TmdbApiResponse getAddFavoritesFallback(int accountId, String sessionId, AddFavoriteRequest request) {
        return new TmdbApiResponse(); // Return a safe default object
    }

}
