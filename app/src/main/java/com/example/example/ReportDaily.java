package com.example.example;

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
    int a, b, c, d, t, s;
    int startYear, startMonth, startDay, startDoW, endYear, endMonth, endDay, endDoW, period;

    LinearLayout linear8;
    HorizontalScrollView scroll;

    TextView chartDay, chartMon, chartCard, chartCash;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_report_daily, null);
        initView(view);
        initChart();
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.roboto_regular);

        if(getArguments() != null){

            startYear = getArguments().getInt("startYear", 0);
            startDay = getArguments().getInt("startDay", 0);
            startMonth = getArguments().getInt("startMonth", 0);
            startDoW = getArguments().getInt("startDow", 0);
            endYear = getArguments().getInt("endYear", 0);
            endMonth = getArguments().getInt("endMonth", 0);
            endDay = getArguments().getInt("endDay", 0);
            endDoW = getArguments().getInt("endDow", 0);

        }
        return view;
    }

    private void initView(View view) {
        chart = (BarChart) view.findViewById(R.id.barchart);
        chartMon = (TextView) view.findViewById(R.id.chartMon);
        chartDay = (TextView) view.findViewById(R.id.chartDay);
        //chartCard = (TextView) view.findViewById(R.id.chartCard);
        //chartCash = (TextView) view.findViewById(R.id.chartCash);

    }

    private void initChart(){

        int a = 4; // 여행 날짜 수 endDay - startDay?
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

        yvalue.add(new BarEntry(new float[]{10, 60},0));
        yvalue.add(new BarEntry(new float[]{30, 50},1));
        yvalue.add(new BarEntry(new float[]{50, 40},2));
        yvalue.add(new BarEntry(new float[]{60, 30},3));
        //yvalue.add(new BarEntry(new float[]{80, 20},4));
        //yvalue.add(new BarEntry(new float[]{100, 10},5));


        BarDataSet set=new BarDataSet(yvalue,"");
        BarData data=new BarData(xvalue,set);

        int colors [] = {getResources().getColor(R.color.category3), getResources().getColor(R.color.category5)};
        set.setColors(colors); //color
        set.setBarSpacePercent(50f); // 바 사이 간격
        set.setDrawValues(true); // 바 수치
        set.isStacked(); //
        set.isDrawValuesEnabled();
        set.setValueTextSize(9f);
        set.setValueTextColor(Color.parseColor("#c8cbd3"));


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
        //left.getValueFormatter();

        chart.getAxisRight().setEnabled(false); // no right axis
        chart.setScaleEnabled(false);
        chart.setDescription(" ");
        chart.setData(data);
        chart.setHorizontalScrollBarEnabled(true);
        //chart.setViewPortOffsets(0f, 0f, 0f, 0f); // day 랑 그래프 띄어져 있는 공간 넓이

        float barWidth = 0.3f; //0.3
        float groupSpace= 0.06f; //0.06
        float barSpace = 0.0f; //0.0
        int groupCount = 3; //3

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
        //chartMon.setText("130,000 ￦");
        //chartDay.setText("Day 1");
//
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


                /*
                chartDay.setText("Day 1");
                switch (i){
                    case 0:
                        chartDay.setText("Day 1");
                        break;
                    case 1:
                        chartDay.setText("Day 2");
                        break;
                    case 2:
                        chartDay.setText("Day 3");
                        break;
                    case 3:
                        chartDay.setText("Day 4");
                        break;
                    case 4:
                        chartDay.setText("Day 5");
                        break;
                    case 5:
                        chartDay.setText("Day 6");
                        break;
                    case 6:
                        chartDay.setText("Day 7");
                        break;
                    case 7:
                        chartDay.setText("Day 8");
                        break;
                    case 8:
                        chartDay.setText("Day 9");
                        break;
                    case 9:
                        chartDay.setText("Day 10");
                        break;
                    case 10:
                        chartDay.setText("Day 11");
                        break;
                    case 11:
                        chartDay.setText("Day 12");
                        break;
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
