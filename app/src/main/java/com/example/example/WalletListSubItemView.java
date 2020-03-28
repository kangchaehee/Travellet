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
    TextView category, place, money;
    ImageView category_ic, payment;

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
        place = (TextView) findViewById(R.id.category_memo);
        money = (TextView) findViewById(R.id.money);
        category_ic = (ImageView) findViewById(R.id.lodging);
        payment= (ImageView) findViewById(R.id.payment);

    }

    public void setCategory(String category) {this.category.setText(category);}

    public void setPlace(String place) {this.place.setText(place);}

    public void setMoney(double money) {this.money.setText("$ "+String.valueOf(money));}

    public void setCategory_ic(int category_ic) {
        this.category_ic.setBackgroundResource(category_ic);
    }

    public void setPayment(int payment) {this.payment.setBackgroundResource(payment);}

}

