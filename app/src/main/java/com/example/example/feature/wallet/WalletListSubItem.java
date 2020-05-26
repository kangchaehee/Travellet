package com.example.example.feature.wallet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class WalletListSubItem extends AppCompatActivity {

    String category, place;
    int category_ic, payment;
    double money;

    public WalletListSubItem(String category, String place, double money, int category_ic, int payment) {
        this.category = category;
        this.place = place;
        this.money = money;
        this.category_ic = category_ic;
        this.payment = payment;
    }


    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getPlace() {return place; }

    public void setPlace(String place) { this.place = place; }

    public void setMoney(double money) { this.money = money; }

    public double getMoney() { return money; }

    public void setCategory_ic(int category_ic) { this.category_ic = category_ic; }

    public int getCategory_ic() { return category_ic; }

    public void setPayment(int payment) { this.payment = payment; }

    public int getPayment() { return payment; }

    @Override
    public String toString() {
        return "WalletListSubItem{" +
                "category='" + category + '\'' +
                ", place='" + place + '\'' +
                ", money=" + money +
                ", lodging=" + category_ic +
                ", payment=" + payment +
                '}';
    }

}
