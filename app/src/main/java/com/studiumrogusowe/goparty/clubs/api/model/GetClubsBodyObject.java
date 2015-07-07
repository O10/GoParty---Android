package com.studiumrogusowe.goparty.clubs.api.model;

/**
 * Created by Piotrek on 2015-06-28.
 */
public class GetClubsBodyObject {

    private int count = 10;
    private double x;
    private double y;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
