package com.manager.tvshows_mvvm_java.responses;

import com.google.gson.annotations.SerializedName;
import com.manager.tvshows_mvvm_java.models.TVShowDetails;

public class TVShowDetailsResponse {
    @SerializedName("tvShow")
    private TVShowDetails tvShowDetails;
    public TVShowDetails getTvShowDetails(){
        return tvShowDetails;
    }
}
