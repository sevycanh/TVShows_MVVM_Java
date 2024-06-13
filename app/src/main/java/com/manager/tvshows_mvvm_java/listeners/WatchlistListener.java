package com.manager.tvshows_mvvm_java.listeners;

import com.manager.tvshows_mvvm_java.models.TVShow;

public interface WatchlistListener {
    void onTVShowClicked(TVShow tvShow);
    void removeTVShowFromWatchlist(TVShow tvShow, int position);
}
