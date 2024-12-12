package com.tmdb.api.config;

import com.tmdb.api.config.filter.TMDBRequestFilter;
import com.tmdb.api.model.AccountResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "tmdb.api")
@RegisterProvider(TMDBRequestFilter.class)
public interface TMDBClient {

    @GET
    @Path("/account")
    AccountResponse getAccountDetails();

}
