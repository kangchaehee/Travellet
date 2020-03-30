package com.example.example;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class day_sub extends LinearLayout {
    TextView day, dow;

    public day_sub(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public day_sub(Context context) {
        super(context);

        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.day_sub, this, true);

        day = findViewById(R.id.day);
        dow = findViewById(R.id.dow);
    }

    public void setDay(int day){
        this.day.setText(String.valueOf(day));
    }

    public void setDoW(String dow){
        this.dow.setText(dow);
    }
}
