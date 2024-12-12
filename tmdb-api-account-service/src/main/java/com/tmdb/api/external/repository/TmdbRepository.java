package com.tmdb.api.external.repository;

import com.tmdb.api.external.util.ApiRequestBuilder;
import com.tmdb.api.external.util.HttpClientFactory;
import com.tmdb.api.util.Loggable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.tmdb.api.external.model.AccountResponse;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Retry;

import java.util.logging.Logger;

@ApplicationScoped
public class TmdbRepository {

    private static final Logger LOGGER = Logger.getLogger(TmdbRepository.class.getName());

    @Inject
    HttpClientFactory httpClientFactory;

    @ConfigProperty(name = "tmdb.api.base-url")
    String BASE_URL;

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
    public AccountResponse getAccountDetails(int accountId) {
        Client client = httpClientFactory.createClient();
        WebTarget target = client.target(BASE_URL + "/account/" + accountId);
        logRequestDetails(target.getUri().toString(), API_TOKEN);

        ApiRequestBuilder builder = new ApiRequestBuilder(target)
                .addHeader("accept", MediaType.APPLICATION_JSON)
                .addHeader("Authorization", "Bearer " + API_TOKEN);
        Response response = builder.build().get();
        logResponseDetails(response);

        if (response.getStatus() == 200) {
            return response.readEntity(AccountResponse.class);
        } else {
            throw new RuntimeException("Failed to fetch account details: HTTP "+response.getStatus());
        }

    }

    


    private void logRequestDetails(String url, String token) {
        LOGGER.info(() -> "Request URL: " + url);
        LOGGER.info(() -> "Authorization Header: Bearer " + maskToken(token));
    }

    private void logResponseDetails(Response response) {
        LOGGER.info(() -> "Response Status: " + response.getStatus());
        LOGGER.info(() -> "Response Body: " + response.readEntity(AccountResponse.class));
    }

    private String maskToken(String token) {
        if (token.length() > 10) {
            return token.substring(0, 10) + "..."; // Mask for security
        }
        return token;
    }

}
