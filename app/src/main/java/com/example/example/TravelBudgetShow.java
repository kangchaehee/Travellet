package com.example.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class TravelBudgetShow extends AppCompatActivity {

    Handler handler = new Handler();

    boolean linear1State=false, linear2State=false, linear3State=false;

    private View linear1;
    private View linear2;
    private View linear3;

    ImageView budget1, budget2, budget3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_budget_show);

        linear1 = (View) findViewById(R.id.linear1);
        linear2 = (View) findViewById(R.id.linear2);
        linear3 = (View) findViewById(R.id.linear3);

        budget1 = (ImageView) findViewById(R.id.budget1);
        budget2 = (ImageView) findViewById(R.id.budget2);
        budget3 = (ImageView) findViewById(R.id.budget3);

        linear1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v1) {

                if(!linear1State){
                    linear1State = true;
                    linear1.setBackgroundResource(R.drawable.border_blue);
                    budget2.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    budget3.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    budget1.setColorFilter(Color.parseColor("#427dff"), PorterDuff.Mode.SRC_IN );

                    linear2State = false;
                    linear2.setBackgroundResource(R.drawable.border_12r_grey);

                    linear3State = false;
                    linear3.setBackgroundResource(R.drawable.border_12r_grey);
                }

                else {
                    linear1State = false;
                    linear1.setBackgroundResource(R.drawable.border_12r_grey);
                    budget1.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );

                }
            }
        });

        linear2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v2) {
                if(!linear2State){
                    linear2State = true;
                    linear2.setBackgroundResource(R.drawable.border_blue);
                    budget1.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    budget3.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    budget2.setColorFilter(Color.parseColor("#427dff"), PorterDuff.Mode.SRC_IN );

                    linear1State = false;
                    linear1.setBackgroundResource(R.drawable.border_12r_grey);

                    linear3State = false;
                    linear3.setBackgroundResource(R.drawable.border_12r_grey);
                }

                else {
                    linear2State = false;
                    linear2.setBackgroundResource(R.drawable.border_12r_grey);
                    budget2.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );

                }
            }
        });

        linear3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v3) {
                if(!linear3State){
                    linear3State = true;
                    linear3.setBackgroundResource(R.drawable.border_blue);
                    budget1.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    budget2.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );
                    budget3.setColorFilter(Color.parseColor("#427dff"), PorterDuff.Mode.SRC_IN );

                    linear1State = false;
                    linear1.setBackgroundResource(R.drawable.border_12r_grey);

                    linear2State = false;
                    linear2.setBackgroundResource(R.drawable.border_12r_grey);
                }

                else {
                    linear3State = false;
                    linear3.setBackgroundResource(R.drawable.border_12r_grey);
                    budget3.setColorFilter(Color.parseColor("#dbdde4"), PorterDuff.Mode.SRC_IN );

                }
            }
        });
    }


    //calendar
    public void onButtonClick(View view1){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onClick(View view){

        Intent intent = new Intent(TravelBudgetShow.this, BudgetSet.class);
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
