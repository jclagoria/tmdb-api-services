package com.tmdb.api.external.repository;

import com.tmdb.api.external.dto.AddFavoriteRequest;
import com.tmdb.api.external.dto.AddToWatchListRequest;
import com.tmdb.api.external.model.*;

public interface TmdbReposiroty {

    public DetailUser getAccountDetails(long accountId, String sessionId);

    public MovieTmdbResponse favoriteMovies(long accountId, String sessionId,
                                            String language, long page, String sortBy);

    public TvTmdbResponse favoriteTv(long accountId, String sessionId,
                                     String language, long page, String sortBy);

    public TmdbListResponse getListTmdb(long accountId, long page, String sessionId);

    public MovieTmdbResponse getRatedMovies(long accountId, String language,
                                            long page, String sessionId, String sortBy);

    public TvTmdbResponse getRatedTv(long accountId, String language,long page, String sessionId, String sortBy);

    public TvEpisodeResponse getRatedTvEpisode(long accountId, String language,
                                               long page, String sessionId, String sortBy);

    public MovieTmdbResponse getWatchlistMovies(long accountId, String language,
                                                long page, String sessionId, String sortBy);

    public TvTmdbResponse getWatchlistTv(long accountId, String language,
                                         long page, String sessionId, String sortBy);

    public TmdbApiResponse addFavorite(int accountId, String sessionId, AddFavoriteRequest request);

    public TmdbApiResponse addToWatchList(int accountId, String sessionId, AddToWatchListRequest request);


}
