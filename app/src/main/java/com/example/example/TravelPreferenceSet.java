package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TravelPreferenceSet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_preference_set);
    }

    //Lodging set
    public void onButtonClick(View view1){

        Intent intent = new Intent(this, TravelLodgingSet.class);
        startActivity(intent);
    }

    // calendar
    public void onClick(View view){

        Intent intent = new Intent(TravelPreferenceSet.this, TravelCalendar.class);
        startActivity(intent);
    }
}
