package com.example.lab_vijaybharathreddy_c0872418_android;

public class LocationList {
    public LocationList(String location_name, Double longi, Double lati) {
        this.location_name = location_name;
        this.longi = longi;
        this.lati = lati;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public Double getLongi() {
        return longi;
    }

    public void setLongi(Double longi) {
        this.longi = longi;
    }

    public Double getLati() {
        return lati;
    }

    public void setLati(Double lati) {
        this.lati = lati;
    }

    String location_name;
    Double longi,lati ;




}
