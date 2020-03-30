package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class TravelEstimatedBudget extends AppCompatActivity {
    float lodging, food, leisure, shopping, transport, etc;
    float total, lodgingB, foodB, leisureB, shoppingB, transportB, etcB;

    TextView lodgingPer, foodPer, leisurePer, shoppingPer, transportPer, etcPer;
    TextView lodgingBudget, foodBudget, leisureBudget, shoppingBudget, transportBudget, etcBudget;
    ProgressBar lodgingBar, foodBar, leisureBar, shoppingBar, transportBar, etcBar;

    Intent intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_estimated_budget);

        lodgingPer = findViewById(R.id.lodgingPer);
        foodPer = findViewById(R.id.foodPer);
        leisurePer = findViewById(R.id.leisurePer);
        shoppingPer = findViewById(R.id.shoppingPer);
        transportPer = findViewById(R.id.transportPer);
        etcPer = findViewById(R.id.etcPer);

        lodgingBar = findViewById(R.id.progress1);
        foodBar = findViewById(R.id.progress2);
        leisureBar = findViewById(R.id.progress3);
        shoppingBar = findViewById(R.id.progress4);
        transportBar = findViewById(R.id.progress5);
        etcBar = findViewById(R.id.progress6);

        lodgingBudget = findViewById(R.id.lodgingBudget);
        foodBudget = findViewById(R.id.foodBudget);
        leisureBudget = findViewById(R.id.leisureBudget);
        shoppingBudget = findViewById(R.id.shoppingBudget);
        transportBudget = findViewById(R.id.transportBudget);
        etcBudget = findViewById(R.id.etcBudget);


        //각 카테고리 비율 설정
        lodging = 40;
        food = 30;
        leisure = 11;
        shopping = 10;
        transport = 8;
        etc = 1;

        //카테고리 비율에 맞게 텍스트랑 프로그레스 바 조정
        lodgingPer.setText(lodging+"%");
        lodgingBar.setProgress((int)lodging);
        foodPer.setText(String.valueOf(food)+"%");
        foodBar.setProgress((int)food);
        leisurePer.setText(String.valueOf(leisure)+"%");
        leisureBar.setProgress((int)leisure);
        shoppingPer.setText(String.valueOf(shopping)+"%");
        shoppingBar.setProgress((int)shopping);
        transportPer.setText(String.valueOf(transport)+"%");
        transportBar.setProgress((int)transport);
        etcPer.setText(String.valueOf(etc)+"%");
        etcBar.setProgress((int)etc);

        //최종 금액, 타입별 예산
        intent2 = getIntent();
        total = intent2.getIntExtra("budget", 0);
        lodgingB = total*lodging/100;
        foodB = total*food/100;
        leisureB = total*leisure/100;
        shoppingB = total*shopping/100;
        transportB = total*transport/100;
        etcB = total*etc/100;

        //각 카테고리 예산 텍스트 조정
        lodgingBudget.setText("$ "+lodgingB);
        foodBudget.setText("$ "+foodB);
        leisureBudget.setText("$ "+leisureB);
        shoppingBudget.setText("$ "+shoppingB);
        transportBudget.setText("$ "+transportB);
        etcBudget.setText("$ "+etcB);
    }

    // travel budget set
    public void onButtonClick(View view1){
        Intent intent = new Intent(this, BudgetSet.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    // plan initial
    public void onClick(View view){
        int startYear = intent2.getIntExtra("startYear", 0);
        int startMonth = intent2.getIntExtra("startMonth", 0);
        int startDay = intent2.getIntExtra("startDay", 0);
        int startDoW = intent2.getIntExtra("startDoW", 0);
        int endYear = intent2.getIntExtra("endYear", 0);
        int endMonth = intent2.getIntExtra("endMonth", 0);
        int endDay = intent2.getIntExtra("endDay", 0);
        int endDoW = intent2.getIntExtra("endDoW", 0);
        String title = intent2.getStringExtra("travelTitle");
        int budgetType = intent2.getIntExtra("budgetType", 0);
        int budget = intent2.getIntExtra("budget", 0);
        int lodgingType = intent2.getIntExtra("lodgingType", 0);
        int prefType = intent2.getIntExtra("prefType", 0);

        Intent intent = new Intent(this, Navigation.class);
        intent.putExtra("startYear", startYear);
        intent.putExtra("startMonth", startMonth);
        intent.putExtra("startDay", startDay);
        intent.putExtra("startDoW", startDoW);
        intent.putExtra("endYear", endYear);
        intent.putExtra("endMonth", endMonth);
        intent.putExtra("endDay", endDay);
        intent.putExtra("endDoW", endDoW);
        intent.putExtra("travelTitle", title);
        intent.putExtra("budgetType", budgetType);
        intent.putExtra("budget", budget);
        intent.putExtra("lodgingType", lodgingType);
        intent.putExtra("prefType", prefType);
        intent.putExtra("lodgingBudget", lodgingB);
        intent.putExtra("foodBudget", foodB);
        intent.putExtra("leisureBudget", leisureB);
        intent.putExtra("shoppingBudget", shoppingB);
        intent.putExtra("transportBudget", transportB);
        intent.putExtra("etcBudget", etcB);
        Toast.makeText(getApplicationContext(), title+"\n"+ startYear+" "+startMonth+" "+startDay+" "+startDoW+
                "\n"+endYear+" "+endMonth+" "+endDay+" "+endDoW
                +"\n"+budgetType+"\n"+budget+"\n"+lodgingType+"\n"+prefType
                +"\n"+lodgingB+"\n"+foodB+"\n"+leisureB+"\n"+shoppingB+"\n"+transportB+"\n"+etcB, Toast.LENGTH_LONG).show();

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
