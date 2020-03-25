package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MainActivityUpcomingFragment fragment1;
    MainActivityPastFragment fragment2;

    ImageButton up_delete, past_delete;
    Button btn_upcoming, btn_past;

    boolean upcomingState=false, pastState=false;
/*
    DeleteDialog oDialog;

    ListView listView;
    ArrayList<MainActivitySubItem> items = new ArrayList<MainActivitySubItem>();
    MainSubAdapter adapter = new MainSubAdapter();

    //ArrayList<PastFragment> items = new ArrayList<PastFragment>();
    //ArrayList<UpcomingFragment> item = new ArrayList<UpcomingFragment>();

 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_plan_initial);

        listView = (ListView) findViewById(R.id.container);
        listView.setAdapter(adapter);

         */

        fragment1 = new MainActivityUpcomingFragment();
        fragment2 = new MainActivityPastFragment();

        up_delete = (ImageButton) findViewById(R.id.up_delete);
        past_delete = (ImageButton) findViewById(R.id.past_delete);

        btn_upcoming = (Button) findViewById(R.id.btn_upcoming);
        btn_upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {

                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                if(!upcomingState){
                    upcomingState = true;
                    btn_past.setTextColor(getColor(R.color.soft_grey));
                    btn_upcoming.setTextColor(getColor(R.color.black));

                    pastState = false;
                    btn_past.setTextColor(getColor(R.color.soft_grey));
                }
                else {
                    upcomingState = false;
                    btn_upcoming.setTextColor(getColor(R.color.soft_grey));
                }
            }
        });

        btn_past = (Button) findViewById(R.id.btn_past);
        btn_past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();

                if(!pastState){
                    pastState = true;
                    btn_upcoming.setTextColor(getColor(R.color.soft_grey));
                    btn_past.setTextColor(getColor(R.color.black));

                    upcomingState = false;
                    btn_upcoming.setTextColor(getColor(R.color.soft_grey));
                }
                else {
                    pastState = false;
                    btn_past.setTextColor(getColor(R.color.soft_grey));
                }
            }
        });

        //oDialog = new DeleteDialog(this);

    }



    //travel title set
    public void onClick(View view1){

        Intent intent = new Intent(this, TravelTitleSet.class);
        startActivity(intent);
    }

}
