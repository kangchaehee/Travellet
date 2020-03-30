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

    // travel budget set
    public void onButtonClick(View view1){

        Intent intent = new Intent(this, BudgetSet.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    // plan initial
    public void onClick(View view){

        Intent intent = new Intent(this, Navigation.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
