package com.studiumrogusowe.goparty.test;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.studiumrogusowe.goparty.R;
import com.studiumrogusowe.goparty.clubs.adapters.ClubListAdapter;
import com.studiumrogusowe.goparty.clubs.api.RecommendedClubsRestAdapter;
import com.studiumrogusowe.goparty.clubs.api.model.GetClubsBodyObject;
import com.studiumrogusowe.goparty.clubs.model.Club;
import com.studiumrogusowe.goparty.clubs.model.ClubLocation;
import com.studiumrogusowe.goparty.clubs.model.LocationCoordinates;
import com.studiumrogusowe.goparty.profile.api.ProfileRestAdapter;
import com.studiumrogusowe.goparty.profile.api.model.ProfileResponseObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class EventsFragment extends Fragment implements LocationListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    ListView eventsList;
    ArrayList<Club> clubs = null;
    View view;
    String token;
    Button refreshList;
    double latitude = 50.05;
    double longitude = 19.93;

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_events, container, false);
        eventsList = (ListView) view.findViewById(R.id.eventsListView);
        SharedPreferences sp = this.getActivity().getSharedPreferences("com.studiumrogusowe.goparty", Context.MODE_PRIVATE);
        token = sp.getString("token","Bearer 0");

        LocationManager locationManager = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);


        getRecommendedClubs();


        eventsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(),com.studiumrogusowe.goparty.clubs.ClubDetails.class);
                intent.putExtra("clubId",((Club)eventsList.getItemAtPosition(position)).getId());
                intent.putExtra("clubName",((Club)eventsList.getItemAtPosition(position)).getName());
                startActivity(intent);
            }
        });

        refreshList = (Button) view.findViewById(R.id.btnRefreshClubList);
        refreshList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRecommendedClubs();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void getRecommendedClubs(){

        RecommendedClubsRestAdapter.getInstance().getRecommendedClubsApi().getClubs(token, longitude,latitude,10, new Callback<List<Club>>() {
            @Override
            public void success(List<Club> clubsCallback, Response response) {

                clubs = (ArrayList<Club>)clubsCallback;
                ClubListAdapter adapter = new ClubListAdapter(
                        view.getContext(),
                        R.layout.clubs_list_item,
                        clubs);

                eventsList.setAdapter(adapter);

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("error", error.toString());
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub

        latitude = location.getLatitude();
        longitude = location.getLongitude();

        Log.i("Geo_Location", "Latitude: " + latitude + ", Longitude: " + longitude);
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

}
