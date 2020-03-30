package com.example.example;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class WalletMain extends Fragment {

    LinearLayout planner;
    FrameLayout con;

    int startYear, startMonth, startDay, startDoW, endYear, endMonth, endDay, endDoW;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_wallet_main, container, false);

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
        Log.d("fail", "argument is null.");

        planner = (LinearLayout) rootView.findViewById(R.id.planner);
        addDay(startYear, startMonth, startDay, endYear, endMonth, endDay);

        con = (FrameLayout) rootView.findViewById(R.id.con);
        Fragment fragment;
        fragment = new WalletMainFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("startYear", startYear);
        bundle.putInt("startMonth", startMonth);
        bundle.putInt("startDay", startDay);
        bundle.putInt("startDoW", startDoW);
        bundle.putInt("endYear", endYear);
        bundle.putInt("endMonth", endMonth);
        bundle.putInt("endDay", endDay);
        fragment.setArguments(bundle);
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.con, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

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