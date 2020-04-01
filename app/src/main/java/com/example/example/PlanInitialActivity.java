package com.example.example;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

public class PlanInitialActivity extends Fragment {
    // github test
    ArrayList<day_sub> dayItems = new ArrayList<day_sub>();

    LinearLayout planner;
    FrameLayout con;

    FragmentCallBack callback;

    int startYear, startMonth, startDay, startDoW, endYear, endMonth, endDay, endDoW, period;

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
            Log.d("success", String.valueOf(startYear));
        }

        planner = (LinearLayout) rootView.findViewById(R.id.planner);
        day_sub dayItem = new day_sub(getContext());
        dayItem.setDay(0);
        dayItem.setDoW("PLAN");
        planner.addView(dayItem);
        dayItems.add(dayItem);
        addDay(startYear, startMonth, startDay, endYear, endMonth, endDay);
        dayItem.selectItem();

        con = (FrameLayout) rootView.findViewById(R.id.con);
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
}
