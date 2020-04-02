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
    String[] days;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_report_daily, container, false);

        BarChart Chart = (BarChart) rootView.findViewById(R.id.barchart);
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.setDescription("");
        chart.setMaxVisibleValueCount(50);
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);

        ArrayList<BarEntry> entries1 = new ArrayList<>();
        ArrayList<BarEntry> entries2 = new ArrayList<>();

        entries1.add(new BarEntry(100f, 0));
        entries1.add(new BarEntry(150f, 1));
        entries1.add(new BarEntry(200f, 2));
        entries1.add(new BarEntry(500f, 3));
        entries1.add(new BarEntry(1000f, 4));
        entries1.add(new BarEntry(500f, 5));

        entries2.add(new BarEntry(1400f, 0));
        entries2.add(new BarEntry(100f, 1));
        entries2.add(new BarEntry(300f, 2));
        entries2.add(new BarEntry(200f, 3));
        entries2.add(new BarEntry(800f, 4));
        entries2.add(new BarEntry(600f, 5));

        BarDataSet barDataSet1 = new BarDataSet(entries1, "cash");
        barDataSet1.setColor(R.color.light_blue);

        BarDataSet barDataSet2 = new BarDataSet(entries2, "card");
        barDataSet2.setColor(R.color.blue);

        BarData data = new BarData(barDataSet1, barDataSet2);
        chart.setData(data);
        data.setValueFormatter(new LargeValueFormatter());

        barWidth = 0.3f;
        barSpace = 0f;
        groupSpace = 0.4f;

        chart.setData(data);
        chart.getBarData().setBarWidth(barWidth);
        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        chart.groupBars(0, groupSpace, barSpace);
        chart.getData().setHighlightEnabled(false);
        chart.invalidate();

        /*
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(true);
        l.setYOffset(20f);
        l.setXOffset(0f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);
        */
        return rootView;
    }
}
