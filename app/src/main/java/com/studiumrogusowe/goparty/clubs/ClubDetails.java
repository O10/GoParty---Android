package com.studiumrogusowe.goparty.clubs;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.studiumrogusowe.goparty.R;
import com.studiumrogusowe.goparty.clubs.api.EventQueueRestAdapter;
import com.studiumrogusowe.goparty.clubs.api.model.CheckBodyObject;
import com.studiumrogusowe.goparty.clubs.api.model.RatingBodyObject;
import com.studiumrogusowe.goparty.profile.api.ProfileRestAdapter;
import com.studiumrogusowe.goparty.profile.api.model.ProfileResponseObject;
import com.studiumrogusowe.goparty.test.UserProfileFragment;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ClubDetails extends Activity {

    String clubId;
    String clubName;
    TextView tClubName;
    Button checkin, checkout, sendRate;
    RatingBar ratingBar;
    boolean checkinClicked = false;
    LinearLayout hiddenBox;
    String token;
    boolean rated = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_details);
        hiddenBox = (LinearLayout) findViewById(R.id.clubDetailsHiddenBox);
        tClubName = (TextView) findViewById(R.id.clubDetailsClubName);
        checkin = (Button) findViewById(R.id.clubDetailsCheckin);
        checkout = (Button) findViewById(R.id.clubDetailsCheckout);
        sendRate =  (Button) findViewById(R.id.clubDetailsRateSubmit);
        ratingBar = (RatingBar) findViewById(R.id.clubDetailsRatingBar);

        Bundle extras = getIntent().getExtras();

        SharedPreferences sp = getSharedPreferences("com.studiumrogusowe.goparty", Context.MODE_PRIVATE);
        token = sp.getString("token","Bearer 0");

        if (extras != null) {
            clubId = extras.getString("clubId");
            clubName =  extras.getString("clubName");
            tClubName.setText("Club: " + clubName);
        }

        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkin.setVisibility(View.GONE);
                hiddenBox.setVisibility(View.VISIBLE);
                // call checkin api method
                checkIn();
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call checkout api method
                checkin.setVisibility(View.VISIBLE);
                hiddenBox.setVisibility(View.INVISIBLE);
                checkOut();
            }
        });

        sendRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkin.setVisibility(View.GONE);
                hiddenBox.setVisibility(View.VISIBLE);
                if (ratingBar.getRating() != 0){

                    // call rate api method
                    rate();
                } else Toast.makeText(getApplicationContext(),"RATE BY CLICKING STARS AND THEN SUBMIT",Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void checkIn(){

        CheckBodyObject body = new CheckBodyObject();
        body.setClubId(clubId);
        body.setTimestamp(System.currentTimeMillis() / 1000L);

        // executing request
        EventQueueRestAdapter.getInstance().getEventQueueApi().checkIn(token, body, new Callback<Response>() {


            @Override
            public void success(Response response, Response response2) {
                if (response.getStatus() == 200){
                    Toast.makeText(getApplicationContext(),"You've checked in into " + clubName,Toast.LENGTH_SHORT).show();
                }
                if (response.getStatus() == 400){
                    Toast.makeText(getApplicationContext(),"STATUS 400",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("error", error.toString());
                Toast.makeText(getApplicationContext(),"ERROR WHILE CHECKING IN",Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void checkOut(){

        CheckBodyObject body = new CheckBodyObject();
        body.setClubId(clubId);
        body.setTimestamp(System.currentTimeMillis() / 1000L);

        // executing request
        EventQueueRestAdapter.getInstance().getEventQueueApi().checkOut(token, body, new Callback<Response>() {


            @Override
            public void success(Response response, Response response2) {
                if (response.getStatus() == 200) {
                    Toast.makeText(getApplicationContext(), "You've checked out from " + clubName, Toast.LENGTH_SHORT).show();
                }
                if (response.getStatus() == 400) {
                    Toast.makeText(getApplicationContext(), "STATUS 400", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("error", error.toString());
                Toast.makeText(getApplicationContext(), "ERROR WHILE CHECKING OUT", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void rate(){

        RatingBodyObject body = new RatingBodyObject();
        body.setClubId(clubId);
        body.setTimestamp(System.currentTimeMillis() / 1000L);
        body.setRating((int) ratingBar.getRating());
        // executing request
        EventQueueRestAdapter.getInstance().getEventQueueApi().rate(token, body, new Callback<Response>() {


            @Override
            public void success(Response response, Response response2) {
                if (response.getStatus() == 200){
                    Toast.makeText(getApplicationContext(),"You've rated " + clubName,Toast.LENGTH_SHORT).show();
                    rated = true;

                }
                if (response.getStatus() == 400){
                    Toast.makeText(getApplicationContext(),"STATUS 400",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("error", error.toString());
                Toast.makeText(getApplicationContext(),"ERROR WHILE RATING",Toast.LENGTH_SHORT).show();

            }
        });

    }



}
