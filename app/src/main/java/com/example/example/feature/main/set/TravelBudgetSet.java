package com.example.example.feature.main.set;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.example.feature.plan.Navigation;
import com.example.example.R;
import com.example.example.feature.main.Main;

public class TravelBudgetSet extends AppCompatActivity {

    EditText Edittext_budget;
    Button btn_Next;
    String budget;
    int mainPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_budget_set);

        Edittext_budget = (EditText) findViewById(R.id.Edittext_budget);

        btn_Next = (Button) findViewById(R.id.btn_Next);
        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String budget = Edittext_budget.getText().toString();
                Intent intent2 = getIntent();
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
                int mainPosition = intent2.getIntExtra("mainPosition", 0);

                if(budget == null || budget.length() == 0){
                    Edittext_budget.setHintTextColor(getColor(R.color.coral_red));
                    //btn_Next.isClickable(false);
                }else{
                    Intent intent = new Intent(TravelBudgetSet.this, Navigation.class);

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
                    intent.putExtra("budget", Double.parseDouble(budget));
                    intent.putExtra("mainPosition", mainPosition);
                    //Toast.makeText(getApplicationContext(), title+"\n"+ startYear+" "+startMonth+" "+startDay+" "+startDoW+
                     //       "\n"+endYear+" "+endMonth+" "+endDay+" "+endDoW
                      //      +"\n"+budgetType+"\n"+budget, Toast.LENGTH_LONG).show();

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
        Intent intent = new Intent(TravelBudgetSet.this, Main.class);
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
