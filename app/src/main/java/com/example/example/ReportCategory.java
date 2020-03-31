package com.example.example;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
//import com.github.mikephil.charting.utils.Highlight;
//import com.github.mikephil.charting.utils.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

public class ReportCategory extends Fragment {

    PieChart chart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_report_category, container, false);
        PieChart pieChart = rootView.findViewById(R.id.piechart);

        ArrayList NoOfEmp = new ArrayList();

        NoOfEmp.add(new Entry(10, 0));
        NoOfEmp.add(new Entry(20, 1));
        NoOfEmp.add(new Entry(15, 2));
        NoOfEmp.add(new Entry(15, 3));
        NoOfEmp.add(new Entry(20, 4));
        NoOfEmp.add(new Entry(20, 5));


        PieDataSet dataSet = new PieDataSet(NoOfEmp, " Of Budget");

        ArrayList category = new ArrayList();

        category.add("Lodging");
        category.add("Food");
        category.add("Leisure");
        category.add("Shopping");
        category.add("Transport");
        category.add("Etc");

        PieData data = new PieData(category, dataSet);
        // MPAndroidChart v3.X 오류 발생

        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        //투명 원의 색상
        // 원형 차트의 구멍 옆에 그려진 투명 원의 반경을 최대 반경의 백분율로 설정합니다 (최대 = 전체 차트의 반경). 기본값 55 %->는 중심 구멍보다 5 % 더 큼 기본
        float percent = 80;
        pieChart.setTransparentCircleRadius(percent);
        // x 값 텍스트를 파이 조각에 그리려면 true로 설정하십시오.
        //setDrawSliceText(boolean enabled)

        //이 옵션을 사용하면 차트 내의 값이 원래 값이 아닌 백분율로 표시됩니다. To ValueFormatter형식에 제공된 값 은 백분율로 제공됩니다.
        //setUsePercentValues(boolean enabled)

        //PieChart의 중앙에 그려진 텍스트를 설정합니다. 더 긴 텍스트는 자동으로 "줄 바꿈"되어 파이 조각에 클리핑되지 않습니다.
        //setCenterText(SpannableString text):

        //중심 텍스트에 대한 경계 상자의 사각형 반경을 파이 구멍 기본값 1.f (100 %)의 백분율로 설정합니다.
        //setCenterTextRadiusPercent(float percent)

        //원형 차트의 중심에있는 구멍의 반경을 최대 반경의 백분율로 설정합니다 (최대 = 전체 차트의 반경). 기본값은 50 %
        //        setHoleRadius(float percent):

        // 투명 원의 색상을 설정합니다.
        //setTransparentCircleColor(int color)

        //투명 원의 투명도 (0-255)를 설정합니다.
        //setTransparentCircleAlpha(int alpha)

        //원을 계산하는 데 사용되는 최대 각도를 설정합니다. 360f는 전체 PieChart파이 차트이고 180f는 하프 파이 차트를 의미합니다. 기본값 : 360f
        //setMaxAngle(float maxangle)

        //pieChart.animateXY(5000, 5000);
        return rootView;
    }
}
