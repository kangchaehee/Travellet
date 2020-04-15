package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ReportDailyItem extends AppCompatActivity {

    TextView chartMon, chartDay, total;
    //LinearLayout linear;

    public ReportDailyItem(TextView chartMon, TextView chartDay, TextView total) {

        this.chartMon = chartMon;
        this.chartDay = chartDay;
        this.total = total;

    }

    public TextView getchartMon() { return chartMon; }

    public void setChartMon(TextView chartMon) { this.chartMon = chartMon; }

    public TextView getChartDay() {return chartDay; }

    public void setChartDay(TextView chartDay) { this.chartDay = chartDay; }

    public TextView gettotal() {return total; }

    public void settotal(TextView total) { this.total = total; }


    @Override
    public String toString() {
        return "ReportDailyItem{" +
                "total='" + total +
                ", chartMon='" +chartMon +
                ", chartDay=" + chartDay +
                '}';
    }
}
