package com.example.example;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.room.Index;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
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
import java.util.List;

import lecho.lib.hellocharts.formatter.AxisValueFormatter;


public class ReportDaily extends Fragment {

    BarChart chart;
    float barSpace, barWidth, groupSpace;
    float a, b;
    int groupCount;

    HorizontalScrollView scroll;

    TextView chartDay, chartMon, chartCard, chartCash;

    @Nullable
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
        chartCard = (TextView) view.findViewById(R.id.chartCard);
        chartCash = (TextView) view.findViewById(R.id.chartCash);

    }

    private void initChart(){

        //float a [] = {10, 20, 30, 40, 50, 60};
        //float b [] = {100, 120, 130, 140, 110, 150};


        List<BarEntry> yvalue=new ArrayList<>();
        yvalue.add(new BarEntry(new float[]{80,50},0));
        yvalue.add(new BarEntry(new float[]{80,100},1));
        yvalue.add(new BarEntry(new float[]{100,100},2));
        yvalue.add(new BarEntry(new float[]{120,140},3));
        yvalue.add(new BarEntry(new float[]{100,120},4));
        yvalue.add(new BarEntry(new float[]{10,120},5));
        //yvalue.add(new BarEntry(new float[]{100,100},6));
        //yvalue.add(new BarEntry(new float[]{120,140},7));
        //yvalue.add(new BarEntry(new float[]{100,120},8));
        //yvalue.add(new BarEntry(new float[]{10,120},9));

        BarDataSet set=new BarDataSet(yvalue,"");
        int colors [] = {getResources().getColor(R.color.category3), getResources().getColor(R.color.category5)};
        set.setColors(colors);
        set.setBarSpacePercent(50f); // 바 사이 간격... 감동이다.
        set.setDrawValues(false);
        //set.setBarSpacePercent(20f);
        set.isStacked();
        //set.setBarSpacePercent(11f);


        List<String> xvalue=new ArrayList<>();
        xvalue.add("Day1");
        xvalue.add("Day2");
        xvalue.add("Day3");
        xvalue.add("Day4");
        xvalue.add("Day5");
        xvalue.add("Day6");
        //xvalue.add("Day7");
        //xvalue.add("Day8");
        //xvalue.add("Day9");
        //xvalue.add("Day10");

        BarData data=new BarData(xvalue,set);

        chart.getXAxis().setAxisMaxValue(5f);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(11f);
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
       //chart.setViewPortOffsets(0f, 0f, 0f, 0f); // day 랑 그래프 띄어져 있는 공간 넓이

        float barWidth = 1f; //0.3
        float groupSpace= 1f; //0.06
        float barSpace = 1f; //0.0
        int groupCount = 3; //3


        chart.getBarData().setGroupSpace(groupSpace);
        chart.getBarData().setGroupSpace(barSpace);
        chart.getBarData().setGroupSpace(barWidth);
        //chart.setDescriptionTypeface(getResources().getFont(R.font.roboto_regular));

        //chart.groupBars(1f, groupSpace, barSpace);

        Legend l = chart.getLegend();
        l.setEnabled(false); //밑에 색깔 설명

        //chart.getBarData().setBarWidth(BAR_WIDTH);
        chart.getWidth();
        chart.canScrollHorizontally(1);
        chart.getScrollX();
        chart.isHorizontalScrollBarEnabled();
        chart.setDoubleTapToZoomEnabled(false); // 줌인

        chartMon.setText("130,000 ￦");
        chartDay.setText("Day 1");


        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

                //chartMon.setText(String.valueof(e.getVal()) + ",000" + " ￦" )
                chartMon.setText(String.format("%.0f", e.getVal())+ ",000" + " ￦"); //소수점 없애는거
                int X = e.getXIndex();


                int i = e.getXIndex();
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
                chartMon.setText("130,000 ￦");
                chartDay.setText("Day 1");
            }
        });

    }
}
