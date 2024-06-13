package com.manager.tvshows_mvvm_java.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.manager.tvshows_mvvm_java.repositories.MostPopularTVShowsRepository;
import com.manager.tvshows_mvvm_java.responses.TVShowsResponse;

public class MostPopularTVShowsViewModel extends ViewModel {
    private MostPopularTVShowsRepository mostPopularTVShowsRepository;

    public MostPopularTVShowsViewModel(){
        mostPopularTVShowsRepository = new MostPopularTVShowsRepository();
    }

    public LiveData<TVShowsResponse> getMostPopularTVShows(int page){
        return mostPopularTVShowsRepository.getMostPopularTVShows(page);
    }
}
