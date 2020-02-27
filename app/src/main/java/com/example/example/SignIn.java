package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class SignIn extends AppCompatActivity {

    Handler handler = new Handler();

    Button btn_signin;
    boolean signinState=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);


        btn_signin = (Button) findViewById(R.id.btn_signin);


        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!signinState){
                    signinState = true;
                    btn_signin.setBackgroundResource(R.drawable.button_background_full);
                    btn_signin.setTextColor(getResources().getColor(R.color.white, getTheme()));
                }

                else {
                    signinState = false;
                    btn_signin.setBackgroundResource(R.drawable.border);
                    btn_signin.setTextColor(getResources().getColor(R.color.blue, getTheme()));
                }
            }
        });


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
