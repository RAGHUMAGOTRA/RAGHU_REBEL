package com.example.admin.vibring.addedlocations;

/**
 * Created by admin on 5/3/2017.
 */

public class SingleRow {

    String locationName,mode;
    double latitude,longitude;
    int uid;

    public SingleRow(int uid,String name, double latitude, double longitude, String mode) {
        this.locationName = name;
        this.latitude = latitude;
        this.longitude=longitude;
        this.mode=mode;
        this.uid=uid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
