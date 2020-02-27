package com.example.example;


import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WalletMain extends AppCompatActivity {

    PlanFragment fragment1;
    WalletFragment fragment2;
    ReportFragment fragment3;
    MypageFragment fragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_main);

        fragment1 = new PlanFragment();
        fragment2 = new WalletFragment();
        fragment3 = new ReportFragment();
        fragment4 = new MypageFragment();


        /*
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        // 첫 화면 지정
        FragmentTransaction transaction = fragment2.beginTransaction();
        transaction.replace(R.id.frame_layout, WalletFragment).commit();
        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction transaction = fragment2.beginTransaction();


                switch (item.getItemId()) {
                    case R.id.navigation_menu1: {
                        transaction.replace(R.id.frame_layout, menu1Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_menu2: {
                        transaction.replace(R.id.frame_layout, menu2Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_menu3: {
                        transaction.replace(R.id.frame_layout, menu3Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_menu4: {
                        transaction.replace(R.id.frame_layout, menu4Fragment).commitAllowingStateLoss();
                        break;
                    }
                }

                return true;
            }



        });*/
    }
}