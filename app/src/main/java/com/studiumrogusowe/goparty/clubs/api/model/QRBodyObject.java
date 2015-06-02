package com.studiumrogusowe.goparty.clubs.api.model;

/**
 * Created by Piotrek on 2015-05-31.
 */
public class QRBodyObject {

    private String clubId;
    private long timestamp;
    private String payload;

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
