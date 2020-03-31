package com.example.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import android.content.Intent;
import android.view.WindowManager;
import android.widget.Button;
import android.content.Intent;
import android.widget.FrameLayout;

public class Report extends AppCompatActivity {
    ReportDaily fragment1;
    ReportCategory fragment2;


    Button btn_daily, btn_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fragment1 = new ReportDaily();
        fragment2 = new ReportCategory();

        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();

        btn_daily = (Button) findViewById(R.id.btn_daily);
        btn_daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                btn_category.setTextColor(getColor(R.color.soft_grey));
                btn_daily.setTextColor(getColor(R.color.black));
            }

        });

        btn_category = (Button) findViewById(R.id.btn_category);
        btn_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                btn_daily.setTextColor(getColor(R.color.soft_grey));
                btn_category.setTextColor(getColor(R.color.black));
            }
        });



    }
}
