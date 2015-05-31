package com.studiumrogusowe.goparty.clubs.model;

import java.util.List;

/**
 * Created by Piotrek on 2015-05-29.
 */
public class Club {

    private String id;
    private String name;
    private List<String> category_list;
    private String about;
    private ClubLocation location;
    private LocationCoordinates coords;
    private String website;
    private List<String> music_genres;
    private String phone;
    private String picture_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCategory_list() {
        return category_list;
    }

    public void setCategory_list(List<String> category_list) {
        this.category_list = category_list;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public ClubLocation getLocation() {
        return location;
    }

    public void setLocation(ClubLocation location) {
        this.location = location;
    }

    public LocationCoordinates getCoords() {
        return coords;
    }

    public void setCoords(LocationCoordinates coords) {
        this.coords = coords;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<String> getMusic_genres() {
        return music_genres;
    }

    public void setMusic_genres(List<String> music_genres) {
        this.music_genres = music_genres;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }
}
