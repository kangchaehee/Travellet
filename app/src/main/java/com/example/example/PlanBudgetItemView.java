package com.example.example;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class PlanBudgetItemView extends LinearLayout {
    ImageView category;
    TextView type, budget;

    public PlanBudgetItemView(Context context) {
        super(context);
        init(context);

    }

    public PlanBudgetItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_plan_budget_item, this, true);

        category = findViewById(R.id.img);
        type = findViewById(R.id.txt);
        budget= findViewById(R.id.budget);
    }

    public void setCategory(int category){
        this.category.setBackgroundResource(category);
    }

    public void setType(String type){
        this.type.setText(type);
    }

    public void setBudget(double budget){
        this.budget.setText(String.valueOf(budget));
    }
}
