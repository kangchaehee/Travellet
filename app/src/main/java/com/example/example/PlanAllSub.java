package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class PlanAllSub extends LinearLayout {
    TextView day, period;
    ListView listView;

    public PlanAllSub(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public PlanAllSub(Context context) {
        super(context);

        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_plan_all_sub, this, true);

        day = findViewById(R.id.day);
        period = findViewById(R.id.period);
        listView = findViewById(R.id.con);
    }

    public void setDay(){

    }

}
