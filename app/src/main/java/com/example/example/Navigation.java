package com.example.example;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.fragment.app.FragmentManager;

import static java.sql.DriverManager.println;

public class Navigation extends AppCompatActivity{
    ImageButton planButton, walletButton, reportButton, profileButton;
    RelativeLayout bottomBar;
    FrameLayout container;

    String time, title, memo;
    double budget;
    int size;
    String[] timeList;
    int mainPosition;

    int startYear, startMonth, startDay, startDoW, endYear, endMonth, endDay, endDoW, budgetType, lodgingType, prefType;
    String travelTitle;
    double budgetTotal, lodgingBudget, foodBudget, leisureBudget, shoppingBudget, transportBudget, etcBudget;

    SQLiteDatabase database;


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_navigation);

        Intent intent2 = getIntent();
        startYear = intent2.getIntExtra("startYear", 0);
        startMonth = intent2.getIntExtra("startMonth", 0);
        startDay = intent2.getIntExtra("startDay", 0);
        //startDoW = intent2.getIntExtra("startDoW", 0);
        endYear = intent2.getIntExtra("endYear", 0);
        endMonth = intent2.getIntExtra("endMonth", 0);
        endDay = intent2.getIntExtra("endDay", 0);
        //endDoW = intent2.getIntExtra("endDoW", 0);
        travelTitle = intent2.getStringExtra("travelTitle");
        budgetTotal = intent2.getDoubleExtra("budget", 0);
        Log.d("budgetTotal", String.valueOf(budgetTotal));
        int dDay = returnDday(startYear, startMonth, startDay);
        mainPosition = intent2.getIntExtra("mainPosition", 0);

        openDatabase("database");
        if (database != null && travelTitle != null) {
            //_id 는 내부적으로 생성되는 아이디!
            String sql = "insert into MainTable(position, start_year, start_month, start_day, end_year, end_month, end_day, d_day, title, total_budget, state) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Object[] params = {mainPosition, startYear, startMonth, startDay, endYear, endMonth, endDay, dDay, travelTitle, budgetTotal, 0};
            database.execSQL(sql, params);

           sql = "select title, d_day from "+ "MainTable";
            Cursor cursor = database.rawQuery(sql, null);
            //println("조회된 데이터 개수: "+cursor.getCount());

            for(int i=0; i<cursor.getCount(); i++){
                cursor.moveToNext();
                String title = cursor.getString(0);
                double d_day = cursor.getDouble(1);
                //int month = cursor.getInt(2);

                Log.d("database", "#"+i+"->"+title+", "+d_day);
            }

            cursor.close();

        } else {

        }

        Fragment plan = new PlanInitialActivity();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("startYear", startYear);
        bundle.putInt("startMonth", startMonth);
        bundle.putInt("startDay", startDay);
        bundle.putInt("startDoW", startDoW);
        bundle.putInt("endYear", endYear);
        bundle.putInt("endMonth", endMonth);
        bundle.putInt("endDay", endDay);
        bundle.putInt("endDoW", endDoW);
        bundle.putDouble("total", budgetTotal);
        bundle.putInt("mainPosition", mainPosition);
        //Toast.makeText(getApplicationContext(), title+"\n"+ startYear+" "+startMonth+" "+startDay+" "+startDoW+
        //        "\n"+endYear+" "+endMonth+" "+endDay+" "+endDoW, Toast.LENGTH_LONG).show();

        plan.setArguments(bundle);
        transaction.add(R.id.container, plan);
        transaction.commit();

        bottomBar = (RelativeLayout) findViewById(R.id.bottomBar);

        planButton = (ImageButton) findViewById(R.id.planButton);
        walletButton = (ImageButton) findViewById(R.id.walletButton);
        reportButton = (ImageButton) findViewById(R.id.reportButton);
        profileButton = (ImageButton) findViewById(R.id.profileButton);

        planButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goPlan();
                planButton.setBackgroundResource(R.drawable.ic_plan_bar_selected);
                walletButton.setBackgroundResource(R.drawable.ic_wallet_bar);
                reportButton.setBackgroundResource(R.drawable.ic_report_bar);
                profileButton.setBackgroundResource(R.drawable.ic_profile_bar);
            }
        });

        walletButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goWallet();
                planButton.setBackgroundResource(R.drawable.ic_plan_bar);
                walletButton.setBackgroundResource(R.drawable.ic_wallet_bar_selected);
                reportButton.setBackgroundResource(R.drawable.ic_report_bar);
                profileButton.setBackgroundResource(R.drawable.ic_profile_bar);
            }
        });

        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goReport();
                planButton.setBackgroundResource(R.drawable.ic_plan_bar);
                walletButton.setBackgroundResource(R.drawable.ic_wallet_bar);
                reportButton.setBackgroundResource(R.drawable.ic_report_bar_selected);
                profileButton.setBackgroundResource(R.drawable.ic_profile_bar);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goProfile();
                planButton.setBackgroundResource(R.drawable.ic_plan_bar);
                walletButton.setBackgroundResource(R.drawable.ic_wallet_bar);
                reportButton.setBackgroundResource(R.drawable.ic_report_bar);
                profileButton.setBackgroundResource(R.drawable.ic_profile_bar_selected);
            }
        });

        container = findViewById(R.id.container);

    }

    public void goPlan(){
        Fragment fragment;
        fragment = new PlanInitialActivity();
        Bundle bundle = new Bundle();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        bundle.putInt("startYear", startYear);
        bundle.putInt("startMonth", startMonth);
        bundle.putInt("startDay", startDay);
        bundle.putInt("startDoW", startDoW);
        bundle.putInt("endYear", endYear);
        bundle.putInt("endMonth", endMonth);
        bundle.putInt("endDay", endDay);
        bundle.putDouble("total", budgetTotal);
        bundle.putInt("mainPosition", mainPosition);
        fragment.setArguments(bundle);
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    public void goWallet(){
        Fragment fragment;
        fragment = new WalletMain();
        Bundle bundle = new Bundle();
        bundle.putInt("startYear", startYear);
        bundle.putInt("startMonth", startMonth);
        bundle.putInt("startDay", startDay);
        bundle.putInt("startDoW", startDoW);
        bundle.putInt("endYear", endYear);
        bundle.putInt("endMonth", endMonth);
        bundle.putInt("endDay", endDay);
        bundle.putInt("mainPosition", mainPosition);
        fragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void goReport(){
        Fragment fragment;
        Bundle bundle = new Bundle();
        fragment = new Report();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        bundle.putInt("mainPosition", mainPosition);
        fragment.setArguments(bundle);
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void goProfile(){
        Fragment fragment;
        fragment = new Profile();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void getPlanInfo(int size, String[] time, String[] title, String[] memo, double[] budget){
        timeList = new String[size];

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("startYear", startYear);
        intent.putExtra("startMonth", startMonth);
        intent.putExtra("startDay", startDay);
        intent.putExtra("startDoW", startDoW);
        intent.putExtra("endYear", endYear);
        intent.putExtra("endMonth", endMonth);
        intent.putExtra("endDay", endDay);
        intent.putExtra("endDoW", endDoW);
        intent.putExtra("budget", budgetTotal);
        intent.putExtra("travelTitle", travelTitle);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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

    public void openDatabase(String databaseName){
        DatabaseHelper helper = new DatabaseHelper(getApplicationContext(), databaseName, null, 4);
        database = helper.getWritableDatabase();
    }

    class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            if(db != null){
                //_id 는 내부적으로 생성되는 아이디!


                //println("테이블 생성됨.");
            }else{
                //println("먼저 데이터베이스를 오픈하세요.");
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //println("onUpgrade 호출됨: "+oldVersion + ", " + newVersion);

            if(newVersion > 1) {

            }
        }
    }

}
