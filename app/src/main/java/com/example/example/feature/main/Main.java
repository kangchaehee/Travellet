package com.example.example.feature.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.example.R;
import com.example.example.feature.main.set.TravelTitleSet;
import com.example.example.data.ProfileData;
import com.example.example.data.ProfileResponse;
import com.example.example.network.InfoID;
import com.example.example.network.RetrofitClient;
import com.example.example.network.ServiceApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main extends AppCompatActivity {

    private ServiceApi service;

    MainUpcomingFragment fragment1;
    MainPastFragment fragment2;
    LinearLayout btn_upcoming, btn_past;
    TextView name;

    int startYear, startMonth, startDay, endYear, endMonth, endDay, dDay;
    String travelTitle;
    float budget, lodgingBudget, foodBudget, leisureBudget, shoppingBudget, transportBudget, etcBudget;
    int mainPosition;

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        setName(new ProfileData(InfoID.userId));

        fragment1 = new MainUpcomingFragment();
        fragment2 = new MainPastFragment();
        name = (TextView) findViewById(R.id.name);

        openDatabase("database");
        if (database != null && travelTitle != null) {
            //_id 는 내부적으로 생성되는 아이디!
            String sql = "create table if not exists " + "MainTable" + "(_id integer PRIMARY KEY autoincrement, position integer, start_year integer, start_month integer, start_day integer, end_year integer, end_month integer, end_day integer, d_day integer, title text, total_budget double, lodging_budget double, food_budget double, shopping_budget double, tourism_budget double, etc_budget double, transport_budget double, state integer)";
            database.execSQL(sql);
        }

        Intent intent2 = getIntent();
        if (intent2 != null) {
            if (intent2.getIntExtra("startYear", 0) != 0) {
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
                if (dDay >= 0) {
                    if (dDay == 0) {
                        bundle.putString("dDay", "D-Day");
                    } else
                        bundle.putString("dDay", "D+" + dDay);
                    fragment1.setArguments(bundle);
                } else {
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

    // 메인 이름 설정 통신 메소드
    private void setName(ProfileData data) {
        service.userProfile(data).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                ProfileResponse result = response.body();
                if (result.getCode() == 200) {
                    name.setText(result.getUserName());
                    Log.d("MainTitle", name.getText().toString());
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Log.e("메인 이름 설정 에러 발생", t.getMessage());
            }
        });
    }


    //travel title set
    public void onClick(View view1) {
        Intent intent = new Intent(this, TravelTitleSet.class);
        String sql = "select title from " + "MainTable";
        Cursor cursor = database.rawQuery(sql, null);
        int position = cursor.getCount();
        Log.d("putBundle: ", position+"");
        intent.putExtra("mainPosition", position);
        startActivity(intent);
    }

    public int returnDday(int endYear, int endMonth, int endDay) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Calendar todayCal = Calendar.getInstance(); //오늘날짜
            Calendar ddayCal = Calendar.getInstance(); //디데이 날짜 계산할거임

            endMonth -= 1;// 받아온날자에서 -1을 해줘야함.
            ddayCal.set(endYear, endMonth, endDay);// D-day의 날짜를 입력

            long today = todayCal.getTimeInMillis() / 86400000; //->(24 * 60 * 60 * 1000) 24시간 60분 60초 * (ms초->초 변환 1000)
            long dday = ddayCal.getTimeInMillis() / 86400000;
            long count = dday - today; // 오늘 날짜에서 dday 날짜 빼
            return (int) count;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void openDatabase(String databaseName) {
        DatabaseHelper helper = new DatabaseHelper(getApplicationContext(), databaseName, null, 4);
        database = helper.getWritableDatabase();
    }

    class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            if (db != null) {
                //_id 는 내부적으로 생성되는 아이디!


                //println("테이블 생성됨.");
            } else {
                //println("먼저 데이터베이스를 오픈하세요.");
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //println("onUpgrade 호출됨: "+oldVersion + ", " + newVersion);

            if (newVersion > 1) {

            }
        }
    }
}
