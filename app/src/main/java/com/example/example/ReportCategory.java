package com.example.example;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
//import com.github.mikephil.charting.utils.Highlight;
//import com.github.mikephil.charting.utils.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

public class ReportCategory extends Fragment {

    PieChart chart;
    float percent, hole;

    TextView chartPer, chartText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_report_category, container, false);
        PieChart pieChart = rootView.findViewById(R.id.piechart);
        chartPer = rootView.findViewById(R.id.chartPer);
        chartText = rootView.findViewById(R.id.charText);


        chartPer.setText("10.0%");
        chartText.setText("Lodging");

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
        category.add("Leisure/Culture");
        category.add("Shopping");
        category.add("Transport");
        category.add("Etc");

        PieData data = new PieData(category, dataSet);
        // MPAndroidChart v3.X 오류 발생
        pieChart.setData(data);
        //dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        int colors [] = {getResources().getColor(R.color.category1), getResources().getColor(R.color.category2), getResources().getColor(R.color.category3), getResources().getColor(R.color.category4), getResources().getColor(R.color.category5), getResources().getColor(R.color.category6)};
        dataSet.setColors(colors);
        dataSet.setDrawValues(false);

        // 하단 x-Values List 안보이게. (색깔과 설명)
        pieChart.getLegend().setEnabled(false);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

                chartPer.setText(String.valueOf(e.getVal())+"%");
                int i = e.getXIndex();
                switch (i){
                    case 0:
                        chartText.setText("Lodging");
                        break;
                    case 1:
                        chartText.setText("Food");
                        break;
                    case 2:
                        chartText.setText("Leisure/Culture");
                        break;
                    case 3:
                        chartText.setText("Shopping");
                        break;
                    case 4:
                        chartText.setText("Transport");
                        break;
                    case 5:
                        chartText.setText("Etc");
                        break;

                }
            }

            @Override
            public void onNothingSelected() {
                chartPer.setText("10.0%");
                chartText.setText("Lodging");
            }
        });

        //퍼센트 나오게
        //pieChart.getLegend().setUsePercentValues(true);

        // 원형 차트의 구멍 옆에 그려진 투명 원의 반경을 최대 반경의 백분율로 설정합니다 (최대 = 전체 차트의 반경). 기본값 55 %->는 중심 구멍보다 5 % 더 큼 기본
        float percent = 80;
        pieChart.setTransparentCircleRadius(percent);

        //x 값 텍스트를 파이 조각에 그리려면 true로 설정하십시오.
        pieChart.setDrawSliceText(false);

        pieChart.setDescription("");
        //차트 내의 값이 원래 값이 아닌 백분율로 표시됩니다. To ValueFormatter형식에 제공된 값은 백분율로 제공됩니다.
        pieChart.setUsePercentValues(false);



        //PieChart의 중앙에 그려진 텍스트를 설정합니다. 더 긴 텍스트는 자동으로 "줄 바꿈"되어 파이 조각에 클리핑되지 않습니다.
        //PieChart.setCenterText(SpannableString text):

        //중심 텍스트에 대한 경계 상자의 사각형 반경을 파이 구멍 기본값 1.f (100 %)의 백분율로 설정합니다.
        //setCenterTextRadiusPercent(float percent)

        //원형 차트의 중심에있는 구멍의 반경을 최대 반경의 백분율로 설정합니다 (최대 = 전체 차트의 반경). 기본값은 50 %
        float hole = 80;
        pieChart.setHoleRadius(hole);

        //퍼센트 출력하는

        // 투명 원의 색상을 설정
        pieChart.setTransparentCircleColor(R.color.white);

        //투명 원의 투명도 (0-255)를 설정
        //int alpha = 255;
        //pieChart.setTransparentCircleAlpha(alpha);

        //원을 계산하는 데 사용되는 최대 각도를 설정합니다.
        //360f는 전체 PieChart이고 180f는 하프 파이 차트를 의미
        //기본값 : 360f
        //setMaxAngle(float maxangle)

        //애니메이션
        //pieChart.animateXY(5000, 5000);

        return rootView;
    }
}
