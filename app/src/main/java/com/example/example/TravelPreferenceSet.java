package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class TravelPreferenceSet extends AppCompatActivity {

    Handler handler = new Handler();

    boolean linear1State=false, linear2State=false, linear3State=false, linear4State=false, linear5State=false, linear6State=false;

    private View linear1;
    private View linear2;
    private View linear3;
    private View linear4;
    private View linear5;
    private View linear6;

    int prefType = 0;
    //prefType 1=쇼핑, 2=음식, 3=휴양, 4=레져, 5=역사, 6=자연, 0=선택없음.

    ImageView shopping, food, rest, leisure, history, nature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
                    prefType = 1;
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
                    prefType = 0;
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
                    prefType = 2;
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
                    prefType = 0;
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
                    prefType = 3;
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
                    prefType = 0;
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
                    prefType = 4;
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
                    prefType = 0;
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
                    prefType = 5;
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
                    prefType = 0;
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
                    prefType = 6;
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
                    prefType = 0;
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
        Intent intent2 = getIntent();
        int startYear = intent2.getIntExtra("startYear", 0);
        int startMonth = intent2.getIntExtra("startMonth", 0);
        int startDay = intent2.getIntExtra("startDay", 0);
        int startDoW = intent2.getIntExtra("startDoW", 0);
        int endYear = intent2.getIntExtra("endYear", 0);
        int endMonth = intent2.getIntExtra("endMonth", 0);
        int endDay = intent2.getIntExtra("endDay", 0);
        int endDoW = intent2.getIntExtra("endDoW", 0);
        String title = intent2.getStringExtra("travelTitle");
        int budgetType = intent2.getIntExtra("budgetType", 0);
        int budget = intent2.getIntExtra("budget", 0);
        int lodgingType = intent2.getIntExtra("lodgingType", 0);

        if(prefType==0){
            Toast.makeText(getApplicationContext(), "Please select your preference type.", Toast.LENGTH_LONG).show();
        }

        else{
            Intent intent = new Intent(TravelPreferenceSet.this, TravelEstimatedBudget.class);
            intent.putExtra("startYear", startYear);
            intent.putExtra("startMonth", startMonth);
            intent.putExtra("startDay", startDay);
            intent.putExtra("startDoW", startDoW);
            intent.putExtra("endYear", endYear);
            intent.putExtra("endMonth", endMonth);
            intent.putExtra("endDay", endDay);
            intent.putExtra("endDoW", endDoW);
            intent.putExtra("travelTitle", title);
            intent.putExtra("budgetType", budgetType);
            intent.putExtra("budget", budget);
            intent.putExtra("lodgingType", lodgingType);
            intent.putExtra("prefType", prefType);
            //Toast.makeText(getApplicationContext(), title+"\n"+ startYear+" "+startMonth+" "+startDay+" "+startDoW+
             //       "\n"+endYear+" "+endMonth+" "+endDay+" "+endDoW
              //      +"\n"+budgetType+"\n"+budget+"\n"+lodgingType+"\n"+prefType, Toast.LENGTH_LONG).show();
            startActivity(intent);
            overridePendingTransition(0, 0);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0, 0);
    }
}
