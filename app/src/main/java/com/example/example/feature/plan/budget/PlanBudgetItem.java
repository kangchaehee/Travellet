package com.example.example.feature.plan.budget;

import androidx.appcompat.app.AppCompatActivity;

public class PlanBudgetItem extends AppCompatActivity {
    int category;
    double budget;

    public PlanBudgetItem(int category, double budget) {
        this.category = category;
        this.budget = budget;
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
}
