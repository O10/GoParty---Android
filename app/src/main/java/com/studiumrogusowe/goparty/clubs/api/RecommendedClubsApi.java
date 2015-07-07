package com.studiumrogusowe.goparty.clubs.api;

import com.studiumrogusowe.goparty.authorization.api.model.AuthResponseObject;
import com.studiumrogusowe.goparty.clubs.api.model.CheckBodyObject;
import com.studiumrogusowe.goparty.clubs.api.model.GetClubsBodyObject;
import com.studiumrogusowe.goparty.clubs.api.model.QRBodyObject;
import com.studiumrogusowe.goparty.clubs.api.model.RatingBodyObject;
import com.studiumrogusowe.goparty.clubs.model.Club;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;


/**
 * Created by Piotrek on 2015-06-28.
 */
public interface RecommendedClubsApi {

        @GET("/profile/recommended/clubs")
        void getClubs(@Header("Authorization") String token,@Query("x") double x,@Query("y") double y,@Query("count") int count, Callback<List<Club>> clubsCallback);
}
