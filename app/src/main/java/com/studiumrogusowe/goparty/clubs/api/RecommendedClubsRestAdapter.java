package com.studiumrogusowe.goparty.clubs.api;

/**
 * Created by Piotrek on 2015-06-28.
 */

import com.squareup.okhttp.OkHttpClient;


import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class RecommendedClubsRestAdapter {
    private static volatile RecommendedClubsRestAdapter recommendedClubsRestAdapter;

    private RestAdapter restAdapter;
    private RecommendedClubsApi recommendedClubsApi;

    private RecommendedClubsRestAdapter() {
    }

    public static RecommendedClubsRestAdapter getInstance() {
        if (recommendedClubsRestAdapter == null) {
            synchronized (RecommendedClubsRestAdapter.class) {
                OkHttpClient okHttpClient = new OkHttpClient();
                okHttpClient.setReadTimeout(60 * 1000, TimeUnit.MILLISECONDS);

                recommendedClubsRestAdapter = new RecommendedClubsRestAdapter();
                recommendedClubsRestAdapter.restAdapter = new RestAdapter.Builder().setEndpoint("http://goparty-gateway.herokuapp.com")
                        .setClient(new OkClient(okHttpClient))
                        .setLogLevel(RestAdapter.LogLevel.FULL).build();
                recommendedClubsRestAdapter.recommendedClubsApi = recommendedClubsRestAdapter.restAdapter.create(RecommendedClubsApi.class);
            }
        }
        return recommendedClubsRestAdapter;
    }


    public RecommendedClubsApi getRecommendedClubsApi() {
        return recommendedClubsApi;
    }
}

