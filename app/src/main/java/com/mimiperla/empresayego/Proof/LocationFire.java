package com.mimiperla.empresayego.Proof;

public class LocationFire {

    private String latitude;
    private String longitude;


    public LocationFire( String latitude,String longitude) {
        this.latitude = latitude;

        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
