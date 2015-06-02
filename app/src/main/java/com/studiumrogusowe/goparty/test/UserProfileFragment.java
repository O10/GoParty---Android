package com.studiumrogusowe.goparty.test;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;
import com.studiumrogusowe.goparty.R;
import com.studiumrogusowe.goparty.authorization.api.AuthRestAdapter;
import com.studiumrogusowe.goparty.authorization.api.model.AuthResponseObject;
import com.studiumrogusowe.goparty.profile.api.ProfileRestAdapter;
import com.studiumrogusowe.goparty.profile.api.model.ProfileResponseObject;

import java.io.InputStream;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Piotrek on 2015-04-20.
 */
public class UserProfileFragment extends Fragment {

    TextView name;
    ListView favGenres, favBands;
    ImageView avatar;
    Button editProfile;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        name = (TextView) view.findViewById(R.id.userProfileName);
        favBands = (ListView) view.findViewById(R.id.userProfileFavBandsList);
        favGenres = (ListView) view.findViewById(R.id.userProfileFavGenresList);
        avatar = (ImageView) view.findViewById(R.id.userProfileAvatar);
        editProfile = (Button) view.findViewById(R.id.userProfileEdit);

        SharedPreferences sp = this.getActivity().getSharedPreferences("com.studiumrogusowe.goparty", Context.MODE_PRIVATE);
        String token = sp.getString("token","Bearer 0");
        Log.d("profiletoken",token);

        ProfileRestAdapter.getInstance().getProfileApi().getProfile(token, new Callback<ProfileResponseObject>() {
            @Override
            public void success(ProfileResponseObject profileResponseObject, Response response) {

                Picasso.with(getActivity()).load(profileResponseObject.getPicture_url()).into(avatar);

                // setting name fields etc
                name.setText(profileResponseObject.getFirst_name()+" "+profileResponseObject.getLast_name());

                ArrayAdapter<String> bandsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, profileResponseObject.getFavourite_bands());
                ArrayAdapter<String> genresAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, profileResponseObject.getFavourite_genres());
                favBands.setAdapter(bandsAdapter);
                favGenres.setAdapter(genresAdapter);


            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("error",error.toString());
            }
        });



        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new EditProfileFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment).addToBackStack( "tag" ).commit();
            }
        });

                // Inflate the layout for this fragment
        return view;
    }
}
