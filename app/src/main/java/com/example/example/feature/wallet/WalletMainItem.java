package com.example.example.feature.wallet;

import androidx.core.app.ActivityCompat;

public class WalletMainItem extends ActivityCompat {
    String w_time, w_title, w_memo;
    double w_cost, w_budget;

    public WalletMainItem(String w_time, String w_title, String w_memo, double w_cost, double w_budget) {
        this.w_time = w_time;
        this.w_title = w_title;
        this.w_memo = w_memo;
        this.w_cost = w_cost;
        this.w_budget = w_budget;
    }

    public String getW_time() {
        return w_time;
    }

    public void setW_time(String w_time) {
        this.w_time = w_time;
    }

    public String getW_title() {
        return w_title;
    }

    public void setW_title(String w_title) {
        this.w_title = w_title;
    }

    public String getW_memo() {
        return w_memo;
    }

    public void setW_memo(String w_memo) {
        this.w_memo = w_memo;
    }

    public double getW_cost() {
        return w_cost;
    }

    public void setW_cost(double w_cost) {
        this.w_cost = w_cost;
    }

    public double getW_budget() {
        return w_budget;
    }

    public void setW_budget(double w_budget) {
        this.w_budget = w_budget;
    }

    @Override
    public String toString() {
        return "WalletMainItem{" +
                "w_time='" + w_time + '\'' +
                ", w_title='" + w_title + '\'' +
                ", w_memo='" + w_memo + '\'' +
                ", w_cost=" + w_cost +
                ", w_budget=" + w_budget +
                '}';
    }
}
