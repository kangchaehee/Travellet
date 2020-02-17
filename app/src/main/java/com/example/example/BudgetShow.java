package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BudgetShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_show);
    }
    public void onClick(View view){

        Intent intent = new Intent(BudgetShow.this, BudgetSet.class);
        startActivity(intent);
    }
}
