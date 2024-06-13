package com.manager.tvshows_mvvm_java.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.manager.tvshows_mvvm_java.network.ApiClient;
import com.manager.tvshows_mvvm_java.network.ApiService;
import com.manager.tvshows_mvvm_java.responses.TVShowDetailsResponse;
import com.manager.tvshows_mvvm_java.responses.TVShowsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowDetailsRepository {
    private ApiService apiService;

    public TVShowDetailsRepository(){
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<TVShowDetailsResponse> getTVShowDetails(String tvShowID){
        MutableLiveData<TVShowDetailsResponse> data = new MutableLiveData<>();
        apiService.getTVShowDetail(tvShowID).enqueue(new Callback<TVShowDetailsResponse>() {
            @Override
            public void onResponse(Call<TVShowDetailsResponse> call, Response<TVShowDetailsResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TVShowDetailsResponse> call, Throwable throwable) {
                data.setValue(null);
            }
        });
        return data;
    }
}
