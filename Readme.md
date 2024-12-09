API Service Documentation for TMDb Account Management
Overview
This document describes the API endpoints available for managing account-related data using The Movie Database (TMDb) API. All endpoints require a valid Bearer token for authentication.

Base URL
https://api.themoviedb.org/3/

Details
url enpoint:  https://api.themoviedb.org/3/account/{account_id}/watchlist/tv
Method type: Get
Path params: account_id, type of data int32, is required
QueryParams:
- language: type of data -> String, is not required, Defaults to en-US
- page: type of data -> int32, is not required, Defaults to 1
- session_id: type of data -> String, is not required
Example Call
AsyncHttpClient client = new DefaultAsyncHttpClient();
client.prepare("GET", "https://api.themoviedb.org/3/account/10332765/watchlist/tv?language=en-US&page=1&sort_by=created_at.asc")
  .setHeader("accept", "application/json")
  .setHeader("Authorization", "Bearer <YOUR-TOKEN-HERE>")
  .execute()
  .toCompletableFuture()
  .thenAccept(System.out::println)
  .join();

client.close();

Response Structure:

public class Avatar{
    public Gravatar gravatar;
    public Tmdb tmdb;
}

public class Gravatar{
    public String hash;
}

public class Root{
    public Avatar avatar;
    public int id;
    public String iso_639_1;
    public String iso_3166_1;
    public String name;
    public boolean include_adult;
    public String username;
}

public class Tmdb{
    public Object avatar_path;
}


Add Favorite
url enpoint: https://api.themoviedb.org/3/account/{account_id}/favorite
method type: Post
Path Params:
- account_id, type of data int32, is required
Query Params
- session_id: type of data -> String, is not required
Body Params:
- raw_body: type of data -> json, is required
Example Call Api
AsyncHttpClient client = new DefaultAsyncHttpClient();
client.prepare("POST", "https://api.themoviedb.org/3/account/10332765/favorite")
  .setHeader("accept", "application/json")
  .setHeader("content-type", "application/json")
  .setHeader("Authorization", "Bearer <YOUR-TOKEN-HERE>")
  .setBody("{\"media_type\":\"movie\",\"media_id\":650,\"favorite\":true}")
  .execute()
  .toCompletableFuture()
  .thenAccept(System.out::println)
  .join();

client.close();

Response Structure:
Ok Structure Response:

public class Root{
    public boolean success;
    public int status_code;
    public String status_message;
}


Add To Watchlist
url enpoint: https://api.themoviedb.org/3/account/{account_id}/watchlist
Method type:Post
Path Params:
- account_id, type of data int31, is required
Query Params
- session_id: type of data -> String, is not required
Body Params:
- raw_body: type of data -> json, is required
Example Call Api
AsyncHttpClient client = new DefaultAsyncHttpClient();
client.prepare("POST", "https://api.themoviedb.org/3/account/10332765/watchlist")
  .setHeader("accept", "application/json")
  .setHeader("content-type", "application/json")
  .setHeader("Authorization", "Bearer <YOUR-TOKEN-HERE>")
  .setBody("{\"media_type\":\"movie\",\"media_id\":11,\"watchlist\":true}")
  .execute()
  .toCompletableFuture()
  .thenAccept(System.out::println)
  .join();

client.close();
Structure Response
Ok and error 
public class Root{
    public boolean success;
    public int status_code;
    public String status_message;
}

Favorite Movies
url enpoint: https://api.themoviedb.org/3/account/{account_id}/favorite/movies
Method type: GET
Path Params:
- account_id, type of data int31, is required
Query Params
- language: type of data -> String, is not required, Defaults to en-US
- page: type of data -> int32, is not required, Defaults to 1
- session_id: type of data -> String, is not required
- sort_by: type of data -> String, Defaults to created_at.asc, another value is  created_at.desc
Body Params
Not have
Example Call Api
AsyncHttpClient client = new DefaultAsyncHttpClient();
client.prepare("GET", "https://api.themoviedb.org/3/account/10332765/favorite/movies?language=en-US&page=1&sort_by=created_at.asc")
  .setHeader("accept", "application/json")
  .setHeader("Authorization", "Bearer <YOUR-TOKEN-HERE>")
  .execute()
  .toCompletableFuture()
  .thenAccept(System.out::println)
  .join();

client.close();
Response Structure
public class Result{
    public boolean adult;
    public String backdrop_path;
    public ArrayList<int> genre_ids;
    public int id;
    public String original_language;
    public String original_title;
    public String overview;
    public double popularity;
    public String poster_path;
    public String release_date;
    public String title;
    public boolean video;
    public double vote_average;
    public int vote_count;
}

public class Root{
    public int page;
    public ArrayList<Result> results;
    public int total_pages;
    public int total_results;
}

