package com.example.example.feature.wallet;

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
import com.example.example.R;

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

    public void setMoney(double money) {this.money.setText("₩ "+String.valueOf(money));}

    public void setCategory_ic(int category_ic) {
        switch (category_ic){
            case 1:
                this.category_ic.setBackgroundResource(R.drawable.ic_lodging_24px);
                break;
            case 2:
                this.category_ic.setBackgroundResource(R.drawable.ic_food_24px);
                break;
            case 3:
                this.category_ic.setBackgroundResource(R.drawable.ic_shopping_24px);
                break;
            case 4:
                this.category_ic.setBackgroundResource(R.drawable.ic_tourism_24px);
                break;
            case 5:
                this.category_ic.setBackgroundResource(R.drawable.ic_bus_24px);
                break;
            case 6:
                this.category_ic.setBackgroundResource(R.drawable.ic_etc_24px);
                break;

        }
        //this.category_ic.setBackgroundResource(category_ic);
    }

    public void setPayment(int payment) {
        if(payment == 1){
            this.payment.setBackgroundResource(R.drawable.ic_cash_24px);
        }
        else{
            this.payment.setBackgroundResource(R.drawable.ic_card_24px);
        }
    }

}

