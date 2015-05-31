package com.studiumrogusowe.goparty.clubs.api.model;

/**
 * Created by Piotrek on 2015-05-30.
 */
public class CheckBodyObject {
    private long timestamp;
    private String clubId;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }
}
