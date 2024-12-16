package com.tmdb.api.external.repository;

import com.tmdb.api.exception.TmdbApiException;
import com.tmdb.api.external.dto.AddFavoriteRequest;
import com.tmdb.api.external.dto.AddToWatchListRequest;
import com.tmdb.api.external.model.*;
import com.tmdb.api.external.util.ApiRequestBuilderFactory;
import com.tmdb.api.external.util.QueryUtil;
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
import org.jboss.logging.Logger;

import java.util.Map;

@ApplicationScoped
public class TmdbRepositoryImpl implements TmdbReposiroty {

    private static final Logger LOGGER = Logger.getLogger(TmdbRepositoryImpl.class);

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
        LOGGER.info("In executeGetRequest " + responseType.getName());
        try (Response response = apiRequestBuilderFactory.newBuilder(target)
                .addHeader("accept", MediaType.APPLICATION_JSON)
                .addHeader("Authorization", "Bearer " + API_TOKEN)
                .build().get()) {
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                LOGGER.info("The Response os Ok");
                return response.readEntity(responseType);
            } else {
                String error = response.readEntity(String.class);
                LOGGER.info("The Response os error");
                throw new TmdbApiException(response.getStatus(),
                        "Failed to fetch data: Http " + response.getStatus() + " " + error);
            }
        }
    }

    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    @Override
    public DetailUser getAccountDetails(long accountId, String sessionId) {
        LOGGER.info("In getAccountDetails " + accountId);
        WebTarget target = prepareTarget("/account/" + accountId,
                QueryUtil.buildQueryMap(sessionId));
        return executeGetRequest(target, DetailUser.class);
    }

    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    @Override
    public MovieTmdbResponse favoriteMovies(long accountId, String sessionId,
                                            String language, long page, String sortBy) {
        LOGGER.info("In favoriteMovies " + accountId);
        WebTarget target = prepareTarget("/account/" + accountId + "/favorite/movies",
                QueryUtil.buildQueryMap(sessionId, language, page, sortBy));
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
        LOGGER.info("In favoriteTv " + accountId);
        WebTarget target = prepareTarget("/account/" + accountId + "/favorite/tv",
                QueryUtil.buildQueryMap(sessionId, language, page, sortBy));
        return executeGetRequest(target, TvTmdbResponse.class);
    }

    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    @Override
    public TmdbListResponse getListTmdb(long accountId, long page, String sessionId) {
        LOGGER.info("In getListTmdb " + accountId);
        WebTarget target = prepareTarget("/account/" + accountId + "/lists",
                QueryUtil.buildQueryMap(sessionId, page));
        return executeGetRequest(target, TmdbListResponse.class);
    }

    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    @Override
    public MovieTmdbResponse getRatedMovies(long accountId, String language,
                                            long page, String sessionId, String sortBy) {
        LOGGER.info("In getRatedMovies " + accountId);
        WebTarget target = prepareTarget("/account/" + accountId + "/rated/movies",
                QueryUtil.buildQueryMap(sessionId, language, page, sortBy));
        return executeGetRequest(target, MovieTmdbResponse.class);
    }

    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    @Override
    public TvTmdbResponse getRatedTv(long accountId, String language,long page, String sessionId, String sortBy) {
        LOGGER.info("In getRatedTv " + accountId);
        WebTarget target = prepareTarget("/account/" + accountId + "/rated/tv",
                QueryUtil.buildQueryMap(sessionId, language, page, sortBy));
        return executeGetRequest(target, TvTmdbResponse.class);
    }

    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    @Override
    public TvEpisodeResponse getRatedTvEpisode(long accountId, String language,
                                               long page, String sessionId, String sortBy) {
        LOGGER.info("In getRatedTvEpisode " + accountId);
        WebTarget target = prepareTarget("/account/" + accountId + "/rated/tv/episodes",
                QueryUtil.buildQueryMap(sessionId, language, page, sortBy));
        return executeGetRequest(target, TvEpisodeResponse.class);
    }

    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    @Override
    public MovieTmdbResponse getWatchlistMovies(long accountId, String language,
                                                long page, String sessionId, String sortBy) {
        LOGGER.info("In getWatchlistMovies " + accountId);
        WebTarget target = prepareTarget("/account/" + accountId + "/watchlist/movies",
                QueryUtil.buildQueryMap(sessionId, language, page, sortBy));
        return executeGetRequest(target, MovieTmdbResponse.class);
    }

    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    @Override
    public TvTmdbResponse getWatchlistTv(long accountId, String language,
                                         long page, String sessionId, String sortBy) {
        LOGGER.info("In getWatchlistTv " + accountId);
        WebTarget target = prepareTarget("/account/" + accountId + "/watchlist/tv",
                QueryUtil.buildQueryMap(sessionId, language, page, sortBy));
        return executeGetRequest(target, TvTmdbResponse.class);
    }

    @Retry(maxRetries = 3, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 1000,
            successThreshold = 2
    )
    @Override
    public TmdbApiResponse addFavorite(int accountId, String sessionId, AddFavoriteRequest request) {
        LOGGER.info("In addFavorite " + accountId);
        try {
            WebTarget target = prepareTarget("/account/" + accountId + "/favorite", QueryUtil.buildQueryMap(sessionId));

            try (Response response = apiRequestBuilderFactory.newBuilder(target)
                    .addHeader("accept", MediaType.APPLICATION_JSON)
                    .addHeader("content-type", MediaType.APPLICATION_JSON)
                    .addHeader("Authorization", "Bearer " + API_TOKEN)
                    .build()
                    .post(Entity.entity(request, MediaType.APPLICATION_JSON))) {
                if (response.getStatus() == 200 || response.getStatus() == 201) {
                    LOGGER.info("Response is Ok for addFavorite");
                    return response.readEntity(TmdbApiResponse.class);
                } else {
                    LOGGER.info("Response is not ok for addFavorite");
                    String errorResponse = response.readEntity(String.class);
                    throw new TmdbApiException(response.getStatus(),
                            "Failed to add favorite: HTTP " + response.getStatus() + ". Response: " + errorResponse);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Failed to add favorite", e);
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
    @Override
    public TmdbApiResponse addToWatchList(int accountId, String sessionId, AddToWatchListRequest request) {
        LOGGER.info("In addToWatchList " + accountId);
        try {
            WebTarget target = prepareTarget("/account/" + accountId + "/watchlist",
                    QueryUtil.buildQueryMap(sessionId));

            try (Response response = apiRequestBuilderFactory.newBuilder(target)
                    .addHeader("accept", MediaType.APPLICATION_JSON)
                    .addHeader("content-type", MediaType.APPLICATION_JSON)
                    .addHeader("Authorization", "Bearer " + API_TOKEN)
                    .build().post(Entity.entity(request, MediaType.APPLICATION_JSON))) {
                if (response.getStatus() == 201 || response.getStatus() == 200) {
                    LOGGER.info("Response is Ok for addToWatchList");
                    return response.readEntity(TmdbApiResponse.class);
                } else {
                    LOGGER.info("Response is not ok for addToWatchList");
                    String errorResponse = response.readEntity(String.class);
                    throw new TmdbApiException(response.getStatus(),
                            "Failed to add favorite: HTTP " + response.getStatus() + ". Response: " + errorResponse);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Failed to add to watchlist", ex);
            throw new RuntimeException("Error adding watch list: " + ex.getMessage(), ex);
        }
    }

}