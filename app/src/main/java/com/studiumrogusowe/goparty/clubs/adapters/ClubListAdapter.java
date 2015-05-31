package com.studiumrogusowe.goparty.clubs.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.studiumrogusowe.goparty.R;
import com.studiumrogusowe.goparty.clubs.model.Club;

import java.util.List;

/**
 * Created by Piotrek on 2015-05-29.
 */
public class ClubListAdapter extends ArrayAdapter<Club> {

    private final List<Club> list;
    private final Context context;
    private final int layout;

    public ClubListAdapter(Context context, int layout, List<Club> list) {
        super(context, layout, list);
        this.context = context;
        this.list = list;
        this.layout = layout;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            view = inflator.inflate(layout, null);


        }

        TextView name = (TextView) view.findViewById(R.id.clubName);
        TextView address = (TextView) view.findViewById(R.id.clubAddress);
        TextView genres = (TextView) view.findViewById(R.id.clubMusicGenres);

        name.setText(list.get(position).getName());
        address.setText(list.get(position).getLocation().getCity() +","+
                list.get(position).getLocation().getStreet());
        genres.setText("Genres: "+list.get(position).getMusic_genres());

        return view;
    }
}
