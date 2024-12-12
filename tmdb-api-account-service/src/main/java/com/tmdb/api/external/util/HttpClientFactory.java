package com.tmdb.api.external.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;

@ApplicationScoped
public class HttpClientFactory {

    public Client createClient() {
        return ClientBuilder.newClient();
    }

}
