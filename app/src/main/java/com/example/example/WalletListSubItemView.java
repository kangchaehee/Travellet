package com.example.example;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;

public class WalletListSubItemView extends LinearLayout {
    TextView category, category_memo, money;
    ImageView lodging, payment;

    public WalletListSubItemView(Context context) {
        super(context);
        init(context);
    }

    public WalletListSubItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_wallet_list_sub_item, this, true);

        category = (TextView) findViewById(R.id.category);
        category_memo = (TextView) findViewById(R.id.category_memo);
        money = (TextView) findViewById(R.id.money);
        lodging = (ImageView) findViewById(R.id.lodging);
        payment= (ImageView) findViewById(R.id.payment);

    }

    public void setCategory(String category) {this.category.setText(category);}

    public void setCategory_memo(String category_memo) {this.category_memo.setText(category_memo);}

    public void setMoney(int money) {this.money.setText(money);}

    public void setLodging(int lodging) {this.lodging.setBackgroundResource(lodging);}

    public void setPayment(int payment) {this.payment.setBackgroundResource(payment);}

}