Favorite TV
url enpoint: https://api.themoviedb.org/3/account/{account_id}/favorite/tv
Method type: GET
Path Params:
- account_id, type of data int31, is required
Query Params
- language: type of data -> String, is not required, Defaults to en-US
- page: type of data -> int32, is not required, Defaults to 1
- session_id: type of data -> String, is not required
- sort_by: type of data -> String, Defaults to created_at.asc, another value is  created_at.desc
Body Params
Not required
Example Call Api
AsyncHttpClient client = new DefaultAsyncHttpClient();
client.prepare("GET", "https://api.themoviedb.org/3/account/10332765/favorite/tv?language=en-US&page=1&sort_by=created_at.asc")
  .setHeader("accept", "application/json")
  .setHeader("Authorization", "Bearer <YOUR-TOKEN-HERE>")
  .execute()
  .toCompletableFuture()
  .thenAccept(System.out::println)
  .join();

client.close();
Response Structure
public class Result{
    public boolean adult;
    public String backdrop_path;
    public ArrayList<int> genre_ids;
    public int id;
    public ArrayList<String> origin_country;
    public String original_language;
    public String original_name;
    public String overview;
    public double popularity;
    public String poster_path;
    public String first_air_date;
    public String name;
    public double vote_average;
    public int vote_count;
}

public class Root{
    public int page;
    public ArrayList<Result> results;
    public int total_pages;
    public int total_results;
}

Lists
url enpoint: https://api.themoviedb.org/3/account/{account_id}/lists
Method type: GET
Path Params:
- account_id, type of data int31, is required
Query Params
- page: type of data -> int32, is not required, Defaults to 1
- session_id: type of data -> String, is not required
Body Params
Not Required
Example Call Api
AsyncHttpClient client = new DefaultAsyncHttpClient();
client.prepare("GET", "https://api.themoviedb.org/3/account/10332765/lists?page=1")
  .setHeader("accept", "application/json")
  .setHeader("Authorization", "Bearer <YOUR-TOKEN-HERE>")
  .execute()
  .toCompletableFuture()
  .thenAccept(System.out::println)
  .join();

client.close();
Response Structure
public class Result{
    public String description;
    public int favorite_count;
    public int id;
    public int item_count;
    public String iso_639_1;
    public String list_type;
    public String name;
    public Object poster_path;
}

public class Root{
    public int page;
    public ArrayList<Result> results;
    public int total_pages;
    public int total_results;
}

Rated Movies
url enpoint: https://api.themoviedb.org/3/account/{account_id}/rated/movies
Method type: GET
Path Params:
- account_id, type of data int31, is required
Query Params
- language: type of data -> String, is not required, Defaults to en-US
- page: type of data -> int32, is not required, Defaults to 1
- session_id: type of data -> String, is not required
- sort_by: type of data -> String, Defaults to created_at.asc, another value is  created_at.desc
Body Params
Not Required
Example Call Api
AsyncHttpClient client = new DefaultAsyncHttpClient();
client.prepare("GET", "https://api.themoviedb.org/3/account/10332765/rated/movies?language=en-US&page=1&sort_by=created_at.asc")
  .setHeader("accept", "application/json")
  .setHeader("Authorization", "Bearer <YOUR-TOKEN-HERE>")
  .execute()
  .toCompletableFuture()
  .thenAccept(System.out::println)
  .join();

client.close();
Response Structure
public class Result{
    public boolean adult;
    public String backdrop_path;
    public ArrayList<int> genre_ids;
    public int id;
    public String original_language;
    public String original_title;
    public String overview;
    public double popularity;
    public String poster_path;
    public String release_date;
    public String title;
    public boolean video;
    public double vote_average;
    public int vote_count;
    public int rating;
}

public class Root{
    public int page;
    public ArrayList<Result> results;
    public int total_pages;
    public int total_results;
}

Rated TV
url enpoint: https://api.themoviedb.org/3/account/{account_id}/rated/tv
Method type: GET
Path Params:
- account_id, type of data int31, is required
Query Params
- language: type of data -> String, is not required, Defaults to en-US
- page: type of data -> int32, is not required, Defaults to 1
- session_id: type of data -> String, is not required
- sort_by: type of data -> String, Defaults to created_at.asc, another value is  created_at.desc
Body Params
Not Required
Example Call Api
AsyncHttpClient client = new DefaultAsyncHttpClient();
client.prepare("GET", "https://api.themoviedb.org/3/account/10332765/rated/tv?language=en-US&page=1&sort_by=created_at.asc")
  .setHeader("accept", "application/json")
  .setHeader("Authorization", "Bearer <YOUR-TOKEN-HERE>")
  .execute()
  .toCompletableFuture()
  .thenAccept(System.out::println)
  .join();

