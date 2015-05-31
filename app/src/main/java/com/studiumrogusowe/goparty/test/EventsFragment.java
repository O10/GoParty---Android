package com.studiumrogusowe.goparty.test;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.studiumrogusowe.goparty.R;
import com.studiumrogusowe.goparty.clubs.adapters.ClubListAdapter;
import com.studiumrogusowe.goparty.clubs.model.Club;
import com.studiumrogusowe.goparty.clubs.model.ClubLocation;
import com.studiumrogusowe.goparty.clubs.model.LocationCoordinates;

import java.util.ArrayList;

public class EventsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    ListView eventsList;

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
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        eventsList = (ListView) view.findViewById(R.id.eventsListView);

        ArrayList<String> tmpGenres;
        Club prozak = new Club();
        prozak.setName("Prozak 2.0");
        prozak.setId("1");
        prozak.setLocation(new ClubLocation("Kraków", "Polska", "Plac Dominikański 6"));
        prozak.setCoords(new LocationCoordinates(19.938205599999947, 50.0589914));
        tmpGenres = new ArrayList<String>();
        tmpGenres.add("trap");
        tmpGenres.add("electro");
        tmpGenres.add("dubstep");
        prozak.setMusic_genres(tmpGenres);

        Club rdza = new Club();
        rdza.setId("2");
        rdza.setName("Rdza");
        rdza.setLocation(new ClubLocation("Kraków","Polska","Bracka 3"));
        rdza.setCoords(new LocationCoordinates(19.93605500000001,50.060567));
        tmpGenres = new ArrayList<String>();
        tmpGenres.add("dubstep");
        rdza.setMusic_genres(tmpGenres);

        Club hush = new Club();
        hush.setId("3");
        hush.setName("Hush Live");
        hush.setLocation(new ClubLocation("Kraków","Polska","św. Tomasza 11A"));
        hush.setCoords(new LocationCoordinates(19.9383196,50.0632167));
        tmpGenres = new ArrayList<String>();
        tmpGenres.add("disco polo");
        tmpGenres.add("disco");
        hush.setMusic_genres(tmpGenres);


        ArrayList<Club> clubs = new ArrayList<Club>();
        clubs.add(prozak);
        clubs.add(rdza);
        clubs.add(hush);


        ClubListAdapter adapter = new ClubListAdapter(
                view.getContext(),
                R.layout.clubs_list_item,
                clubs);

        eventsList.setAdapter(adapter);

        eventsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(),com.studiumrogusowe.goparty.clubs.ClubDetails.class);
                intent.putExtra("clubId",((Club)eventsList.getItemAtPosition(position)).getId());
                intent.putExtra("clubName",((Club)eventsList.getItemAtPosition(position)).getName());
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
