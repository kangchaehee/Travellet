package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void onClicked(View view){
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);

    }

    //main empty 로.
    public void onClick(View view){

        Intent intent = new Intent(this, MainEmpty.class);
        startActivity(intent);
    }


    //signin 으로
    public void onButtonClick(View v){
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);

    }


}
