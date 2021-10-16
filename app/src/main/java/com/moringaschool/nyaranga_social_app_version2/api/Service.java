package com.moringaschool.nyaranga_social_app_version2.api;

import com.moringaschool.nyaranga_social_app_version2.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("/search/Users?q=language:java+loacation:lagos")
    Call<ItemResponse> getItems();
}
