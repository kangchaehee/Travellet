package com.example.example;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Set;

public class BudgetSet extends AppCompatActivity {

    EditText Edittext_budget;
    Button btn_Next;
    String budget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_set);

        Edittext_budget = (EditText) findViewById(R.id.Edittext_budget);

        btn_Next = (Button) findViewById(R.id.btn_Next);
        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String budget = Edittext_budget.getText().toString();
                if(budget == null || budget.length() == 0){
                    Edittext_budget.setHintTextColor(getColor(R.color.coral_red));
                    //btn_Next.isClickable(false);
                }else{
                    Intent intent = new Intent(BudgetSet.this, TravelLodgingSet.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 52){
            String budget = Edittext_budget.getText().toString();
            if(budget != null){
                Edittext_budget.setTextColor(getColor(R.color.soft_black));
                Edittext_budget.setText(budget);
            }
        }
    }

    //Budget show
    public void onButtonClick(View view1){
        Intent intent = new Intent(BudgetSet.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /*
    //Travel Lodging set
    public void onClick(View view){

        Intent intent = new Intent(BudgetSet.this, TravelLodgingSet.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
    */

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0, 0);
    }
}
