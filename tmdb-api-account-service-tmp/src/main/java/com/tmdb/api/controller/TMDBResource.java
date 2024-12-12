package com.tmdb.api.controller;

import com.tmdb.api.model.AccountResponse;
import com.tmdb.api.service.TMDBService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/tmdb/account")
public class TMDBResource {

    @Inject
    TMDBService tmdbService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public AccountResponse getAccount() {
        return tmdbService.getAccountDetails();
    }
}
