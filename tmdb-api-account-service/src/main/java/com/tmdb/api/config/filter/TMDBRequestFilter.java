package com.tmdb.api.config.filter;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

@Provider
public class TMDBRequestFilter implements ClientRequestFilter {

    @ConfigProperty(name = "tmdb.api.token")
    String apiToken;

    @Override
    public void filter(ClientRequestContext clientRequestContext) throws IOException {
        clientRequestContext.getHeaders().add("Authorization", "Bearer " + apiToken);
        clientRequestContext.getHeaders().add("Accept", "application/json");
    }
}
