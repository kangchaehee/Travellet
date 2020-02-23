package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.Serializable;

public class PlaceSelectItem implements Serializable {

    String title, address;

    public PlaceSelectItem(String title, String address) {
        this.title = title;
        this.address = address;
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

    @Override
    public String toString() {
        return "PlaceSelectItem{" +
                "title='" + title + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
