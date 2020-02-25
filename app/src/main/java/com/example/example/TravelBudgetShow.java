package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;

public class TravelBudgetShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_budget_show);
    }

    //calendar
    public void onButtonClick(View view1){

        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }

    public void onClick(View view){

        Intent intent = new Intent(TravelBudgetShow.this, BudgetSet.class);
        startActivity(intent);
    }
}
