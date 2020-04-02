package com.example.example;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
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

import java.util.ArrayList;
import java.util.List;


public class ReportDaily extends Fragment {

    BarChart chart;
    //float barSpace, barWidth, groupSpace;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_report_daily, container, false);

        BarChart chart = rootView.findViewById(R.id.barchart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 0));
        entries.add(new BarEntry(1f, 1));
        entries.add(new BarEntry(2f, 2));
        entries.add(new BarEntry(3f, 3));
        entries.add(new BarEntry(4f, 4));
        entries.add(new BarEntry(5f, 5));

        BarDataSet barDataSet = new BarDataSet(entries, "Inducesmile");
        //barDataSet.setBarBorderWidth(0.9f);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Day1");
        labels.add("Day2");
        labels.add("Day3");
        labels.add("Day4");
        labels.add("Day5");
        labels.add("Day6");

        BarData data = new BarData(labels, barDataSet);
        chart.setData(data); // set the data and list of labels into chart
        //chart.setDescription("Set Bar Chart Description Here");  // set the description

        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //chart.animateY(5000);

        return rootView;
    }
}
