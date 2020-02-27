package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class TravelLodgingSet extends AppCompatActivity {

    Handler handler = new Handler();

    Button btn_hotel, btn_condo, btn_hostel;
    boolean hotelState=false, condoState=false, hostelState=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_lodging_set);

        btn_hotel = (Button) findViewById(R.id.btn_hotel);
        btn_condo = (Button) findViewById(R.id.btn_condo);
        btn_hostel = (Button) findViewById(R.id.btn_hostel);


        btn_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!hotelState){
                    hotelState = true;
                    btn_hotel.setBackgroundResource(R.drawable.border);
                    //btn_hotel.setTextColor(getResources().getColor(R.color.white, getTheme()));
//
                    condoState = false;
                    btn_condo.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_condo.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    hostelState = false;
                    btn_hostel.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_hostel.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

                else {
                    hotelState = false;
                    btn_hotel.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_hotel.setTextColor(getResources().getColor(R.color.blue, getTheme()));
                }
            }
        });

        btn_condo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!condoState){
                    condoState = true;
                    btn_condo.setBackgroundResource(R.drawable.border);
                    //btn_condo.setTextColor(getResources().getColor(R.color.white, getTheme()));

                    hotelState = false;
                    btn_hotel.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_hotel.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    hostelState = false;
                    btn_hostel.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_hostel.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

                else {
                    condoState = false;
                    btn_condo.setBackgroundResource(R.drawable.button_background_border);
                    //btn_condo.setTextColor(getResources().getColor(R.color.blue, getTheme()));
                }
            }
        });

        btn_hostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!hostelState){
                    hostelState = true;
                    btn_hostel.setBackgroundResource(R.drawable.border);
                    //btn_hostel.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    condoState = false;
                    btn_condo.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_condo.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    hotelState = false;
                    btn_hotel.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_hotel.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

                else {
                    hostelState = false;
                    btn_hostel.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_hostel.setTextColor(getResources().getColor(R.color.blue, getTheme()));
                }
            }
        });
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
