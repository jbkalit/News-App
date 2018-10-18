package com.kalit.news.data.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by KALIT on 9/16/2018.
 */

public interface ApiService {
    @GET("sources")
    Call<String> sourcesData(@Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<String> articlesData(@Query("sources") String sources,
                                  @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<String> searchData(@Query("sources") String sources,
                            @Query("q") String keyword,
                            @Query("apiKey") String apiKey);


}
