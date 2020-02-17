package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);

    }


    //sign in
    public void onClick(View view1){


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //register
    public void onButtonClick(View view2){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }
}
