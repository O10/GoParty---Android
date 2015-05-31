package com.studiumrogusowe.goparty.clubs.api;

import com.studiumrogusowe.goparty.clubs.api.model.CheckBodyObject;
import com.studiumrogusowe.goparty.clubs.api.model.RatingBodyObject;
import com.studiumrogusowe.goparty.profile.api.model.ProfileBodyObject;
import com.studiumrogusowe.goparty.profile.api.model.ProfileResponseObject;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;

/**
 * Created by Piotrek on 2015-05-30.
 */
public interface EventQueueApi {

    @POST("/events/checkin")
    void checkIn(@Header("Authorization") String token,@Body CheckBodyObject checkBodyObject, Callback<Response> cb);

    @POST("/events/checkout")
    void checkOut(@Header("Authorization") String token,@Body CheckBodyObject checkBodyObject, Callback<Response> cb);

    @POST("/events/rating")
    void rate(@Header("Authorization") String token,@Body RatingBodyObject ratingBodyObject, Callback<Response> cb);
}
