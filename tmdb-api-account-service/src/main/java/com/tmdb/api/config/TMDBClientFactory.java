package com.tmdb.api.config;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

import java.net.URI;

public class TMDBClientFactory {

    private static TMDBClient instance;

    public static TMDBClient getInstance(String baseUrl) {
        if (instance == null) {
            synchronized (TMDBClientFactory.class) {
                if (instance == null) {
                    instance = RestClientBuilder.newBuilder()
                            .baseUri(URI.create(baseUrl))
                            .build(TMDBClient.class);
                }
            }
        }
        return instance;
    }
}
