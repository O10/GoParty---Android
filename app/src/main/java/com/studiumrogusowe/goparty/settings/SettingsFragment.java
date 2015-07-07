package com.studiumrogusowe.goparty.settings;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.studiumrogusowe.goparty.R;
import com.studiumrogusowe.goparty.clubs.api.GcmBackendApi;

import java.io.IOException;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by O10 on 31.05.15.
 */
public class SettingsFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String SENDER_ID = "379951331636";


    private SwitchCompat notificationSwitch;

    private String regid;
    private GoogleCloudMessaging gcm;

    public final void initGcm() {
        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(getActivity());
            regid = getRegistrationId(getActivity());

            if (regid.isEmpty()) {
                notificationSwitch.setEnabled(false);
                registerInBackground();
            } else {
            }

        } else {
            Log.i(getClass().getSimpleName(), "No valid Google Play Services APK found.");
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View topView = inflater.inflate(R.layout.fragment_settings, container, false);
        notificationSwitch = (SwitchCompat) topView.findViewById(R.id.notification_switch);
        return topView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notificationSwitch = (SwitchCompat) view.findViewById(R.id.notification_switch);
        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    registerToBackEnd();
                }
            }
        });
    }

    private void registerToBackEnd() {
        SharedPreferences sp = getActivity().getSharedPreferences("com.studiumrogusowe.goparty", Context.MODE_PRIVATE);
        String token = sp.getString("token_clear", "0");
        final RestAdapter build = new RestAdapter.Builder().setEndpoint("http://young-reef-6139.herokuapp.com").setLogLevel(RestAdapter.LogLevel.FULL).build();
        final GcmBackendApi gcmBackendApi = build.create(GcmBackendApi.class);

        gcmBackendApi.register(token,getRegistrationId(getActivity()), new Callback<Void>() {
            @Override
            public void success(Void aVoid, Response response) {
                Toast.makeText(getActivity(), "Zarejestrowano", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(getClass().getSimpleName(), "Error" + error);
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initGcm();
    }

    private SharedPreferences getGCMPreferences() {
        // This sample app persists the registration ID in shared preferences, but
        // how you store the registration ID in your app is up to you.
        return getActivity().getSharedPreferences(SettingsFragment.class.getSimpleName(),
                Context.MODE_PRIVATE);
    }

    private int getAppVersion() {
        try {
            PackageInfo packageInfo = getActivity().getPackageManager()
                    .getPackageInfo(getActivity().getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGCMPreferences();
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i(getClass().getSimpleName(), "Registration not found.");
            return "";
        }
        // Check if app was updated; if so, it must clear the registration ID
        // since the existing registration ID is not guaranteed to work with
        // the new app version.
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion();
        if (registeredVersion != currentVersion) {
            Log.i(getClass().getSimpleName(), "App version changed.");
            return "";
        }
        return registrationId;
    }

    public final boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, getActivity(),
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(getClass().getSimpleName(), "This device is not supported.");
            }
            return false;
        }
        return true;
    }

    private void registerInBackground() {
        new RegisterAsyncTask().execute(null, null, null);
    }

    private void storeRegistrationId(String regId) {
        final SharedPreferences prefs = getGCMPreferences();
        int appVersion = getAppVersion();
        Log.i(getClass().getSimpleName(), "Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.apply();
    }


    private class RegisterAsyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void[] objects) {
            String msg = "";
            try {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(getActivity());
                }
                regid = gcm.register(SENDER_ID);
                msg = "Device registered, registration ID=" + regid;

                // You should send the registration ID to your server over HTTP,
                // so it can use GCM/HTTP or CCS to send messages to your app.
                // The request to your server should be authenticated if your app
                // is using accounts.

                // For this demo: we don't need to send it because the device
                // will send upstream messages to a server that echo back the
                // message using the 'from' address in the message.

                // Persist the registration ID - no need to register again.
                storeRegistrationId(regid);
            } catch (IOException ex) {
                msg = "Error :" + ex.getMessage();
                // If there is an error, don't just keep trying to register.
                // Require the user to click a button again, or perform
                // exponential back-off.
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) {
            notificationSwitch.setEnabled(true);
            Log.d(getClass().getSimpleName(), "App registered " + msg);
        }
    }
}
