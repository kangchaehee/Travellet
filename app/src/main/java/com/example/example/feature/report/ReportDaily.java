package com.example.example.feature.report;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Index;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.Resource;
import com.example.example.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.XAxisValueFormatter;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.renderer.XAxisRendererHorizontalBarChart;
import com.github.mikephil.charting.utils.ColorTemplate;

import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
//import com.github.mikephil.charting.utils.PercentFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import lecho.lib.hellocharts.formatter.AxisValueFormatter;


public class ReportDaily extends Fragment {

    BarChart chart;
    float barSpace, barWidth, groupSpace;
    int groupCount;
    int a, b, c, d, x, y, day;
    int startYear, startMonth, startDay, startDoW, endYear, endMonth, endDay, endDoW, period;

    LinearLayout linear8;
    HorizontalScrollView scroll;

    TextView chartDay, chartMon;
    SQLiteDatabase database;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_report_daily, null);
        initView(view);
        initChart();

        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.roboto_regular);

        return view;
    }

    private void initView(View view) {
        chart = (BarChart) view.findViewById(R.id.barchart);
        chartMon = (TextView) view.findViewById(R.id.chartMon);
        chartDay = (TextView) view.findViewById(R.id.chartDay);

    }

    private void initChart(){

        int a = 4 ; // 여행 날짜 수 endDay - startDay
        List<String> xvalue=new ArrayList<>();

            // xvalue 에 day 넣는 거
        for (b = 1 ; b < a +1 ; b++){
            xvalue.add("Day" + b);
        }

        /*
        xvalue.add("Day1");
        xvalue.add("Day2");
        xvalue.add("Day3");
        xvalue.add("Day4");
        xvalue.add("Day5");
        xvalue.add("Day6");
        */

        List<BarEntry> yvalue = new ArrayList<>();

            //cash, card
        yvalue.add(new BarEntry(new float[]{12, 100},0));
        yvalue.add(new BarEntry(new float[]{0, 52},1));
        yvalue.add(new BarEntry(new float[]{18, 0},2));
        yvalue.add(new BarEntry(new float[]{23, 1},3));
        //yvalue.add(new BarEntry(new float[]{80, 20},4));
        //yvalue.add(new BarEntry(new float[]{100, 10},5));


        BarDataSet set=new BarDataSet(yvalue,"");
        BarData data=new BarData(xvalue,set);

        int colors [] = {getResources().getColor(R.color.category3), getResources().getColor(R.color.category5)};
        set.setColors(colors); //color
        set.setBarSpacePercent(50f); // 바 사이 간격
        set.setDrawValues(false); // 바 수치
        //set.isStacked(); //
        //set.isDrawValuesEnabled();
        //set.setValueTextSize(9f);
        //set.setValueTextColor(Color.parseColor("#c8cbd3"));


        chart.getXAxis().setAxisMaxValue(5f);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.parseColor("#c8cbd3")); // icon grey
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        //xAxis.setSpaceBetweenLabels(); //데이 사이 간격


        YAxis left = chart.getAxisLeft();
        left.setDrawLabels(true); // axis labels
        left.setDrawAxisLine(true); // axis line
        left.setDrawGridLines(true); // grid lines
        left.setTextColor(Color.parseColor("#c8cbd3")); // icon grey
        left.setTextSize(11f);
        left.setDrawZeroLine(true); // draw a zero line

        chart.getAxisRight().setEnabled(false); // no right axis
        chart.setScaleEnabled(false);
        chart.setDescription(" ");
        chart.setData(data);
        chart.setHorizontalScrollBarEnabled(true);

        chart.isClickable();
        //chart.setViewPortOffsets(0f, 0f, 0f, 0f); // day 랑 그래프 띄어져 있는 공간 넓이

        float barWidth = 0.3f;
        float groupSpace= 0.06f;
        float barSpace = 0.0f;
        int groupCount = 3;

        chart.getBarData().setGroupSpace(groupSpace);
        chart.getBarData().setGroupSpace(barSpace);
        chart.getBarData().setGroupSpace(barWidth);

        chart.getXValCount();
        Legend l = chart.getLegend();
        l.setEnabled(false); //밑에 색깔 설명

        chart.getWidth();
        chart.isHorizontalScrollBarEnabled();
        chart.setDoubleTapToZoomEnabled(false); // 줌인
        chart.setDrawValueAboveBar(true);


        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

                int t = 1;
                int i = e.getXIndex(); //day 값
                //chartMon.setText(String.valueOf(e.getVal()) + ",000" + " ￦" )
                chartMon.setText(String.format("%.0f", e.getVal())+ ",000" + " ￦"); //소수점 없애는거
                chartDay.setText("Day" + String.valueOf(e.getXIndex() + 1));
            }

            @Override
            public void onNothingSelected() {
            }
        });

        /*
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

                int t = 1;
                int i = e.getXIndex(); //day 값
                //chartMon.setText(String.valueof(e.getVal()) + ",000" + " ￦" )
                //chartMon.setText(String.format("%.0f", e.getVal())+ ",000" + " ￦"); //소수점 없애는거
                //chartDay.setText("day"+ i + t);

                for (int s = 0; s ==6 ; s++){

                    System.out.println(String.format("%.0f", e.getVal())+ ",000" + " ￦");
                    System.out.println("day"+ i + t);
                    //chartMon.setText(String.format("%.0f", e.getVal())+ ",000" + " ￦"); //소수점 없애는거
                    //chartDay.setText("day"+ i + t);

                }
            }

            @Override
            public void onNothingSelected() {
                //chartMon.setText("130,000 ￦");
                //chartDay.setText("Day 1");

            }
        });
         */
    }
}
