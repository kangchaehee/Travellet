package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class TravelPreferenceSet extends AppCompatActivity {

    Handler handler = new Handler();

    Button btn_shopping, btn_food, btn_rest, btn_leisure, btn_history, btn_nature;
    boolean shoppingState=false, foodState=false, restState=false, leisureState=false, historyState=false, natureState=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_preference_set);


        btn_shopping = (Button) findViewById(R.id.btn_shopping);
        btn_food = (Button) findViewById(R.id.btn_food);
        btn_rest = (Button) findViewById(R.id.btn_rest);
        btn_leisure = (Button) findViewById(R.id.btn_leisure);
        btn_history = (Button) findViewById(R.id.btn_history);
        btn_nature = (Button) findViewById(R.id.btn_nature);


        //쇼핑
        btn_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!shoppingState){
                    shoppingState = true;
                    btn_shopping.setBackgroundResource(R.drawable.border);
                    //btn_shopping.setTextColor(getResources().getColor(R.color.white, getTheme()));

                    foodState = false;
                    btn_food.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_food.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    restState = false;
                    btn_rest.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_rest.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    leisureState = false;
                    btn_leisure.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_leisure.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    historyState = false;
                    btn_history.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_history.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    natureState = false;
                    btn_nature.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_nature.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

                else {
                    shoppingState = false;
                    btn_shopping.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_shopping.setTextColor(getResources().getColor(R.color.blue, getTheme()));
                }
            }
        });

        //음식
        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!foodState){
                    foodState = true;
                    btn_food.setBackgroundResource(R.drawable.border);
                    //btn_shopping.setTextColor(getResources().getColor(R.color.white, getTheme()));

                    shoppingState = false;
                    btn_shopping.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_food.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    restState = false;
                    btn_rest.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_rest.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    leisureState = false;
                    btn_leisure.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_leisure.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    historyState = false;
                    btn_history.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_history.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    natureState = false;
                    btn_nature.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_nature.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

                else {
                    foodState = false;
                    btn_food.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_shopping.setTextColor(getResources().getColor(R.color.blue, getTheme()));
                }
            }
        });

        //휴식
        btn_rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!restState){
                    restState = true;
                    btn_rest.setBackgroundResource(R.drawable.border);
                    //btn_rest.setTextColor(getResources().getColor(R.color.white, getTheme()));

                    shoppingState = false;
                    btn_shopping.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_shopping.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    foodState = false;
                    btn_food.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_food.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    leisureState = false;
                    btn_leisure.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_leisure.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    historyState = false;
                    btn_history.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_history.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    natureState = false;
                    btn_nature.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_nature.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

                else {
                    restState = false;
                    btn_rest.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_shopping.setTextColor(getResources().getColor(R.color.blue, getTheme()));
                }
            }
        });


        //레져
        btn_leisure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!leisureState){
                    leisureState = true;
                    btn_leisure.setBackgroundResource(R.drawable.border);
                    //btn_leisure.setTextColor(getResources().getColor(R.color.white, getTheme()));

                    shoppingState = false;
                    btn_shopping.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_shopping.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    foodState = false;
                    btn_food.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_food.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    restState = false;
                    btn_rest.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_rest.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    historyState = false;
                    btn_history.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_history.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    natureState = false;
                    btn_nature.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_nature.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

                else {
                    leisureState = false;
                    btn_leisure.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_shopping.setTextColor(getResources().getColor(R.color.blue, getTheme()));
                }
            }
        });

        //역사
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!historyState){
                    historyState = true;
                    btn_history.setBackgroundResource(R.drawable.border);
                    //btn_history.setTextColor(getResources().getColor(R.color.white, getTheme()));

                    shoppingState = false;
                    btn_shopping.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_shopping.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    foodState = false;
                    btn_food.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_food.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    restState = false;
                    btn_rest.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_leisure.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    leisureState = false;
                    btn_leisure.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_history.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    natureState = false;
                    btn_nature.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_nature.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

                else {
                    historyState = false;
                    btn_history.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_shopping.setTextColor(getResources().getColor(R.color.blue, getTheme()));
                }
            }
        });

        //자연
        btn_nature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!natureState){
                    natureState = true;
                    btn_nature.setBackgroundResource(R.drawable.border);
                    //btn_history.setTextColor(getResources().getColor(R.color.white, getTheme()));

                    shoppingState = false;
                    btn_shopping.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_shopping.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    foodState = false;
                    btn_food.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_food.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    restState = false;
                    btn_rest.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_leisure.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    historyState = false;
                    btn_history.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_history.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    leisureState = false;
                    btn_leisure.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_nature.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

                else {
                    natureState = false;
                    btn_nature.setBackgroundResource(R.drawable.border_12r_grey);
                    //btn_shopping.setTextColor(getResources().getColor(R.color.blue, getTheme()));
                }
            }
        });
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
