package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TravelEstimatedBudget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_estimated_budget);
    }

    // plan initial
    public void onClick(View view){

        Intent intent = new Intent(this, PlanInitialActivity.class);
        startActivity(intent);
    }
}
