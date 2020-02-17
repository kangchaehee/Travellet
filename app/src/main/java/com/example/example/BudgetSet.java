package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BudgetSet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_set);
    }
    public void onClick(View view){

        Intent intent = new Intent(BudgetSet.this, TravelEstimatedBudget.class);
        startActivity(intent);
    }
}
