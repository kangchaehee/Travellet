package com.example.example;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class WalletCostItemView extends LinearLayout {
    ImageView category, payment;
    TextView type, budget;

    public WalletCostItemView(Context context) {
        super(context);
        init(context);

    }

    public WalletCostItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_cost_item, this, true);

        category = findViewById(R.id.img);
        payment = findViewById(R.id.img2);
        type = findViewById(R.id.txt);
        budget= findViewById(R.id.budget);
    }

    public void setCategory(int category){
        this.category.setBackgroundResource(category);
    }

    public void setPayment(int payment){
        this.payment.setBackgroundResource(payment);
    }

    public void setType(String type){
        this.type.setText(type);
    }

    public void setBudget(double budget){
        this.budget.setText(String.valueOf(budget));
    }
}