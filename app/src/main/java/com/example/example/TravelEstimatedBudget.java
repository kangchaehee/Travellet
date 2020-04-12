package com.example.example;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class TravelEstimatedBudget extends AppCompatActivity {
    int lodging, food, tourism, shopping, transport, etc;
    double total, total2, lodgingB, foodB, leisureB, shoppingB, transportB, etcB;

    TextView lodgingPer, foodPer, leisurePer, shoppingPer, transportPer, etcPer;
    TextView lodgingBudget, foodBudget, leisureBudget, shoppingBudget, transportBudget, etcBudget, totalBudget;
    SeekBar lodgingBar, foodBar, leisureBar, shoppingBar, transportBar, etcBar;
    LinearLayout lodgingLin, foodLin, tourLin, shopLin, etcLin;

    Intent intent2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_travel_estimated_budget);


        lodgingPer = findViewById(R.id.lodgingPer);
        foodPer = findViewById(R.id.foodPer);
        leisurePer = findViewById(R.id.leisurePer);
        shoppingPer = findViewById(R.id.shoppingPer);
        //transportPer = findViewById(R.id.transportPer);
        etcPer = findViewById(R.id.etcPer);

        lodgingBar = findViewById(R.id.progress1);
        foodBar = findViewById(R.id.progress2);
        leisureBar = findViewById(R.id.progress3);
        shoppingBar = findViewById(R.id.progress4);
        //transportBar = findViewById(R.id.progress5);
        etcBar = findViewById(R.id.progress6);

        lodgingBudget = findViewById(R.id.lodgingBudget);
        foodBudget = findViewById(R.id.foodBudget);
        leisureBudget = findViewById(R.id.leisureBudget);
        shoppingBudget = findViewById(R.id.shoppingBudget);
        //transportBudget = findViewById(R.id.transportBudget);
        etcBudget = findViewById(R.id.etcBudget);
        totalBudget = findViewById(R.id.money);

        lodgingLin = findViewById(R.id.lodging_lin);
        foodLin = findViewById(R.id.food_lin);
        tourLin = findViewById(R.id.tourism_lin);
        shopLin = findViewById(R.id.shopping_lin);
        etcLin = findViewById(R.id.etc_lin);

        //토탈에서 남은 금액이랑 금액 설정 안한거 몇갠지
        Intent intent = getIntent();
        total = intent.getDoubleExtra("total", 0);
        total2 = total;
        totalBudget.setText(String.valueOf(total));

        lodging = intent.getIntExtra("lodging", 0);
        if(lodging == 0)
            //lodgingLin.setVisibility(View.GONE);
        food = intent.getIntExtra("food", 0);
        if(food == 0)
            foodLin.setVisibility(View.GONE);
        shopping = intent.getIntExtra("shopping", 0);
        if(shopping == 0)
            shopLin.setVisibility(View.GONE);
        tourism = intent.getIntExtra("tourism", 0);
        if(tourism == 0)
            tourLin.setVisibility(View.GONE);
        etc = intent.getIntExtra("etc", 0);
        if(etc == 0)
            etcLin.setVisibility(View.GONE);

        //카테고리별 예산 max=total
        lodgingBar.setMax((int)total/200);
        foodBar.setMax((int)total/200);
        leisureBar.setMax((int)total/200);
        shoppingBar.setMax((int)total/200);
        etcBar.setMax((int)total/200);

        //각 카테고리 예산 텍스트 조정
        lodgingBudget.setText("$ "+lodgingB);
        foodBudget.setText("$ "+foodB);
        leisureBudget.setText("$ "+leisureB);
        shoppingBudget.setText("$ "+shoppingB);
        //transportBudget.setText("$ "+transportB);
        etcBudget.setText("$ "+etcB);

        //카테고리 비율에 맞게 텍스트랑 프로그레스 바 조정
        lodgingBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //progress가 바뀐 선택 값
                total2 = total-foodB-shoppingB - leisureB - etc;
                totalBudget.setText(String.valueOf((int)total2-progress*200));
                int percent= (progress*200)*100/(int)total;
                lodgingPer.setText(String.valueOf((percent))+"%");
                lodgingBudget.setText("₩ "+String.valueOf(progress*200));
                lodgingB = progress*200;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //total2 = total2-lodgingB;
            }
        });

        foodBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //progress가 바뀐 선택 값
                total2 = total-lodgingB-shoppingB-leisureB - etc;
                totalBudget.setText(String.valueOf((int)total2-progress*200));
                int percent= (progress*200)*100/(int)total;
                foodPer.setText(String.valueOf(percent)+"%");
                foodBudget.setText("₩ "+String.valueOf(progress*200));
                foodB = progress*200;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //total2 = total2 - foodB;
            }
        });

        shoppingBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //progress가 바뀐 선택 값
                total2 = total-lodgingB-shoppingB-leisureB - etc;
                totalBudget.setText(String.valueOf((int)total2-progress*200));
                int percent= (progress*200)*100/(int)total;
                shoppingPer.setText(String.valueOf(percent)+"%");
                shoppingBudget.setText("₩ "+String.valueOf(progress*200));
                shoppingB = progress*200;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        leisureBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //progress가 바뀐 선택 값
                total2 = total-lodgingB-shoppingB-leisureB - etc;
                totalBudget.setText(String.valueOf((int)total2-progress*200));
                int percent= (progress*200)*100/(int)total;
                leisurePer.setText(String.valueOf(percent)+"%");
                leisureBudget.setText("₩ "+String.valueOf(progress*200));
                leisureB = progress*200;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        etcBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //progress가 바뀐 선택 값
                total2 = total-lodgingB-shoppingB-leisureB - etc;
                totalBudget.setText(String.valueOf((int)total2-progress*200));
                int percent= (progress*200)*100/(int)total;
                etcPer.setText(String.valueOf(percent)+"%");
                etcBudget.setText("₩ "+String.valueOf(progress*200));
                etcB = progress*200;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });



        //최종 금액, 타입별 예산
        intent2 = getIntent();
        /*total = intent2.getIntExtra("budget", 0);
        lodgingB = total*lodging/100;
        foodB = total*food/100;
        leisureB = total*leisure/100;
        shoppingB = total*shopping/100;
        //transportB = total*transport/100;*/
        //etcB = total*etc/100;



    }

    // travel budget set
    public void onButtonClick(View view1){
        finish();
    }

    // plan initial
    public void onClick(View view){
        /*int startYear = intent2.getIntExtra("startYear", 0);
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
        int prefType = intent2.getIntExtra("prefType", 0);*/

        Intent intent = new Intent(this, Navigation.class);
        /*intent.putExtra("startYear", startYear);
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
        //intent.putExtra("transportBudget", transportB);
        intent.putExtra("etcBudget", etcB);
        //Toast.makeText(getApplicationContext(), title+"\n"+ startYear+" "+startMonth+" "+startDay+" "+startDoW+
         //       "\n"+endYear+" "+endMonth+" "+endDay+" "+endDoW
          //      +"\n"+budgetType+"\n"+budget+"\n"+lodgingType+"\n"+prefType
           //     +"\n"+lodgingB+"\n"+foodB+"\n"+leisureB+"\n"+shoppingB+"\n"+transportB+"\n"+etcB, Toast.LENGTH_LONG).show();*/

        //
        //startActivity(intent);
        finish();
    }


}
