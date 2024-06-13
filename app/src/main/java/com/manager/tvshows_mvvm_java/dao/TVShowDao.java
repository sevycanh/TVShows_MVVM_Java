package com.manager.tvshows_mvvm_java.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.manager.tvshows_mvvm_java.models.TVShow;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import kotlinx.coroutines.flow.Flow;

/** @noinspection ALL*/
@Dao
public interface TVShowDao {

    @Query("SELECT * FROM tvShows")
    Flowable<List<TVShow>> getWatchlist(); //Flo giúp nhận sự thay đổi của dữ liệu

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addToWatchList(TVShow tvShow); // Compl chỉ trả về complete hoặc error

    @Delete
    Completable removeFromWatchlist(TVShow tvShow);

    @Query("SELECT * FROM tvShows WHERE id = :tvShowId")
    Flowable<TVShow> getTVShowFromWatchlist(String tvShowId);
}
