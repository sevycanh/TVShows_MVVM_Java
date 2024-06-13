package com.manager.tvshows_mvvm_java.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.manager.tvshows_mvvm_java.R;
import com.manager.tvshows_mvvm_java.databinding.ItemContainerTvShowBinding;
import com.manager.tvshows_mvvm_java.listeners.TVShowsListener;
import com.manager.tvshows_mvvm_java.listeners.WatchlistListener;
import com.manager.tvshows_mvvm_java.models.TVShow;

import java.util.List;

public class WatchlistAdapter extends RecyclerView.Adapter<WatchlistAdapter.TVShowViewHolder> {
    private List<TVShow> tvShows;
    private LayoutInflater layoutInflater;
    private WatchlistListener watchlistListener;

    public WatchlistAdapter(List<TVShow> tvShows, WatchlistListener watchlistListener) {
        this.tvShows = tvShows;
        this.watchlistListener = watchlistListener;
    }

    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerTvShowBinding tvShowBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_container_tv_show, parent, false
        );
        return new TVShowViewHolder(tvShowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowViewHolder holder, int position) {
        holder.bindTVShow(tvShows.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    class TVShowViewHolder extends RecyclerView.ViewHolder{
        private ItemContainerTvShowBinding itemContainerTvShowBinding;


        public TVShowViewHolder(@NonNull ItemContainerTvShowBinding itemView) {
            super(itemView.getRoot());
            this.itemContainerTvShowBinding = itemView;
        }

        public void bindTVShow(TVShow tvShow){
            itemContainerTvShowBinding.setTvShow(tvShow);
            itemContainerTvShowBinding.executePendingBindings();

            itemContainerTvShowBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    watchlistListener.onTVShowClicked(tvShow);
                }
            });

            itemContainerTvShowBinding.imageDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    watchlistListener.removeTVShowFromWatchlist(tvShow, getAdapterPosition());
                }
            });
            itemContainerTvShowBinding.imageDelete.setVisibility(View.VISIBLE);
        }
    }
}
