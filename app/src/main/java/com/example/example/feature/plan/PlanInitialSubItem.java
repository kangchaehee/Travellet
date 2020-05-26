package com.example.example.feature.plan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PlanInitialSubItem extends AppCompatActivity {

    String placeTime, placeName, placeMemo, transBudgetText;
    int transport, placeType;
    double x, y, transBudget = 0;

    public PlanInitialSubItem(String placeTime, String placeName, String placeMemo, int transport, int placeType, double x, double y, double totalBudget) {
        this.placeTime = placeTime;
        this.placeName = placeName;
        this.placeMemo = placeMemo;
        this.transport = transport;
        this.placeType = placeType;
        this.x = x;
        this.y = y;
        transBudget = totalBudget;

    }


    public String getPlaceTime() {
        return placeTime;
    }

    public void setPlaceTime(String placeTime) {
        this.placeTime = placeTime;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceMemo() {
        return placeMemo;
    }

    public void setPlaceMemo(String placeMemo) {
        this.placeMemo = placeMemo;
    }

    public void setTransport(int transport){ this.transport = transport; }

    public int getTransport() {
        return transport;
    }

    public int getPlaceType() {
        return placeType;
    }

    public void setPlaceType(int placeType) {
        this.placeType = placeType;
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

    public double getTransBudget() {
        return transBudget;
    }

    public void setTransBudget(double transBudget) {
        this.transBudget = transBudget;
    }

    @Override
    public String toString() {
        return "PlanInitialSubItem{" +
                "placeTime='" + placeTime + '\'' +
                ", placeName='" + placeName + '\'' +
                ", placeMemo='" + placeMemo + '\'' +
                ", transBudgetText='" + transBudgetText + '\'' +
                ", transport=" + transport +
                ", placeType=" + placeType +
                '}';
    }
}

