package com.example.example;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.odsay.odsayandroidsdk.API;
import com.odsay.odsayandroidsdk.ODsayData;
import com.odsay.odsayandroidsdk.ODsayService;
import com.odsay.odsayandroidsdk.OnResultCallbackListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static android.content.Context.MODE_PRIVATE;

public class PlanInitialActivity extends Fragment {
    // github test
    ArrayList<day_sub> dayItems = new ArrayList<day_sub>();

    LinearLayout planner;
    FrameLayout con;

    FragmentCallBack callback;

    int startYear, startMonth, startDay, startDoW, endYear, endMonth, endDay, endDoW, period;
    double budgetTotal;
    int lodging=0, food=0, leisure=0, shopping=0, transport=0, etc=0;

    SQLiteDatabase database;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentCallBack){
            callback = (FragmentCallBack) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(callback != null){
            callback = null;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_plan_initial, container, false);
        //버전 상향에 따른 네트워크 연결 조정
        if (Build.VERSION.SDK_INT > 28) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        if(getArguments() != null){
            startYear = getArguments().getInt("startYear", 0);
            startDay = getArguments().getInt("startDay", 0);
            startMonth = getArguments().getInt("startMonth", 0);
            startDoW = getArguments().getInt("startDow", 0);
            endYear = getArguments().getInt("endYear", 0);
            endMonth = getArguments().getInt("endMonth", 0);
            endDay = getArguments().getInt("endDay", 0);
            endDoW = getArguments().getInt("endDow", 0);
            budgetTotal = getArguments().getDouble("total", 0);
        }

        planner = (LinearLayout) rootView.findViewById(R.id.planner);
        day_sub dayItem = new day_sub(getContext());
        dayItem.setDay(0);
        dayItem.setDoW("PLAN");
        planner.addView(dayItem);
        dayItems.add(dayItem);
        addDay(startYear, startMonth, startDay, endYear, endMonth, endDay);
        dayItems.get(1).selectItem();

        con = (FrameLayout) rootView.findViewById(R.id.con);
        Fragment fragment;
        fragment = new PlanInitialFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index", 1);
        bundle.putInt("startYear", startYear);
        bundle.putInt("startMonth", startMonth);
        bundle.putInt("startDay", startDay);
        bundle.putInt("endYear", endYear);
        bundle.putInt("endMonth", endMonth);
        bundle.putInt("endDay", endDay);
        bundle.putDouble("total", budgetTotal);
        fragment.setArguments(bundle);
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.con, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        //openDatabase("database");
        //createTable("planTable");


        day_sub all = dayItems.get(0);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //추가되는 일정 저장은 Db연결하고
                for(int i=1; i<dayItems.size(); i++) {
                    day_sub item = dayItems.get(i);
                    int finalI = i;
                    item.unItem();
                }
                all.selectItem();
                Fragment fragment;
                fragment = new PlanAllFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("index", 0);
                bundle.putInt("startYear", startYear);
                bundle.putInt("startMonth", startMonth);
                bundle.putInt("startDay", startDay);
                bundle.putInt("endYear", endYear);
                bundle.putInt("endMonth", endMonth);
                bundle.putInt("endDay", endDay);
                fragment.setArguments(bundle);
                FragmentManager manager = getChildFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.con, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        int size = dayItems.size();
        for(int i=1; i<size; i++){
            day_sub item = dayItems.get(i);
            int finalI = i;
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //추가되는 일정 저장은 Db연결하고
                    for(int i=0; i<dayItems.size(); i++) {
                        day_sub item = dayItems.get(i);
                        int finalI = i;
                        item.unItem();
                    }
                    item.selectItem();

                    Calendar startCal = Calendar.getInstance();
                    int m = startMonth-1;
                    startCal.set(startYear, m, startDay);
                    startCal.add(Calendar.DATE, finalI-1);
                    int startYear = startCal.get(Calendar.YEAR);
                    int startMonth = startCal.get(Calendar.MONTH);
                    int startDay = startCal.get(Calendar.DAY_OF_MONTH);

                    Fragment fragment;
                    fragment = new PlanInitialFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("index", finalI);
                    bundle.putInt("startYear", startYear);
                    bundle.putInt("startMonth", startMonth);
                    Log.d("startMonth: ", String.valueOf(startMonth));
                    bundle.putInt("startDay", startDay);
                    fragment.setArguments(bundle);
                    FragmentManager manager = getChildFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.con, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        }
        return rootView;
    }


    public void addDay(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay){
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd"); //년월일 표시

        startMonth -=1;
        endMonth -=1;
        startCal.set(startYear, startMonth, startDay);
        String startDate = dateFormat.format(startCal.getTime());
        endCal.set(endYear, endMonth, endDay);
        String endDate = dateFormat.format(endCal.getTime());
        Log.d("period", startDate+"\n"+endDate);

        int i=0;
        while(!startDate.equals(endDate)){ //다르다면 실행, 동일 하다면 빠져나감

            if(i==0) { //최초 실행 출력
                int day = startCal.get(Calendar.DAY_OF_MONTH);
                int dow = startCal.get(Calendar.DAY_OF_WEEK);

                day_sub dayItem = new day_sub(getContext());
                dayItems.add(dayItem);
                dayItem.setDay(day);
                switch (dow) {
                    case 1:
                        dayItem.setDoW("SUN");
                        break;
                    case 2:
                        dayItem.setDoW("MON");
                        break;
                    case 3:
                        dayItem.setDoW("TUE");
                        break;
                    case 4:
                        dayItem.setDoW("WED");
                        break;
                    case 5:
                        dayItem.setDoW("THU");
                        break;
                    case 6:
                        dayItem.setDoW("FRI");
                        break;
                    case 7:
                        dayItem.setDoW("SAT");
                        break;
                }
                planner.addView(dayItem);
            }

            startCal.add(Calendar.DATE, 1); //1일 더해줌
            startDate = dateFormat.format(startCal.getTime()); //비교를 위한 값 셋팅

            int day = startCal.get(Calendar.DAY_OF_MONTH);
            int dow = startCal.get(Calendar.DAY_OF_WEEK);

            day_sub dayItem = new day_sub(getContext());
            dayItems.add(dayItem);
            dayItem.setDay(day);
            switch (dow) {
                case 1:
                    dayItem.setDoW("SUN");
                    break;
                case 2:
                    dayItem.setDoW("MON");
                    break;
                case 3:
                    dayItem.setDoW("TUE");
                    break;
                case 4:
                    dayItem.setDoW("WED");
                    break;
                case 5:
                    dayItem.setDoW("THU");
                    break;
                case 6:
                    dayItem.setDoW("FRI");
                    break;
                case 7:
                    dayItem.setDoW("SAT");
                    break;
            }
            planner.addView(dayItem);

            i++;

        }
    }

    public void openDatabase(String databaseName){
        DatabaseHelper helper = new DatabaseHelper(getContext(), databaseName, null, 4);
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
                Log.d("database", "헬퍼 onCreate");
                String sql = "create table if not exists " + "planTable" + "(_id integer PRIMARY KEY autoincrement, date integer, year integer, month integer, type integer, place text, hour integer, min integer, memo text, transport integer, total_budget double)";
                db.execSQL(sql);

                sql = "create table if not exists " + "budgetTable" + "(_id integer PRIMARY KEY autoincrement, data integer, plan_id integer, type integer, budget double, memo text)";
                db.execSQL(sql);

                //println("테이블 생성됨.");
            }else{
                //println("먼저 데이터베이스를 오픈하세요.");
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //println("onUpgrade 호출됨: "+oldVersion + ", " + newVersion);

            if(newVersion > 1) {
                db.execSQL("drop table if exists " + "planTable");
                db.execSQL("drop table if exists " + "budgetTable");
                //println("테이블 삭제함");

                if (db != null) {
                    //_id 는 내부적으로 생성되는 아이디!
                    String sql = "create table if not exists " + "planTable" + "(_id integer PRIMARY KEY autoincrement, date integer, year integer, month integer, type integer, place text, hour integer, min integer, memo text, transport integer, total_budget double)";
                    db.execSQL(sql);

                    //_id 는 내부적으로 생성되는 아이디!
                    sql = "create table if not exists " + "budgetTable" + "(_id integer PRIMARY KEY autoincrement, data integer, plan_id integer, type integer, budget double, memo text";
                    db.execSQL(sql);

                    //println("테이블 새로 생성됨.");
                } else {
                    //println("먼저 데이터베이스를 오픈하세요.");
                }
            }
        }
    }

    public void createTable(String tableName){
        if(database != null){
            //_id 는 내부적으로 생성되는 아이디!
            Log.d("database", tableName);
            String sql = "create table if not exists " + tableName + "(_id integer PRIMARY KEY autoincrement, date integer, year integer, month integer, type integer, place text, hour integer, min integer, memo text, transport integer, total_budget double)";
            database.execSQL(sql);

        }else{
            Log.d("database faile", tableName);
        }
    }

    public void insertData(String name, int age, String mobile){

        if(database != null){
            //물음표에 해당하는 거를 object 배열을 이용해서 따로 넣을 수 있음.
            // 물음표 안 쓰고 바로 넣을 수도 있고!
            String sql = "insert into planTable(date, type) values(1, 1)";

            Object[] params = {name, age, mobile};

            Log.d("database", name);

            database.execSQL(sql, params);
        }else {
        }
    }
}
