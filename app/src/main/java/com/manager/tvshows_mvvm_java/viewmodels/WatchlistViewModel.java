package com.manager.tvshows_mvvm_java.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.manager.tvshows_mvvm_java.database.TVShowsDatabase;
import com.manager.tvshows_mvvm_java.models.TVShow;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class WatchlistViewModel extends AndroidViewModel {
    private TVShowsDatabase tvShowsDatabase;

    public WatchlistViewModel(@NonNull Application application) {
        super(application);
        tvShowsDatabase = TVShowsDatabase.getTvShowsDatabase(application);
    }

    public Flowable<List<TVShow>> loadWatchlist(){
        return tvShowsDatabase.tvShowDao().getWatchlist();
    }

    public Completable removeTVShowFromWatchlist(TVShow tvShow){
        return tvShowsDatabase.tvShowDao().removeFromWatchlist(tvShow);
    }
}
