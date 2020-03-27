package com.example.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MainActivityUpcomingFragment fragment1;
    MainActivityPastFragment fragment2;
    FrameLayout container;

    ImageButton up_delete, past_delete;
    Button btn_upcoming, btn_past;

    boolean upcomingState=false, pastState=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fragment1 = new MainActivityUpcomingFragment();
        fragment2 = new MainActivityPastFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();

        btn_upcoming = (Button) findViewById(R.id.btn_upcoming);
        btn_past = (Button) findViewById(R.id.btn_past);

        btn_upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                btn_upcoming.setTextColor(getColor(R.color.black));
                btn_past.setTextColor(getColor(R.color.soft_grey));
            }

        });


        btn_past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                btn_past.setTextColor(getColor(R.color.black));
                btn_upcoming.setTextColor(getColor(R.color.soft_grey));
            }
        });

    }
    //travel title set
    public void onClick(View view1){

        Intent intent = new Intent(this, TravelTitleSet.class);
        startActivity(intent);
    }

}
