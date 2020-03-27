package com.example.example;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.FragmentManager;

public class Navigation extends AppCompatActivity implements FragmentCallBack{
    ImageButton planButton, walletButton, reportButton, profileButton;
    RelativeLayout bottomBar;

    String time, title, memo;
    double budget;
    int size;
    String[] timeList;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_navigation);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, new PlanInitialActivity());
        transaction.commit();

        bottomBar = (RelativeLayout) findViewById(R.id.bottomBar);

        planButton = (ImageButton) findViewById(R.id.planButton);
        walletButton = (ImageButton) findViewById(R.id.walletButton);
        reportButton = (ImageButton) findViewById(R.id.reportButton);
        profileButton = (ImageButton) findViewById(R.id.profileButton);

        planButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goPlan();
                planButton.setBackgroundResource(R.drawable.ic_plan_bar_selected);
                walletButton.setBackgroundResource(R.drawable.ic_wallet_bar);
                reportButton.setBackgroundResource(R.drawable.ic_report_bar);
                profileButton.setBackgroundResource(R.drawable.ic_profile_bar);
            }
        });

        walletButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goWallet();
                planButton.setBackgroundResource(R.drawable.ic_plan_bar);
                walletButton.setBackgroundResource(R.drawable.ic_wallet_bar_selected);
                reportButton.setBackgroundResource(R.drawable.ic_report_bar);
                profileButton.setBackgroundResource(R.drawable.ic_profile_bar);
            }
        });

        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planButton.setBackgroundResource(R.drawable.ic_plan_bar);
                walletButton.setBackgroundResource(R.drawable.ic_wallet_bar);
                reportButton.setBackgroundResource(R.drawable.ic_report_bar_selected);
                profileButton.setBackgroundResource(R.drawable.ic_profile_bar);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goProfile();
                planButton.setBackgroundResource(R.drawable.ic_plan_bar);
                walletButton.setBackgroundResource(R.drawable.ic_wallet_bar);
                reportButton.setBackgroundResource(R.drawable.ic_report_bar);
                profileButton.setBackgroundResource(R.drawable.ic_profile_bar_selected);
            }
        });

    }

    public void goPlan(){
        Fragment fragment;
        fragment = new PlanInitialActivity();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void goWallet(){
        Fragment fragment;
        fragment = new WalletMain();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /*public void goReport(){
        Fragment fragment;
        fragment = new Profile();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }*/

    public void goProfile(){
        Fragment fragment;
        fragment = new Profile();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void getPlanInfo(int size, String[] time, String[] title, String[] memo, double[] budget){
        timeList = new String[size];

    }
}
