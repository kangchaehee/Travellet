package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TravelTitleSet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_title_set);
    }

    //Main Empty
    public void onButtonClick(View view1){
        finish();
    }

    //travelcalendar
    public void onClick(View view){

        Intent intent = new Intent(TravelTitleSet.this, TravelCalendar.class);
        startActivity(intent);
        overridePendingTransition(0, 0);

    }
}
