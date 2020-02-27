package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class TravelBudgetShow extends AppCompatActivity {
    Handler handler = new Handler();

    Button btn_frugal, btn_middle, btn_luxurious;
    boolean frugalState=false, middleState=false, luxuriousState=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_budget_show);
        btn_frugal = (Button) findViewById(R.id.btn_frugal);
        btn_middle = (Button) findViewById(R.id.btn_middle);
        btn_luxurious = (Button) findViewById(R.id.btn_luxurious);

        btn_frugal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!frugalState){
                    frugalState = true;
                    btn_frugal.setBackgroundResource(R.drawable.border_blue);

                    middleState = false;
                    btn_middle.setBackgroundResource(R.drawable.border_12r_grey);

                    luxuriousState = false;
                    btn_luxurious.setBackgroundResource(R.drawable.border_12r_grey);
                }

                else {
                    frugalState = false;
                    btn_frugal.setBackgroundResource(R.drawable.border_12r_grey);
                }
            }
        });

        //중간
        btn_middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!middleState){
                    middleState = true;
                    btn_middle.setBackgroundResource(R.drawable.border_blue);

                    frugalState = false;
                    btn_frugal.setBackgroundResource(R.drawable.border_12r_grey);

                    luxuriousState = false;
                    btn_luxurious.setBackgroundResource(R.drawable.border_12r_grey);
                }

                else {
                    middleState = false;
                    btn_middle.setBackgroundResource(R.drawable.border_12r_grey);
                }
            }
        });

        //럭셔리
        btn_luxurious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!luxuriousState){
                    luxuriousState = true;
                    btn_luxurious.setBackgroundResource(R.drawable.border_blue);

                    frugalState = false;
                    btn_frugal.setBackgroundResource(R.drawable.border_12r_grey);

                    middleState = false;
                    btn_middle.setBackgroundResource(R.drawable.border_12r_grey);
                }

                else {
                    luxuriousState = false;
                    btn_luxurious.setBackgroundResource(R.drawable.border_12r_grey);
                }
            }
        });
    }

    //calendar
    public void onButtonClick(View view1){

        Intent intent = new Intent(this, TravelCalendar.class);
        startActivity(intent);
    }

    public void onClick(View view){

        Intent intent = new Intent(TravelBudgetShow.this, BudgetSet.class);
        startActivity(intent);
    }
}
