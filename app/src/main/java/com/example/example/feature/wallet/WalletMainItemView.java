package com.example.example.feature.wallet;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.example.R;

public class WalletMainItemView extends LinearLayout {
    TextView w_time, w_title, w_memo, w_cost, w_budget;


    public WalletMainItemView(Context context) {
        super(context);
        init(context);
    }

    public WalletMainItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_wallet_main_item, this, true);

        w_time = (TextView) findViewById(R.id.walletTime);
        w_title = (TextView) findViewById(R.id.walletName);
        w_memo = (TextView) findViewById(R.id.walletMemo);
        w_cost = (TextView) findViewById(R.id.costText);
        w_budget = (TextView) findViewById(R.id.budgetText);

    }

    public void setW_time(String time){
        w_time.setText(time);
    }

    public void setW_title(String title){
        w_title.setText(title);
    }

    public void setW_memo(String memo){
        w_memo.setText(memo);
    }

    public void setW_cost(double cost){
        w_cost.setText(String.valueOf(cost));
    }

    public void setW_budget(double budget){
        w_budget.setText(String.valueOf(budget));
    }

}
