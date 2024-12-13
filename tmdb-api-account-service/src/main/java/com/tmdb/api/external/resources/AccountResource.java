package com.tmdb.api.external.resources;

import com.tmdb.api.external.dto.AddFavoriteRequest;
import com.tmdb.api.external.dto.AddToWatchListRequest;
import com.tmdb.api.external.model.*;
import com.tmdb.api.external.service.AccountService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    @Inject
    AccountService accountService;


    @GET
    @Path("/{accountId}")
    public DetailUser getAccountService(@PathParam("accountId") int accountId) {
        return accountService.getAccount(accountId);
    }

    @POST
    @Path("/{accountId}/favorite")
    public TmdbApiResponse addFavorite(@PathParam("accountId") int accountId,
                                       @QueryParam("sessionId") String sessionId,
                                       AddFavoriteRequest addFavoriteRequest) {
        return accountService.addFavoriteToAccount(accountId, sessionId, addFavoriteRequest);
    }

    @POST
    @Path("/{accountId}/watchlist")
    public TmdbApiResponse addToWatchList(@PathParam("accountId") int accountId,
                                          @QueryParam("sessionId") String sessionId,
                                          AddToWatchListRequest request) {
        return accountService.addToWatchList(accountId, sessionId, request);
    }

    @GET
    @Path("/{accountId}/favorite/movies")
    public FavoriteMovieResponse favoriteMovies(@PathParam("accountId") long accountId,
                                                @QueryParam("sessionId") String sessionId,
                                                @QueryParam("language") String language,
                                                @QueryParam("page") long page,
                                                @QueryParam("sortBy") String sortBy) {
        return accountService.getFavoriteMovies(accountId, sessionId, language, page, sortBy);
    }

    @GET
    @Path("/{accountId}/favorite/tv")
    public FavoriteTvResponse favoriteTv(@PathParam("accountId") long accountId,
                                         @QueryParam("sessionId") String sessionId,
                                         @QueryParam("language") String language,
                                         @QueryParam("page") long page,
                                         @QueryParam("sortBy") String sortBy) {
        return accountService.getFavoriteTv(accountId, sessionId, language, page, sortBy);
    }

}
