package com.studiumrogusowe.goparty.clubs.model;

/**
 * Created by Piotrek on 2015-05-29.
 */
public class ClubLocation {

    private String city;
    private String country;
    private String street;

    public ClubLocation(String city, String country, String street) {
        this.city = city;
        this.country = country;
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