client.close();
Response Structure
public class Result{
    public boolean adult;
    public String backdrop_path;
    public ArrayList<int> genre_ids;
    public int id;
    public ArrayList<String> origin_country;
    public String original_language;
    public String original_name;
    public String overview;
    public double popularity;
    public String poster_path;
    public String first_air_date;
    public String name;
    public double vote_average;
    public int vote_count;
    public int rating;
}

public class Root{
    public int page;
    public ArrayList<Result> results;
    public int total_pages;
    public int total_results;
}

Rated TV Episodes
url enpoint: https://api.themoviedb.org/3/account/{account_id}/rated/tv/episodes
Method type: GET
Path Params:
- account_id, type of data int31, is required
Query Params
- language: type of data -> String, is not required, Defaults to en-US
- page: type of data -> int32, is not required, Defaults to 1
- session_id: type of data -> String, is not required
- sort_by: type of data -> String, Defaults to created_at.asc, another value is  created_at.desc
Body Params
Not Required
Example Call Api
AsyncHttpClient client = new DefaultAsyncHttpClient();
client.prepare("GET", "https://api.themoviedb.org/3/account/10332765/rated/tv/episodes?language=en-US&page=1&sort_by=created_at.asc")
  .setHeader("accept", "application/json")
  .setHeader("Authorization", "Bearer <YOUR-TOKEN-HERE>")
  .execute()
  .toCompletableFuture()
  .thenAccept(System.out::println)
  .join();

client.close();
Response Structure
public class Result{
    public String air_date;
    public int episode_number;
    public String episode_type;
    public int id;
    public String name;
    public String overview;
    public String production_code;
    public int runtime;
    public int season_number;
    public int show_id;
    public String still_path;
    public double vote_average;
    public int vote_count;
    public int rating;
}

public class Root{
    public int page;
    public ArrayList<Result> results;
    public int total_pages;
    public int total_results;
}


Watchlist Movies
url enpoint: https://api.themoviedb.org/3/account/{account_id}/watchlist/movies
Method type: GET
Path Params:
- account_id, type of data int31, is required
Query Params
- language: type of data -> String, is not required, Defaults to en-US
- page: type of data -> int32, is not required, Defaults to 1
- session_id: type of data -> String, is not required
- sort_by: type of data -> String, Defaults to created_at.asc, another value is  created_at.desc
Body Params
Not Required
Example Call Api
AsyncHttpClient client = new DefaultAsyncHttpClient();
client.prepare("GET", "https://api.themoviedb.org/3/account/10332765/watchlist/movies?language=en-US&page=1&sort_by=created_at.asc")
  .setHeader("accept", "application/json")
  .setHeader("Authorization", "Bearer <YOUR-TOKEN-HERE>")
  .execute()
  .toCompletableFuture()
  .thenAccept(System.out::println)
  .join();

client.close();
Response Structure
public class Result{
    public boolean adult;
    public String backdrop_path;
    public ArrayList<int> genre_ids;
    public int id;
    public String original_language;
    public String original_title;
    public String overview;
    public double popularity;
    public String poster_path;
    public String release_date;
    public String title;
    public boolean video;
    public double vote_average;
    public int vote_count;
}

public class Root{
    public int page;
    public ArrayList<Result> results;
    public int total_pages;
    public int total_results;
}


Watchlist TV
url enpoint: https://api.themoviedb.org/3/account/{account_id}/watchlist/tv
Method type: GET
Path Params:
- account_id, type of data int31, is required
Query Params
- language: type of data -> String, is not required, Defaults to en-US
- page: type of data -> int32, is not required, Defaults to 1
- session_id: type of data -> String, is not required
- sort_by: type of data -> String, Defaults to created_at.asc, another value is  created_at.desc
Body Params
Not Required
Example Call Api
AsyncHttpClient client = new DefaultAsyncHttpClient();
client.prepare("GET", "https://api.themoviedb.org/3/account/10332765/watchlist/tv?language=en-US&page=1&sort_by=created_at.asc")
  .setHeader("accept", "application/json")
  .setHeader("Authorization", "Bearer <YOUR-TOKEN-HERE>")
  .execute()
  .toCompletableFuture()
  .thenAccept(System.out::println)
  .join();

client.close();
Response Structure
public class Result{
    public boolean adult;
    public String backdrop_path;
    public ArrayList<int> genre_ids;
    public int id;
    public ArrayList<String> origin_country;
    public String original_language;
    public String original_name;
    public String overview;
    public double popularity;
    public String poster_path;
    public String first_air_date;
    public String name;
    public double vote_average;
    public int vote_count;
}

public class Root{
    public int page;
    public ArrayList<Result> results;
    public int total_pages;
    public int total_results;
}
