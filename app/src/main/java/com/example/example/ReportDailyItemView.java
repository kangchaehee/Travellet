package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ReportDailyItemView extends LinearLayout {

    TextView chartMon, chartDay, total;
    //LinearLayout linear;

    public ReportDailyItemView(Context context) {
        super(context);
        init(context);
    }

    public ReportDailyItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_report_daily, this, true);

        TextView chartMon, chartDay, total;
        //LinearLayout linear;

        chartMon = (TextView) findViewById(R.id.chartMon);
        chartDay = (TextView) findViewById(R.id.chartDay);
        total = (TextView) findViewById(R.id.total);
    }

    public void setChartMon(TextView chartMon){this.chartMon.setText((CharSequence) chartMon);}

    public void setChartDay(TextView chartDay){this.chartDay.setText((CharSequence) chartDay);}

    public void setTotal(TextView total){this.total.setText((CharSequence) total);}

}
