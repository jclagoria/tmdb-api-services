package com.tmdb.api.external.repository;

import com.tmdb.api.external.dto.AddFavoriteRequest;
import com.tmdb.api.external.dto.AddToWatchListRequest;
import com.tmdb.api.external.model.*;
import com.tmdb.api.external.util.ApiRequestBuilderFactory;
import com.tmdb.api.util.Loggable;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Retry;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class TmdbRepository {

    @Inject
    ApiRequestBuilderFactory apiRequestBuilderFactory;

    @Inject
    WebTarget basedTarget;

    @ConfigProperty(name = "tmdb.api.token")
    String API_TOKEN;

    @PostConstruct
    public void validateConfiguration() {
        if (API_TOKEN == null || API_TOKEN.isEmpty()) {
            throw new IllegalStateException("API_TOKEN must be configured");
        }

        if (basedTarget == null) {
            throw new IllegalStateException("Base WebTarget must be initialized");
        }
    }

    private WebTarget prepareTarget(String path, Map<String, Object> queryParams) {
        WebTarget target = basedTarget.path(path);

        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            if ( entry.getKey() != null && entry.getValue() != null ) {
                target = target.queryParam(entry.getKey(), entry.getValue());
            }
        }
        return target;
    }

    private <T> T executeGetRequest(WebTarget target, Class<T> responseType) {
        try (Response response = apiRequestBuilderFactory.newBuilder(target)
                .addHeader("accept", MediaType.APPLICATION_JSON)
                .addHeader("Authorization", "Bearer " + API_TOKEN)
                .build().get()) {
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                return response.readEntity(responseType);
            } else {
                String error = response.readEntity(String.class);
                throw new IllegalCallerException("FAiled to fetch data: Http " + response.getStatus() + " " + error);
            }
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
    public DetailUser getAccountDetails(long accountId, String sessionId) {
        Map<String, Object> queryParams = new HashMap<>();

        if (sessionId != null) {
            queryParams.put("session_id", sessionId);
        }

        WebTarget target = prepareTarget("/account/" + accountId, queryParams);
        return executeGetRequest(target, DetailUser.class);
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
        Map<String, Object> queryParams = new HashMap<>();

        if(sessionId != null){
            queryParams.put("session_id", sessionId);
        }
        queryParams.put("language", language != null  ? language : "en-US");
        queryParams.put("page", page > 0 ? page : 1);
        queryParams.put("sort_by", sortBy != null ? sortBy : "created_at.asc");

        WebTarget target = prepareTarget("/account/" + accountId + "/favorite/movies", queryParams);
        return executeGetRequest(target, MovieTmdbResponse.class);
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

        Map<String, Object> queryParams = new HashMap<>();
        if(sessionId != null){
            queryParams.put("session_id", sessionId);
        }
        queryParams.put("language", language != null  ? language : "en-US");
        queryParams.put("page", page > 0 ? page : 1);
        queryParams.put("sort_by", sortBy != null ? sortBy : "created_at.asc");

        WebTarget target = prepareTarget("/account/" + accountId + "/favorite/tv", queryParams);
        return executeGetRequest(target, TvTmdbResponse.class);
    }

    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    public TmdbListResponse getListTmdb(long accountId, long page, String sessionId) {

        Map<String, Object> queryParams = new HashMap<>();

        if(sessionId != null){
            queryParams.put("session_id", sessionId);
        }
        queryParams.put("page", page > 0 ? page : 1);

        WebTarget target = prepareTarget("/account/" + accountId + "/lists", queryParams);
        return executeGetRequest(target, TmdbListResponse.class);
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
        Map<String, Object> queryParams = new HashMap<>();
        if(sessionId != null){
            queryParams.put("session_id", sessionId);
        }
        queryParams.put("language", language != null  ? language : "en-US");
        queryParams.put("page", page > 0 ? page : 1);
        queryParams.put("sort_by", sortBy != null ? sortBy : "created_at.asc");

        WebTarget target = prepareTarget("/account/" + accountId + "/rated/movies", queryParams);
        return executeGetRequest(target, MovieTmdbResponse.class);
    }

    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    public TvTmdbResponse getRatedTv(long accountId, String language,long page, String sessionId, String sortBy) {
        Map<String, Object> queryParams = new HashMap<>();
        if(sessionId != null){
            queryParams.put("session_id", sessionId);
        }
        queryParams.put("language", language != null  ? language : "en-US");
        queryParams.put("page", page > 0 ? page : 1);
        queryParams.put("sort_by", sortBy != null ? sortBy : "created_at.asc");

        WebTarget target = prepareTarget("/account/" + accountId + "/rated/tv", queryParams);
        return executeGetRequest(target, TvTmdbResponse.class);
    }

    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    public TvEpisodeResponse getRatedTvEpisode(long accountId, String language,
                                               long page, String sessionId, String sortBy) {
        Map<String, Object> queryParams = new HashMap<>();
        if(sessionId != null){
            queryParams.put("session_id", sessionId);
        }
        queryParams.put("language", language != null  ? language : "en-US");
        queryParams.put("page", page > 0 ? page : 1);
        queryParams.put("sort_by", sortBy != null ? sortBy : "created_at.asc");

        WebTarget target = prepareTarget("/account/" + accountId + "/rated/tv/episodes", queryParams);
        return executeGetRequest(target, TvEpisodeResponse.class);
    }

    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    public MovieTmdbResponse getWatchlistMovies(long accountId, String language,
                                                long page, String sessionId, String sortBy) {
        Map<String, Object> queryParams = new HashMap<>();
        if(sessionId != null){
            queryParams.put("session_id", sessionId);
        }
        queryParams.put("language", language != null  ? language : "en-US");
        queryParams.put("page", page > 0 ? page : 1);
        queryParams.put("sort_by", sortBy != null ? sortBy : "created_at.asc");

        WebTarget target = prepareTarget("/account/" + accountId + "/watchlist/movies", queryParams);
        return executeGetRequest(target, MovieTmdbResponse.class);
    }

    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    public TvTmdbResponse getWatchlistTv(long accountId, String language,
                                         long page, String sessionId, String sortBy) {
        Map<String, Object> queryParams = new HashMap<>();
        if(sessionId != null){
            queryParams.put("session_id", sessionId);
        }
        queryParams.put("language", language != null  ? language : "en-US");
        queryParams.put("page", page > 0 ? page : 1);
        queryParams.put("sort_by", sortBy != null ? sortBy : "created_at.asc");

        WebTarget target = prepareTarget("/account/" + accountId + "/watchlist/tv", queryParams);
        return executeGetRequest(target, TvTmdbResponse.class);
    }

    @Loggable
    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    public TmdbApiResponse addFavorite(int accountId, String sessionId, AddFavoriteRequest request) {
        try {
            Map<String, Object> queryParams = new HashMap<>();
            if(sessionId != null){
                queryParams.put("session_id", sessionId);
            }

            WebTarget target = prepareTarget("/account/" + accountId + "/favorite", queryParams);

            try (Response response = apiRequestBuilderFactory.newBuilder(target)
                    .addHeader("accept", MediaType.APPLICATION_JSON)
                    .addHeader("content-type", MediaType.APPLICATION_JSON)
                    .addHeader("Authorization", "Bearer " + API_TOKEN)
                    .build()
                    .post(Entity.entity(request, MediaType.APPLICATION_JSON))) {
                if (response.getStatus() == 200 || response.getStatus() == 201) {
                    return response.readEntity(TmdbApiResponse.class);
                } else {
                    String errorResponse = response.readEntity(String.class);
                    throw new RuntimeException("Failed to add favorite: HTTP " + response.getStatus() + ". Response: " + errorResponse);
                    //throw new ApiException("Failed to add favorite", response.getStatus());
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
            Map<String, Object> queryParams = new HashMap<>();
            if(sessionId != null){
                queryParams.put("session_id", sessionId);
            }

            WebTarget target = prepareTarget("/account/" + accountId + "/watchlist", queryParams);

            try (Response response = apiRequestBuilderFactory.newBuilder(target)
                    .addHeader("accept", MediaType.APPLICATION_JSON)
                    .addHeader("content-type", MediaType.APPLICATION_JSON)
                    .addHeader("Authorization", "Bearer " + API_TOKEN)
                    .build().post(Entity.entity(request, MediaType.APPLICATION_JSON))) {
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

}
