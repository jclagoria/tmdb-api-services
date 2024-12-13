package com.tmdb.api.external.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;

@ApplicationScoped
public class ApiRequestBuilder {

    private final WebTarget target;
    private final Builder requestBuilder;

    @Inject
    public ApiRequestBuilder(WebTarget target) {
        this.target = target;
        this.requestBuilder = target.request();
    }

    public ApiRequestBuilder addHeader(String name, String value) {
        requestBuilder.header(name, value);
        return this;
    }

    public Invocation.Builder build(){
        return requestBuilder;
    }
}
