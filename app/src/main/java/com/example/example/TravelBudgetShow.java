package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TravelBudgetShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_budget_show);
    }
    public void onClick(View view){

        Intent intent = new Intent(TravelBudgetShow.this, BudgetSet.class);
        startActivity(intent);
    }
}
