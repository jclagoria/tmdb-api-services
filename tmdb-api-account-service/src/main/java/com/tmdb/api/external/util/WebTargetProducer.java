package com.tmdb.api.external.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class WebTargetProducer {

    @ConfigProperty(name = "tmdb.api.base-url")
    String baseURL;

    @Produces
    @ApplicationScoped
    public Client createClient() {
        return ClientBuilder.newClient();
    }

    @Produces
    @ApplicationScoped
    public WebTarget createWebTarget(Client client) {
        return client.target(baseURL);
    }
}
