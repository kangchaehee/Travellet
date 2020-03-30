package com.example.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MainActivityUpcomingFragment fragment1;
    MainActivityPastFragment fragment2;
    Button btn_upcoming, btn_past;


    int startYear, startMonth, startDay, startDoW, endYear, endMonth, endDay, endDoW, budgetType, budgetTotal, lodgingType, prefType;
    String travelTitle;
    float lodgingBudget, foodBudget, leisureBudget, shoppingBudget, transportBudget, etcBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent2 = getIntent();
        startYear = intent2.getIntExtra("startYear", 0);
        startMonth = intent2.getIntExtra("startMonth", 0);
        startDay = intent2.getIntExtra("startDay", 0);
        startDoW = intent2.getIntExtra("startDoW", 0);
        endYear = intent2.getIntExtra("endYear", 0);
        endMonth = intent2.getIntExtra("endMonth", 0);
        endDay = intent2.getIntExtra("endDay", 0);
        endDoW = intent2.getIntExtra("endDoW", 0);
        travelTitle = intent2.getStringExtra("travelTitle");
        budgetType = intent2.getIntExtra("budgetType", 0);
        budgetTotal = intent2.getIntExtra("budget", 0);
        lodgingType = intent2.getIntExtra("lodgingType", 0);
        prefType = intent2.getIntExtra("prefType", 0);
        lodgingBudget = intent2.getFloatExtra("lodgingBudget", 0);


        fragment1 = new MainActivityUpcomingFragment();
        fragment2 = new MainActivityPastFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();

        btn_upcoming = (Button) findViewById(R.id.btn_upcoming);
        btn_past = (Button) findViewById(R.id.btn_past);

        btn_upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                btn_upcoming.setTextColor(getColor(R.color.black));
                btn_past.setTextColor(getColor(R.color.soft_grey));
            }

        });


        btn_past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                btn_past.setTextColor(getColor(R.color.black));
                btn_upcoming.setTextColor(getColor(R.color.soft_grey));
            }
        });

    }
    //travel title set
    public void onClick(View view1){

        Intent intent = new Intent(this, TravelTitleSet.class);
        startActivity(intent);
    }

}
