package com.tmdb.api.external.util;

import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.client.Invocation.Builder;


public class ApiRequestBuilder {

    private final WebTarget target;
    private final Builder requestBuilder;

    public ApiRequestBuilder(WebTarget target) {
        this.target = target;
        this.requestBuilder = target.request();
    }

    public ApiRequestBuilder addHeader(String name, String value) {
        requestBuilder.header(name, value);
        return this;
    }

    public Builder build(){
        return requestBuilder;
    }
}
