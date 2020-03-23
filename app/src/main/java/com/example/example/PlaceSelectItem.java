package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.Serializable;

public class PlaceSelectItem implements Serializable {

    String title, address;
    double mapx, mapy;

    public PlaceSelectItem(String title, String address, double mapx, double mapy) {
        this.title = title;
        this.address = address;
        this.mapx = mapx;
        this.mapy = mapy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getMapx() {
        return mapx;
    }

    public void setMapx(double mapx) {
        this.mapx = mapx;
    }

    public double getMapy() {
        return mapy;
    }

    public void setMapy(double mapy) {
        this.mapy = mapy;
    }

    @Override
    public String toString() {
        return "PlaceSelectItem{" +
                "title='" + title + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
