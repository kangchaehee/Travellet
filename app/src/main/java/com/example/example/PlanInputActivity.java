package com.example.example;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class PlanInputActivity extends AppCompatActivity {

    int hour, min, category=-1;
    String place;
    String memo;

    TimePicker timePicker;

    Button add;
    ImageButton placeSelect;
    TextView selectTitleText;
    EditText editMemo;

    ImageButton lodging, food, shopping, tourism, etc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_plan_input);

        timePicker = (TimePicker) findViewById(R.id.select_time);

        placeSelect = (ImageButton) findViewById(R.id.SelectPlaceButton);
        placeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectPlaceActivity.class);
                startActivityForResult(intent, 103);
            }
        });

        selectTitleText = (TextView) findViewById(R.id.select_place);

        editMemo = (EditText) findViewById(R.id.memo);

        lodging = (ImageButton) findViewById(R.id.lodging);
        food = (ImageButton) findViewById(R.id.food);
        shopping = (ImageButton) findViewById(R.id.shopping);
        tourism = (ImageButton) findViewById(R.id.tourism);
        etc = (ImageButton) findViewById(R.id.etc);


        lodging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(category ==-1 || category != 1){
                    category = 1;
                    lodging.setBackgroundResource(R.drawable.ic_lodging_selected);
                    food.setBackgroundResource(R.drawable.ic_food);
                    shopping.setBackgroundResource(R.drawable.ic_shopping);
                    tourism.setBackgroundResource(R.drawable.ic_tourism);
                    etc.setBackgroundResource(R.drawable.ic_etc);
                }
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(category ==-1 || category != 2){
                    category = 2;
                    lodging.setBackgroundResource(R.drawable.ic_lodging);
                    food.setBackgroundResource(R.drawable.ic_food_selected);
                    shopping.setBackgroundResource(R.drawable.ic_shopping);
                    tourism.setBackgroundResource(R.drawable.ic_tourism);
                    etc.setBackgroundResource(R.drawable.ic_etc);
                }
            }
        });

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(category ==-1 || category != 3){
                    category = 3;
                    lodging.setBackgroundResource(R.drawable.ic_lodging);
                    food.setBackgroundResource(R.drawable.ic_food);
                    shopping.setBackgroundResource(R.drawable.ic_shopping_selected);
                    tourism.setBackgroundResource(R.drawable.ic_tourism);
                    etc.setBackgroundResource(R.drawable.ic_etc);
                }
            }
        });

        tourism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(category ==-1 || category != 4){
                    category = 4;
                    lodging.setBackgroundResource(R.drawable.ic_lodging);
                    food.setBackgroundResource(R.drawable.ic_food);
                    shopping.setBackgroundResource(R.drawable.ic_shopping);
                    tourism.setBackgroundResource(R.drawable.ic_tourism_selected);
                    etc.setBackgroundResource(R.drawable.ic_etc);
                }
            }
        });

        etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(category ==-1 || category != 5){
                    category = 5;
                    lodging.setBackgroundResource(R.drawable.ic_lodging);
                    food.setBackgroundResource(R.drawable.ic_food);
                    shopping.setBackgroundResource(R.drawable.ic_shopping);
                    tourism.setBackgroundResource(R.drawable.ic_tourism);
                    etc.setBackgroundResource(R.drawable.ic_etc_selected);
                }
            }
        });

        add = (Button) findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToBack();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 103){
            if(intent != null){
                place = intent.getStringExtra("selectTitle");
                selectTitleText.setText(place);
            }
        }
    }

    public void returnToBack(){
        if(android.os.Build.VERSION.SDK_INT>= android.os.Build.VERSION_CODES.M){
            hour = timePicker.getHour();
            min = timePicker.getMinute();
        } else{
            hour = timePicker.getCurrentHour();
            min = timePicker.getCurrentMinute();
        }
        memo = editMemo.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("title", place);
        intent.putExtra("memo", memo);
        intent.putExtra("hour", hour);
        intent.putExtra("min", min);

        setResult(RESULT_OK, intent);
        finish();
    }
}
