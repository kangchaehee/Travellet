package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TravelLodgingSet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_lodging_set);
    }

    //travel title set
    public void onButtonClick(View view1){

        Intent intent = new Intent(this, TravelTitleSet.class);
        startActivity(intent);
    }

    //travel preference set
    public void onClick(View view){

        Intent intent = new Intent(TravelLodgingSet.this, TravelPreferenceSet.class);
        startActivity(intent);
    }

}
