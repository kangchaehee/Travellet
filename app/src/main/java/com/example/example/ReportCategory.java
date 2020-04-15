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
import java.util.Arrays;
import java.util.List;

public class ReportCategory extends Fragment {

    PieChart chart;
    float percent, hole;
    int i, e, array;

    TextView chartPer, chartText;
    TextView money_lodging, money_food, money_leisure, money_shopping, money_transport, money_etc;

    //float lodging, food, leisure, shopping, transport, etc;
    //float sum, per_a, per_b, per_c, per_d, per_e, per_f;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_report_category, container, false);
        PieChart pieChart = rootView.findViewById(R.id.piechart);
        chartPer = rootView.findViewById(R.id.chartPer);
        chartText = rootView.findViewById(R.id.chartText);

        //money_lodging = rootView.findViewById(R.id.money_lodging);
        //money_food = rootView.findViewById(R.id.money_food);
        //money_leisure = rootView.findViewById(R.id.money_leisure);
        //money_shopping = rootView.findViewById(R.id.money_shopping);
        //money_transport = rootView.findViewById(R.id.money_transport);
        //money_etc = rootView.findViewById(R.id.money_etc);


        ArrayList NoOfEmp = new ArrayList();

        //lodging 부터 etc 까지 값 받아들이기.
        /*
        float lodging;  //a
        float food;  //b
        float leisure;  //c
        float shopping;  //d
        float transport;  //e
        float etc;  //f

        float sum =  a+b+c+d+e+f;

        float per_a =a / sum;  // lodging 퍼센트
        float per_b =b / sum;  // food 퍼센트
        float per_c =c / sum;  // leisure 퍼센트
        float per_d =d / sum;  // shopping 퍼센트
        float per_e =e / sum;  // transport 퍼센트
        float per_f =f / sum;  // etc 퍼센트

        NoOfEmp.add(new Entry(per_a, 0));
        NoOfEmp.add(new Entry(per_b, 1));
        NoOfEmp.add(new Entry(per_c, 2));
        NoOfEmp.add(new Entry(per_d, 3));
        NoOfEmp.add(new Entry(per_e, 4));
        NoOfEmp.add(new Entry(per_f, 5));
        */

        NoOfEmp.add(new Entry(10, 0));
        NoOfEmp.add(new Entry(10, 1));
        NoOfEmp.add(new Entry(15, 2));
        NoOfEmp.add(new Entry(15, 3));
        NoOfEmp.add(new Entry(20, 4));
        NoOfEmp.add(new Entry(30, 5));

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

        chartPer.setText("10.0%");
        //chartPer.setText(a + "%");
        chartText.setText("Lodging"); //lodging 으로 고정


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

        // 원형 차트의 구멍 옆에 그려진 투명 원의 반경을 최대 반경의 백분율로 설정합니다 (최대 = 전체 차트의 반경). 기본값 55 %->는 중심 구멍보다 5 % 더 큼 기본
        float percent = 80;
        pieChart.setTransparentCircleRadius(percent);

        //x 값 텍스트를 파이 조각에 그리려면 true로 설정하십시오.
        pieChart.setDrawSliceText(false);

        pieChart.setDescription("");
        //차트 내의 값이 원래 값이 아닌 백분율로 표시됩니다. To ValueFormatter형식에 제공된 값은 백분율로 제공됩니다.
        pieChart.setUsePercentValues(false);

        //원형 차트의 중심에있는 구멍의 반경을 최대 반경의 백분율로 설정합니다 (최대 = 전체 차트의 반경). 기본값은 50 %
        float hole = 80;
        pieChart.setHoleRadius(hole);

        // 투명 원의 색상을 설정
        pieChart.setTransparentCircleColor(R.color.white);

        //투명 원의 투명도 (0-255)를 설정
        //int alpha = 255;
        //pieChart.setTransparentCircleAlpha(alpha);

        //원을 계산하는 데 사용되는 최대 각도를 설정합니다.
        //360f는 전체 PieChart이고 180f는 하프 파이 차트를 의미
        //기본값 : 360f
        //setMaxAngle(float maxangle)


        /*
            // 밑에 텍스트
        money_lodging.setText(lodging + "￦");
        money_food.setText(food + "￦");
        money_leisure.setText(leisure + "￦");
        money_shopping.setText(shopping + "￦");
        money_transport.setText(transport + "￦");
        money_etc.setText(etc + "￦");

              //소수점 없애고 싶을 땐 이거
        money_lodging.setText(String.format("%.0f", lodging)+ " ￦");
        money_food.setText(String.format("%.0f", lodging)+ " ￦");
        money_leisure.setText(String.format("%.0f", lodging)+ " ￦");
        money_shopping.setText(String.format("%.0f", lodging)+ " ￦");
        money_transport.setText(String.format("%.0f", lodging)+ " ￦");
        money_etc.setText(String.format("%.0f", lodging)+ " ￦");

         */

        return rootView;
    }



}
