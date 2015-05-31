package com.studiumrogusowe.goparty.clubs.api.model;

/**
 * Created by Piotrek on 2015-05-30.
 */
public class RatingBodyObject {

    private long timestamp;
    private String clubId;
    private int rating;

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
