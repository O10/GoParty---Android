package com.studiumrogusowe.goparty.clubs.model;

/**
 * Created by Piotrek on 2015-05-29.
 */
public class LocationCoordinates {

    public LocationCoordinates(double xCoordinate, double yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    private double xCoordinate;
    private double yCoordinate;

    public double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
