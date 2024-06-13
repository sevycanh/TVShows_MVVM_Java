package com.manager.tvshows_mvvm_java.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.manager.tvshows_mvvm_java.network.ApiClient;
import com.manager.tvshows_mvvm_java.network.ApiService;
import com.manager.tvshows_mvvm_java.responses.TVShowsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTVShowRepository {
    private ApiService apiService;

    public SearchTVShowRepository(){
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<TVShowsResponse> searchTVShow(String query, int page){
        MutableLiveData<TVShowsResponse> data = new MutableLiveData<>();
        apiService.searchTVShow(query, page).enqueue(new Callback<TVShowsResponse>() {
            @Override
            public void onResponse(Call<TVShowsResponse> call, Response<TVShowsResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TVShowsResponse> call, Throwable throwable) {
                data.setValue(null);
            }
        });
        return data;
    }
}
