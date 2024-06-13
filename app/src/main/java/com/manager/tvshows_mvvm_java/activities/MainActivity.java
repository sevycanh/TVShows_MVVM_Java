package com.manager.tvshows_mvvm_java.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.manager.tvshows_mvvm_java.R;
import com.manager.tvshows_mvvm_java.adapters.TvShowsAdapter;
import com.manager.tvshows_mvvm_java.databinding.ActivityMainBinding;
import com.manager.tvshows_mvvm_java.listeners.TVShowsListener;
import com.manager.tvshows_mvvm_java.models.TVShow;
import com.manager.tvshows_mvvm_java.viewmodels.MostPopularTVShowsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TVShowsListener {

    private ActivityMainBinding activityMainBinding;
    private MostPopularTVShowsViewModel viewModel;
    private List<TVShow> tvShows = new ArrayList<>();
    private TvShowsAdapter tvShowsAdapter;
    private int currentPage = 1;

    private int totalAvailablePages = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        doInitialization();
    }

    private void doInitialization() {
        activityMainBinding.tvshowsRecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
        tvShowsAdapter = new TvShowsAdapter(tvShows, this);
        activityMainBinding.tvshowsRecyclerView.setAdapter(tvShowsAdapter);
        activityMainBinding.tvshowsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!activityMainBinding.tvshowsRecyclerView.canScrollVertically(1)){
                    if (currentPage <= totalAvailablePages){
                        currentPage +=1;
                        getMostPopularTVShows();
                    }
                }
            }
        });

        activityMainBinding.imageWatchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), WatchlistActivity.class));
            }
        });

        activityMainBinding.imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
            }
        });

        getMostPopularTVShows();
    }

    private void getMostPopularTVShows() {
        toggleLoading();
        viewModel.getMostPopularTVShows(currentPage).observe(this, tvShowsResponse -> {
                    toggleLoading();
                    if (tvShowsResponse != null) {
                        totalAvailablePages = tvShowsResponse.getTotalPages();
                        if (tvShowsResponse.getTvShows() != null) {
                            int oldCount = tvShows.size();
                            tvShows.addAll(tvShowsResponse.getTvShows());
                            tvShowsAdapter.notifyItemRangeInserted(oldCount, tvShows.size());
                        }
                    }
                }
        );
    }

    private void toggleLoading(){
        if (currentPage ==1){
            if (activityMainBinding.getIsLoading()!=null && activityMainBinding.getIsLoading()){
                activityMainBinding.setIsLoading(false);
            } else {
                activityMainBinding.setIsLoading(true);
            }
        } else {
            if (activityMainBinding.getIsLoadingMore()!=null && activityMainBinding.getIsLoadingMore()){
                activityMainBinding.setIsLoadingMore(false);
            } else {
                activityMainBinding.setIsLoadingMore(true);
            }
        }
    }

    @Override
    public void onTVShowClicked(TVShow tvShow) {
        Intent intent = new Intent(getApplicationContext(), TVShowDetailsActivity.class);
//        intent.putExtra("id", tvShow.getId());
//        intent.putExtra("name", tvShow.getName());
//        intent.putExtra("startDate", tvShow.getStartDate());
//        intent.putExtra("country", tvShow.getCountry());
//        intent.putExtra("network", tvShow.getNetwork());
//        intent.putExtra("status", tvShow.getStatus());
        intent.putExtra("tvShow", tvShow);
        startActivity(intent);
    }
}