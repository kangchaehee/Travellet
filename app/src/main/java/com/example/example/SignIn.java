package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);

    }

    //Main Activity
    public void onClick(View view1){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //버튼 눌렀을 때 색 변화 만들기.

    }

    //register
    public void onButtonClick(View view2){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }
}
