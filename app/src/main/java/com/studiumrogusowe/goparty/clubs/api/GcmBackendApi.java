package com.studiumrogusowe.goparty.clubs.api;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.http.Part;

/**
 * Created by O10 on 31.05.15.
 */
public interface GcmBackendApi {

    @POST("/notifications/register")
    @FormUrlEncoded
    void register(@Field("registration_id") String registrationId, Callback<Void> callback);
}
