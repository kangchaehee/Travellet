package com.example.example.feature.plan.place;

import java.io.Serializable;

public class PlaceSearchItem implements Serializable {

    String placeListName, placeListAddr, placeListType, placeListThumb;

    public PlaceSearchItem(String placeListThumb, String placeListName, String placeListAddr, String placeListType) {
        this.placeListThumb = placeListThumb;
        this.placeListName = placeListName;
        this.placeListAddr = placeListAddr;
        this.placeListType = placeListType;
    }


    public String getPlaceListThumb() {
        return placeListThumb;
    }

    public void setPlaceListThumb(String placeListThumb) {
        this.placeListThumb = placeListThumb;
    }

    public String getPlaceListName() {
        return placeListName;
    }

    public void setPlaceListName(String placeListName) {
        this.placeListName = placeListName;
    }

    public String getPlaceListAddr() {
        return placeListAddr;
    }

    public void setPlaceListAddr(String placeListAddr) {
        this.placeListAddr = placeListAddr;
    }

    public String getPlaceListType() {
        return placeListType;
    }

    public void setPlaceListType(String placeListType) {
        this.placeListType = placeListType;
    }


    @Override
    public String toString() {
        return "PlaceSearchItem{" +
                "placeListThumb=" + placeListThumb +
                ", placeListName='" + placeListName + '\'' +
                ", placeListAddr='" + placeListAddr + '\'' +
                ", placeListType='" + placeListType + '\'' +
                '}';
    }
}
