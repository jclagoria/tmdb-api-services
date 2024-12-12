package com.tmdb.api.external.resources;

import com.tmdb.api.external.model.AccountResponse;
import com.tmdb.api.external.service.AccountService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/account")
public class AccountResource {

    @Inject
    AccountService accountService;


    @GET
    @Path("/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public AccountResponse getAccountService(@PathParam("accountId") int accountId) {
        return accountService.getAccount(accountId);
    }

}
