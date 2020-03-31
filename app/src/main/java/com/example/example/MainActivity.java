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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    MainActivityUpcomingFragment fragment1;
    MainActivityPastFragment fragment2;
    Button btn_upcoming, btn_past;

    int startYear, startMonth, startDay, endYear, endMonth, endDay, dDay;
    String travelTitle;
    float lodgingBudget, foodBudget, leisureBudget, shoppingBudget, transportBudget, etcBudget;

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
                travelTitle = intent2.getStringExtra("travelTitle");

                Toast.makeText(getApplicationContext(), travelTitle, Toast.LENGTH_LONG).show();

                Bundle bundle = new Bundle();
                bundle.putInt("startYear", startYear);
                bundle.putInt("startMonth", startMonth);
                bundle.putInt("startDay", startDay);
                bundle.putInt("endYear", endYear);
                bundle.putInt("endMonth", endMonth);
                bundle.putInt("endDay", endDay);
                bundle.putString("travelTitle", travelTitle);

                dDay = returnDday(startYear, startMonth, startDay);
                if(dDay >=0){
                    if(dDay == 0){
                        bundle.putString("dDay", "D-Day");
                    }
                    else
                        bundle.putString("dDay", "D + "+dDay);
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
