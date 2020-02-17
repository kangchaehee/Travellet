package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TravelCalendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_calendar);
    }
    public void onClick(View view){

        Intent intent = new Intent(TravelCalendar.this, BudgetShow.class);
        startActivity(intent);
    }

}
