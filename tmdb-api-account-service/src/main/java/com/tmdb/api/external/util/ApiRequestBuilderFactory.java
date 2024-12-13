package com.tmdb.api.external.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.WebTarget;

@ApplicationScoped
public class ApiRequestBuilderFactory {
    public ApiRequestBuilder newBuilder(WebTarget target) {
        return new ApiRequestBuilder(target);
    }
}
