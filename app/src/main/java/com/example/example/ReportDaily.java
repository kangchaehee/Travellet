package com.example.example;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.room.Index;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    //String[] days;
    //TextView chartPer, chartText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_report_daily, null);
        initView(view);
        initChart();
        return view;
    }

    private void initView(View view) {
        chart = (BarChart) view.findViewById(R.id.barchart);
    }

    private void initChart(){

        List<BarEntry> yvalue=new ArrayList<>();
        yvalue.add(new BarEntry(new float[]{20,50},0));
        yvalue.add(new BarEntry(new float[]{80,100},1));
        yvalue.add(new BarEntry(new float[]{100,100},2));
        yvalue.add(new BarEntry(new float[]{120,140},3));
        yvalue.add(new BarEntry(new float[]{100,120},4));

        BarDataSet set=new BarDataSet(yvalue,"");
        int colors [] = {getResources().getColor(R.color.category3), getResources().getColor(R.color.category5)};
        set.setColors(colors);
        set.setDrawValues(false);

        //float barSpace= 11;
        //set.getBarSpace(barSpace);

        List<String> xvalue=new ArrayList<>();
        xvalue.add("Day1");
        xvalue.add("Day2");
        xvalue.add("Day3");
        xvalue.add("Day4");
        xvalue.add("Day5");

        BarData data=new BarData(xvalue,set);
        //chart.getBarData().setBarWidth(barWidth);

        set.setBarSpacePercent(20f);
        chart.getXAxis().setAxisMaxValue(5f);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(R.color.icon_grey);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);


        YAxis left = chart.getAxisLeft();
        left.setDrawLabels(true); // axis labels
        left.setDrawAxisLine(true); // axis line
        left.setDrawGridLines(true); // grid lines
        left.setTextColor(R.color.icon_grey);
        left.setDrawZeroLine(true); // draw a zero line
        chart.getAxisRight().setEnabled(false); // no right axis

        Legend l = chart.getLegend();
        l.setEnabled(false); //밑에 색깔 설명



        barWidth = 20;
        chart.setDescription("");
        chart.setData(data);



    }
}
