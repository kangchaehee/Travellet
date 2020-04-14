package com.example.example;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import java.util.ArrayList;


public class PlanAllFragment extends Fragment {
    Button addButton, placeSearch;
    Button withFriends;
    TextView day, period;

    int startYear, startMonth, startDay, endYear, endMonth, endDay;
    int periodInt;
    int mainPosition;

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.plan_all_fragment, container, false);
        LinearLayout con = rootView.findViewById(R.id.lin);

        if (getArguments() != null) {
            mainPosition = getArguments().getInt("mainPosition", 0);
            periodInt = getArguments().getInt("period", 0);
            startYear = getArguments().getInt("startYear", 0);
            startDay = getArguments().getInt("startDay", 0);
            startMonth = getArguments().getInt("startMonth", 0);
            for (int i = 0; i < periodInt; i++) {
                int date = i+1;
                PlanAllSub planItem = new PlanAllSub(getContext());
                planItem.setDay(date, mainPosition);
                planItem.setPeriod(startYear, startMonth, startDay+i);
                planItem.setListView();
                con.addView(planItem);
            }
        }

        return rootView;
    }
}
