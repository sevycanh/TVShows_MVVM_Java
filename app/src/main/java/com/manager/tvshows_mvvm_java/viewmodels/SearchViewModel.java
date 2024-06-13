package com.manager.tvshows_mvvm_java.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.manager.tvshows_mvvm_java.repositories.SearchTVShowRepository;
import com.manager.tvshows_mvvm_java.responses.TVShowsResponse;

public class SearchViewModel extends ViewModel {
    private SearchTVShowRepository searchTVShowRepository;

    public SearchViewModel(){
        searchTVShowRepository = new SearchTVShowRepository();
    }

    public LiveData<TVShowsResponse> searchTVShows(String query, int page){
        return searchTVShowRepository.searchTVShow(query, page);
    }
}
