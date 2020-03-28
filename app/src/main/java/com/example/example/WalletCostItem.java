package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

public class WalletCostItem extends AppCompatActivity {
    int category, payment;
    double budget;

    public WalletCostItem(int category, int payment, double budget) {
        this.category = category;
        this.budget = budget;
        this.payment = payment;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }
}
