
package com.studiumrogusowe.goparty.clubs.api;
import com.squareup.okhttp.OkHttpClient;
import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class EventQueueRestAdapter {
    private static volatile EventQueueRestAdapter eventQueueRestAdapter;

    private RestAdapter restAdapter;
    private EventQueueApi eventQueueApi;

    private EventQueueRestAdapter() {
    }

    public static EventQueueRestAdapter getInstance() {
        if (eventQueueRestAdapter == null) {
            synchronized (EventQueueRestAdapter.class) {
                OkHttpClient okHttpClient = new OkHttpClient();
                okHttpClient.setReadTimeout(60 * 1000, TimeUnit.MILLISECONDS);

                eventQueueRestAdapter = new EventQueueRestAdapter();
                eventQueueRestAdapter.restAdapter = new RestAdapter.Builder().setEndpoint(EventQueueUtils.API_PATH)
                        .setClient(new OkClient(okHttpClient))
                        .setLogLevel(RestAdapter.LogLevel.FULL).build();
                eventQueueRestAdapter.eventQueueApi = eventQueueRestAdapter.restAdapter.create(EventQueueApi.class);
            }
        }
        return eventQueueRestAdapter;
    }


    public EventQueueApi getEventQueueApi() {
        return eventQueueApi;
    }
}
