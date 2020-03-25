package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class WalletListSubItem extends AppCompatActivity {

    String category, category_memo;
    int money, lodging, payment;

    public WalletListSubItem(String category, String category_memo, int money, int lodging, int payment) {
        this.category = category;
        this.category_memo = category_memo;
        this.money = money;
        this.lodging = lodging;
        this.payment = payment;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory_memo() {
        return category_memo;
    }

    public void setCategory_memo(String category_memo) {
        this.category_memo = category_memo;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }


    public void setLodging(int lodging) {
        this.lodging = lodging;
    }

    public int getLodging() {
        return lodging;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getPayment() {
        return payment;
    }


}
