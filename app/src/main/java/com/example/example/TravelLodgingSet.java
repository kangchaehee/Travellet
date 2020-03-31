package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class TravelLodgingSet extends AppCompatActivity {

    Handler handler = new Handler();

    boolean linear1State=false, linear2State=false, linear3State=false;

    private View linear1;
    private View linear2;
    private View linear3;

    ImageView lodging1, lodging2, lodging3;
    int lodgingType=0;
    //lodgingType이 1=hotel, 2=콘도, 펜션, 레지던스, 3=유스호스텔, 게스트하우스, 0=선택없음.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_lodging_set);

        linear1 = (View) findViewById(R.id.linear1);
        linear2 = (View) findViewById(R.id.linear2);
        linear3 = (View) findViewById(R.id.linear3);

        lodging1 = (ImageView) findViewById(R.id.lodging1);
        lodging2 = (ImageView) findViewById(R.id.lodging2);
        lodging3 = (ImageView) findViewById(R.id.lodging3);

        linear1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v1) {

                if(!linear1State){
                    linear1State = true;
                    lodgingType=1;
                    linear1.setBackgroundResource(R.drawable.border_blue);
                    lodging2.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    lodging3.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    lodging1.setColorFilter(Color.parseColor("#427dff"), PorterDuff.Mode.SRC_IN );

                    linear2State = false;
                    linear2.setBackgroundResource(R.drawable.border_12r_grey);

                    linear3State = false;
                    linear3.setBackgroundResource(R.drawable.border_12r_grey);

                }

                else {
                    linear1State = false;
                    lodgingType=0;
                    linear1.setBackgroundResource(R.drawable.border_12r_grey);
                    lodging1.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                }
            }
        });

        linear2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v2) {
                if(!linear2State){
                    linear2State = true;
                    lodgingType=2;
                    linear2.setBackgroundResource(R.drawable.border_blue);
                    lodging1.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    lodging3.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    lodging2.setColorFilter(Color.parseColor("#427dff"), PorterDuff.Mode.SRC_IN );

                    linear1State = false;
                    linear1.setBackgroundResource(R.drawable.border_12r_grey);

                    linear3State = false;
                    linear3.setBackgroundResource(R.drawable.border_12r_grey);

                }

                else {
                    linear2State = false;
                    lodgingType=0;
                    linear2.setBackgroundResource(R.drawable.border_12r_grey);
                    lodging2.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );

                }
            }
        });

        linear3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v3) {
                if(!linear3State){
                    linear3State = true;
                    lodgingType=3;
                    linear3.setBackgroundResource(R.drawable.border_blue);
                    lodging1.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    lodging2.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    lodging3.setColorFilter(Color.parseColor("#427dff"), PorterDuff.Mode.SRC_IN );

                    linear1State = false;
                    linear1.setBackgroundResource(R.drawable.border_12r_grey);

                    linear2State = false;
                    linear2.setBackgroundResource(R.drawable.border_12r_grey);
                }

                else {
                    linear3State = false;
                    lodgingType=0;
                    linear3.setBackgroundResource(R.drawable.border_12r_grey);
                    lodging3.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                }
            }
        });
    }

    //travel title set
    public void onButtonClick(View view1){
        Intent intent = new Intent(TravelLodgingSet.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    //travel preference set
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
        if(lodgingType==0){
            Toast.makeText(getApplicationContext(), "Please select your lodging type.", Toast.LENGTH_LONG).show();

        }

        else{
            Intent intent = new Intent(TravelLodgingSet.this, TravelPreferenceSet.class);
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
            /*Toast.makeText(getApplicationContext(), title+"\n"+ startYear+" "+startMonth+" "+startDay+" "+startDoW+
                    "\n"+endYear+" "+endMonth+" "+endDay+" "+endDoW
                    +"\n"+budgetType+"\n"+budget+"\n"+lodgingType, Toast.LENGTH_LONG).show();*/
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
