package com.example.example;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PlanInitialSubItemView extends LinearLayout {
    TextView placeTime, placeName, placeMemo, transBudgetText, transportText;
    ImageView transportIc, transportBudget;

    public PlanInitialSubItemView(Context context) {
        super(context);
        init(context);
    }

    public PlanInitialSubItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_plan_initial_sub_item, this, true);

        placeTime = (TextView) findViewById(R.id.place_time);
        placeName = (TextView) findViewById(R.id.placeName);
        placeMemo = (TextView) findViewById(R.id.placeMemo);
        transportIc = (ImageView) findViewById(R.id.transport_ic);
        transBudgetText = (TextView) findViewById(R.id.TransBudgetText);
        transportText = (TextView) findViewById(R.id.transportText);

    }
    public void setPlaceTime(String time) {
        this.placeTime.setText(time);
    }

    public void setPlaceName(String name) {
        this.placeName.setText(name);
    }

    public void setPlaceMemo(String memo) {
        this.placeMemo.setText(memo);
    }

    public void setTransportIc(int transport) {this.transportIc.setBackgroundResource(transport);}

    public void setTransportText(String transport){
        this.transportText.setText(transport);
    }

    public void setTransBudgetText(String budget){
        this.transBudgetText.setText(budget);
    }



}

