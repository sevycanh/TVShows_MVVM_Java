package com.manager.tvshows_mvvm_java.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.manager.tvshows_mvvm_java.network.ApiClient;
import com.manager.tvshows_mvvm_java.network.ApiService;
import com.manager.tvshows_mvvm_java.responses.TVShowsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostPopularTVShowsRepository {
    private ApiService apiService;

    public MostPopularTVShowsRepository(){
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<TVShowsResponse> getMostPopularTVShows(int page){
        MutableLiveData<TVShowsResponse> data = new MutableLiveData<>();
        apiService.getMostPopularTVShow(page).enqueue(new Callback<TVShowsResponse>() {
            @Override
            public void onResponse(@NonNull Call<TVShowsResponse> call, @NonNull Response<TVShowsResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<TVShowsResponse> call, @NonNull Throwable throwable) {
                data.setValue(null);
            }
        });
        return data;
    }
}
