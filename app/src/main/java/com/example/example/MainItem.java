package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

public class MainItem extends AppCompatActivity {
    int image;
    String dDay, title, startDay, endDay, period;

    public MainItem(String dDay, String title, String startDay, String endDay) {
        this.image = image;
        this.dDay = dDay;
        this.title = title;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getdDay() {
        return dDay;
    }

    public void setdDay(String dDay) {
        this.dDay = dDay;
    }

    public String getTripTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    @Override
    public String toString() {
        return "MainItem{" +
                ", dDay='" + dDay + '\'' +
                ", title='" + title + '\'' +
                ", startDay='" + startDay + '\'' +
                ", endDay='" + endDay + '\'' +
                ", duration='" + period + '\'' +
                '}';
    }
}
