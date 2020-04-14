package com.example.example;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;

public class day_sub extends LinearLayout {
    TextView day, dow;
    View line;

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
        line = findViewById(R.id.line);
    }

    public void setDay(int day){
        if(day == 0)
            this.day.setText(String.valueOf("ALL"));
        else
            this.day.setText(String.valueOf(day));
    }

    public void setDoW(String dow){
        this.dow.setText(dow);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void selectItem(){
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.roboto_bold);
        this.day.setTextColor(getResources().getColor(R.color.black));
        this.day.setTypeface(typeface);
        this.dow.setTextColor(getResources().getColor(R.color.black));
        this.dow.setTypeface(typeface);
        line.setVisibility(VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void unItem(){
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.roboto_regular);
        this.day.setTextColor(getResources().getColor(R.color.soft_grey));
        this.day.setTypeface(typeface);
        this.dow.setTextColor(getResources().getColor(R.color.soft_grey));
        this.dow.setTypeface(typeface);
        line.setVisibility(INVISIBLE);
    }
}
