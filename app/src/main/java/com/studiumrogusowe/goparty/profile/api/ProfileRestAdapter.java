package com.studiumrogusowe.goparty.profile.api;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class ProfileRestAdapter {
    private static volatile ProfileRestAdapter profileRestAdapter;

    private RestAdapter restAdapter;
    private ProfileApi profileApi;

    private ProfileRestAdapter() {
    }

    public static ProfileRestAdapter getInstance() {
        if (profileRestAdapter == null) {
            synchronized (ProfileRestAdapter.class) {
                OkHttpClient okHttpClient = new OkHttpClient();
                okHttpClient.setReadTimeout(60 * 1000, TimeUnit.MILLISECONDS);

                profileRestAdapter = new ProfileRestAdapter();
                profileRestAdapter.restAdapter = new RestAdapter.Builder().setEndpoint(ProfileUtilities.API_PATH)
                        .setClient(new OkClient(okHttpClient))
                        .setLogLevel(RestAdapter.LogLevel.FULL).build();
                profileRestAdapter.profileApi = profileRestAdapter.restAdapter.create(ProfileApi.class);
            }
        }
        return profileRestAdapter;
    }


    public ProfileApi getProfileApi() {
        return profileApi;
    }
}
