package com.example.example;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.content.Intent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.content.Intent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Report extends Fragment {
    ReportDaily fragment1;
    ReportCategory fragment2;


    LinearLayout btn_daily, btn_category;
    TextView txt1, txt2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_report, container, false);
        fragment1 = new ReportDaily();
        fragment2 = new ReportCategory();

        getChildFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();

        btn_daily = rootView.findViewById(R.id.btn_daily);
        txt1 = rootView.findViewById(R.id.txt1);
        btn_daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                getChildFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                txt2.setTextColor(getResources().getColor(R.color.soft_grey));
                txt1.setTextColor(getResources().getColor(R.color.black));
            }

        });

        btn_category = rootView.findViewById(R.id.btn_category);
        txt2 = rootView.findViewById(R.id.txt2);
        btn_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                getChildFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                txt1.setTextColor(getResources().getColor(R.color.soft_grey));
                txt2.setTextColor(getResources().getColor(R.color.black));
            }
        });

        return rootView;
    }
}
