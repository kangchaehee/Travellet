package com.example.example.feature.plan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.example.R;
import com.example.example.feature.plan.PlanAllSub;


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
