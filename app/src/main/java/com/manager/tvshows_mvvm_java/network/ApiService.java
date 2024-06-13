package com.manager.tvshows_mvvm_java.network;

import com.manager.tvshows_mvvm_java.responses.TVShowDetailsResponse;
import com.manager.tvshows_mvvm_java.responses.TVShowsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("most-popular")
    Call<TVShowsResponse> getMostPopularTVShow(@Query("page") int page);

    @GET("show-details")
    Call<TVShowDetailsResponse> getTVShowDetail(@Query("q") String tvShowID);

    @GET("search")
    Call<TVShowsResponse> searchTVShow(@Query("q") String query, @Query("page") int page);
}
