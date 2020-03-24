package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TravelPreferenceSet extends AppCompatActivity {

    Handler handler = new Handler();

    boolean linear1State=false, linear2State=false, linear3State=false, linear4State=false, linear5State=false, linear6State=false;

    private View linear1;
    private View linear2;
    private View linear3;
    private View linear4;
    private View linear5;
    private View linear6;

    ImageView shopping, food, rest, leisure, history, nature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_preference_set);

        linear1 = (View) findViewById(R.id.linear1);
        linear2 = (View) findViewById(R.id.linear2);
        linear3 = (View) findViewById(R.id.linear3);
        linear4 = (View) findViewById(R.id.linear4);
        linear5 = (View) findViewById(R.id.linear5);
        linear6 = (View) findViewById(R.id.linear6);

        shopping = (ImageView) findViewById(R.id.shopping);
        food = (ImageView) findViewById(R.id.food);
        rest = (ImageView) findViewById(R.id.rest);
        leisure = (ImageView) findViewById(R.id.leisure);
        history = (ImageView) findViewById(R.id.history);
        nature = (ImageView) findViewById(R.id.nature);

        //쇼핑
        linear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!linear1State){
                    linear1State = true;
                    linear1.setBackgroundResource(R.drawable.border_blue);
                    food.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    rest.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    leisure.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    history.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    nature.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    shopping.setColorFilter(Color.parseColor("#427dff"), PorterDuff.Mode.SRC_IN );

                    linear2State = false;
                    linear2.setBackgroundResource(R.drawable.border_12r_grey);

                    linear3State = false;
                    linear3.setBackgroundResource(R.drawable.border_12r_grey);

                    linear4State = false;
                    linear4.setBackgroundResource(R.drawable.border_12r_grey);

                    linear5State = false;
                    linear5.setBackgroundResource(R.drawable.border_12r_grey);

                    linear6State = false;
                    linear6.setBackgroundResource(R.drawable.border_12r_grey);

                }

                else {
                    linear1State = false;
                    linear1.setBackgroundResource(R.drawable.border_12r_grey);
                    shopping.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                }
            }
        });

        linear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!linear2State){
                    linear2State = true;
                    linear2.setBackgroundResource(R.drawable.border_blue);
                    shopping.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    rest.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    leisure.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    history.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    nature.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    food.setColorFilter(Color.parseColor("#427dff"), PorterDuff.Mode.SRC_IN );

                    linear1State = false;
                    linear1.setBackgroundResource(R.drawable.border_12r_grey);

                    linear3State = false;
                    linear3.setBackgroundResource(R.drawable.border_12r_grey);

                    linear4State = false;
                    linear4.setBackgroundResource(R.drawable.border_12r_grey);

                    linear5State = false;
                    linear5.setBackgroundResource(R.drawable.border_12r_grey);

                    linear6State = false;
                    linear6.setBackgroundResource(R.drawable.border_12r_grey);
                }

                else {
                    linear2State = false;
                    linear2.setBackgroundResource(R.drawable.border_12r_grey);
                    food.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );

                }
            }
        });

        linear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!linear3State){
                    linear3State = true;
                    linear3.setBackgroundResource(R.drawable.border_blue);
                    shopping.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    food.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    leisure.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    history.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    nature.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    rest.setColorFilter(Color.parseColor("#427dff"), PorterDuff.Mode.SRC_IN );

                    linear1State = false;
                    linear1.setBackgroundResource(R.drawable.border_12r_grey);

                    linear2State = false;
                    linear2.setBackgroundResource(R.drawable.border_12r_grey);

                    linear4State = false;
                    linear4.setBackgroundResource(R.drawable.border_12r_grey);

                    linear5State = false;
                    linear5.setBackgroundResource(R.drawable.border_12r_grey);

                    linear6State = false;
                    linear6.setBackgroundResource(R.drawable.border_12r_grey);
                }

                else {
                    linear3State = false;
                    linear3.setBackgroundResource(R.drawable.border_12r_grey);
                    rest.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                }
            }
        });

        linear4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!linear4State){
                    linear4State = true;
                    linear4.setBackgroundResource(R.drawable.border_blue);
                    shopping.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    food.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    rest.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    history.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    nature.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    leisure.setColorFilter(Color.parseColor("#427dff"), PorterDuff.Mode.SRC_IN );

                    linear1State = false;
                    linear1.setBackgroundResource(R.drawable.border_12r_grey);

                    linear2State = false;
                    linear2.setBackgroundResource(R.drawable.border_12r_grey);

                    linear3State = false;
                    linear3.setBackgroundResource(R.drawable.border_12r_grey);

                    linear5State = false;
                    linear5.setBackgroundResource(R.drawable.border_12r_grey);

                    linear6State = false;
                    linear6.setBackgroundResource(R.drawable.border_12r_grey);
                }

                else {
                    linear4State = false;
                    linear4.setBackgroundResource(R.drawable.border_12r_grey);
                    leisure.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                }
            }
        });

        linear5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!linear5State){
                    linear5State = true;
                    linear5.setBackgroundResource(R.drawable.border_blue);
                    shopping.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    food.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    rest.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    leisure.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    nature.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    history.setColorFilter(Color.parseColor("#427dff"), PorterDuff.Mode.SRC_IN );

                    linear1State = false;
                    linear1.setBackgroundResource(R.drawable.border_12r_grey);

                    linear2State = false;
                    linear2.setBackgroundResource(R.drawable.border_12r_grey);

                    linear3State = false;
                    linear3.setBackgroundResource(R.drawable.border_12r_grey);

                    linear4State = false;
                    linear4.setBackgroundResource(R.drawable.border_12r_grey);

                    linear6State = false;
                    linear6.setBackgroundResource(R.drawable.border_12r_grey);
                }

                else {
                    linear5State = false;
                    linear5.setBackgroundResource(R.drawable.border_12r_grey);
                    history.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                }
            }
        });

        linear6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!linear6State){
                    linear6State = true;
                    linear6.setBackgroundResource(R.drawable.border_blue);
                    shopping.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    food.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    rest.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    leisure.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    history.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    nature.setColorFilter(Color.parseColor("#427dff"), PorterDuff.Mode.SRC_IN );

                    linear1State = false;
                    linear1.setBackgroundResource(R.drawable.border_12r_grey);

                    linear2State = false;
                    linear2.setBackgroundResource(R.drawable.border_12r_grey);

                    linear3State = false;
                    linear3.setBackgroundResource(R.drawable.border_12r_grey);

                    linear4State = false;
                    linear4.setBackgroundResource(R.drawable.border_12r_grey);

                    linear5State = false;
                    linear5.setBackgroundResource(R.drawable.border_12r_grey);
                }

                else {
                    linear6State = false;
                    linear6.setBackgroundResource(R.drawable.border_12r_grey);
                    nature.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                }

            }
        });
    }

    //Lodging set
    public void onButtonClick(View view1){
        Intent intent = new Intent(TravelPreferenceSet.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    // Estimated budget
    public void onClick(View view){

        Intent intent = new Intent(TravelPreferenceSet.this, TravelEstimatedBudget.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0, 0);
    }
}
