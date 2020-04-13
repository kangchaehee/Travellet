package com.example.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    MainActivityUpcomingFragment fragment1;
    MainActivityPastFragment fragment2;
    LinearLayout btn_upcoming, btn_past;

    int startYear, startMonth, startDay, endYear, endMonth, endDay, dDay;
    String travelTitle;
    float budget, lodgingBudget, foodBudget, leisureBudget, shoppingBudget, transportBudget, etcBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fragment1 = new MainActivityUpcomingFragment();
        fragment2 = new MainActivityPastFragment();

        Intent intent2 = getIntent();
        if(intent2 != null){
            if(intent2.getIntExtra("startYear", 0) != 0){
                startYear = intent2.getIntExtra("startYear", 0);
                startMonth = intent2.getIntExtra("startMonth", 0);
                startDay = intent2.getIntExtra("startDay", 0);
                endYear = intent2.getIntExtra("endYear", 0);
                endMonth = intent2.getIntExtra("endMonth", 0);
                endDay = intent2.getIntExtra("endDay", 0);
                budget = intent2.getFloatExtra("budget", 0);
                lodgingBudget = intent2.getFloatExtra("lodgingBudget", 0);
                foodBudget = intent2.getFloatExtra("foodBudget", 0);
                leisureBudget = intent2.getFloatExtra("leisureBudget", 0);
                shoppingBudget = intent2.getFloatExtra("shoppingBudget", 0);
                transportBudget = intent2.getFloatExtra("transportBudget", 0);
                etcBudget = intent2.getFloatExtra("etcBudget", 0);
                travelTitle = intent2.getStringExtra("travelTitle");


                Bundle bundle = new Bundle();
                bundle.putInt("startYear", startYear);
                bundle.putInt("startMonth", startMonth);
                bundle.putInt("startDay", startDay);
                bundle.putInt("endYear", endYear);
                bundle.putInt("endMonth", endMonth);
                bundle.putInt("endDay", endDay);
                bundle.putString("travelTitle", travelTitle);
                //Log.d("putBundle: ", travelTitle);
                bundle.putFloat("budget", budget);
                bundle.putFloat("lodgingBudget", lodgingBudget);
                bundle.putFloat("foodBudget", foodBudget);
                bundle.putFloat("leisureBudget", leisureBudget);
                bundle.putFloat("shoppingBudget", shoppingBudget);
                bundle.putFloat("transportBudget", transportBudget);
                bundle.putFloat("etcBudget", etcBudget);

                dDay = returnDday(startYear, startMonth, startDay);
                if(dDay >=0){
                    if(dDay == 0){
                        bundle.putString("dDay", "D-Day");
                    }
                    else
                        bundle.putString("dDay", "D+"+dDay);
                    fragment1.setArguments(bundle);
                }

                else {
                    bundle.putString("dDay", "D-" + dDay);
                    fragment2.setArguments(bundle);
                }
            }
        }

        //시간이 지남에 따라 디데이 바뀌는 경우는 db 추가하고 여러 데이터 있을 때 할 수 있을 것 같다.

        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();

        btn_upcoming = findViewById(R.id.btn_upcoming);
        btn_past = findViewById(R.id.btn_past);
        TextView upcomingTxt = findViewById(R.id.textView3);
        TextView pastTxt = findViewById(R.id.textView2);
        View line1 = findViewById(R.id.line);
        View line2 = findViewById(R.id.line2);

        btn_upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                upcomingTxt.setTextColor(getColor(R.color.black));
                pastTxt.setTextColor(getColor(R.color.soft_grey));
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.INVISIBLE);
            }

        });


        btn_past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                upcomingTxt.setTextColor(getColor(R.color.soft_grey));
                pastTxt.setTextColor(getColor(R.color.black));
                line1.setVisibility(View.INVISIBLE);
                line2.setVisibility(View.VISIBLE);
            }
        });

    }
    //travel title set
    public void onClick(View view1){

        Intent intent = new Intent(this, TravelTitleSet.class);
        startActivity(intent);
    }

    public int returnDday(int endYear, int endMonth, int endDay){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Calendar todayCal = Calendar.getInstance(); //오늘날짜
            Calendar ddayCal = Calendar.getInstance(); //디데이 날짜 계산할거임

            endMonth -= 1;// 받아온날자에서 -1을 해줘야함.
            ddayCal.set(endYear, endMonth, endDay);// D-day의 날짜를 입력

            long today = todayCal.getTimeInMillis()/86400000; //->(24 * 60 * 60 * 1000) 24시간 60분 60초 * (ms초->초 변환 1000)
            long dday = ddayCal.getTimeInMillis()/86400000;
            long count = dday - today; // 오늘 날짜에서 dday 날짜 빼
            return (int) count;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

}
