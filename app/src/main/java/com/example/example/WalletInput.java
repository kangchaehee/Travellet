package com.example.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.EntityDeletionOrUpdateAdapter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.example.network.InfoID;

public class WalletInput extends AppCompatActivity {

    ImageButton card, cash;
    ImageButton lodging, food, shopping, tourism, transport, etc;

    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;
    private ImageButton buttonC;
    private EditText edit1;
    TextView money;
    float exchangeMoney;

    int category = 1;
    int payment=1;

    boolean cashState=false, cardState=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_input);

        lodging = (ImageButton) findViewById(R.id.lodging);
        food = (ImageButton) findViewById(R.id.food);
        shopping = (ImageButton) findViewById(R.id.shopping);
        tourism = (ImageButton) findViewById(R.id.tourism);
        transport = (ImageButton) findViewById(R.id.transport);
        etc = (ImageButton) findViewById(R.id.etc);

        card = (ImageButton) findViewById(R.id.card);
        cash = (ImageButton) findViewById(R.id.cash);

        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);
        button5=(Button)findViewById(R.id.button5);
        button6=(Button)findViewById(R.id.button6);
        button7=(Button)findViewById(R.id.button7);
        button8=(Button)findViewById(R.id.button8);
        button9=(Button)findViewById(R.id.button9);
        button0=(Button)findViewById(R.id.button0);
        buttonC=(ImageButton)findViewById(R.id.buttonC);

        edit1 = (EditText) findViewById(R.id.edit1);
        money = (TextView)findViewById(R.id.money);



            //카드랑 현금 중에 선택
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cashState){
                    payment = 1;
                    cashState = true;
                    cash.setBackgroundResource(R.drawable.ic_cash_selected);

                    cardState = false;
                    card.setBackgroundResource(R.drawable.ic_card);
                }

                else {
                    cashState = false;
                    cash.setBackgroundResource(R.drawable.ic_cash);

                }
            }
        });

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cardState){
                    payment = 2;
                    cardState = true;
                    card.setBackgroundResource(R.drawable.ic_card_selected);

                    cashState = false;
                    cash.setBackgroundResource(R.drawable.ic_cash);
                }

                else {
                    cardState = false;
                    card.setBackgroundResource(R.drawable.ic_card);
                }
            }
        });

        //카테고리 선택
        lodging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 1;
                lodging.setBackgroundResource(R.drawable.ic_lodging_selected);
                food.setBackgroundResource(R.drawable.ic_food);
                shopping.setBackgroundResource(R.drawable.ic_shopping);
                tourism.setBackgroundResource(R.drawable.ic_tourism);
                transport.setBackgroundResource(R.drawable.ic_transport);
                etc.setBackgroundResource(R.drawable.ic_etc);

            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 2;
                lodging.setBackgroundResource(R.drawable.ic_lodging);
                food.setBackgroundResource(R.drawable.ic_food_selected);
                shopping.setBackgroundResource(R.drawable.ic_shopping);
                tourism.setBackgroundResource(R.drawable.ic_tourism);
                transport.setBackgroundResource(R.drawable.ic_transport);
                etc.setBackgroundResource(R.drawable.ic_etc);
            }
        });

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 3;
                lodging.setBackgroundResource(R.drawable.ic_lodging);
                food.setBackgroundResource(R.drawable.ic_food);
                shopping.setBackgroundResource(R.drawable.ic_shopping_selected);
                tourism.setBackgroundResource(R.drawable.ic_tourism);
                transport.setBackgroundResource(R.drawable.ic_transport);
                etc.setBackgroundResource(R.drawable.ic_etc);

            }
        });

        tourism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 4;
                lodging.setBackgroundResource(R.drawable.ic_lodging);
                food.setBackgroundResource(R.drawable.ic_food);
                shopping.setBackgroundResource(R.drawable.ic_shopping);
                tourism.setBackgroundResource(R.drawable.ic_tourism_selected);
                transport.setBackgroundResource(R.drawable.ic_transport);
                etc.setBackgroundResource(R.drawable.ic_etc);

            }
        });

        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 5;
                lodging.setBackgroundResource(R.drawable.ic_lodging);
                food.setBackgroundResource(R.drawable.ic_food);
                shopping.setBackgroundResource(R.drawable.ic_shopping);
                tourism.setBackgroundResource(R.drawable.ic_tourism);
                transport.setBackgroundResource(R.drawable.ic_transport_selected);
                etc.setBackgroundResource(R.drawable.ic_etc);
            }
        });

        etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 6;
                lodging.setBackgroundResource(R.drawable.ic_lodging);
                food.setBackgroundResource(R.drawable.ic_food);
                shopping.setBackgroundResource(R.drawable.ic_shopping);
                tourism.setBackgroundResource(R.drawable.ic_tourism);
                transport.setBackgroundResource(R.drawable.ic_transport);
                etc.setBackgroundResource(R.drawable.ic_etc_selected);
            }
        });

        View.OnClickListener cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == button1) {
                    edit1.setText(edit1.getText().toString() + 1);
                    setMoney();
                } else if (v == button2) {
                    edit1.setText(edit1.getText().toString() + 2);
                    setMoney();
                } else if (v == button3) {
                    edit1.setText(edit1.getText().toString() + 3);
                    setMoney();
                } else if (v == button4) {
                    edit1.setText(edit1.getText().toString() + 4);
                    setMoney();
                } else if (v == button5) {
                    edit1.setText(edit1.getText().toString() + 5);
                    setMoney();
                } else if (v == button6) {
                    edit1.setText(edit1.getText().toString() + 6);
                    setMoney();
                } else if (v == button7) {
                    edit1.setText(edit1.getText().toString() + 7);
                    setMoney();
                } else if (v == button8) {
                    edit1.setText(edit1.getText().toString() + 8);
                    setMoney();
                } else if (v == button9) {
                    edit1.setText(edit1.getText().toString() + 9);
                    setMoney();
                } else if (v == button0) {
                    edit1.setText(edit1.getText().toString() + 0);
                    setMoney();
                } else if (v == buttonC) {
                    edit1.setText("");
                    money.setText("");
                }
            }
        };

        button1.setOnClickListener(cl);
        button2.setOnClickListener(cl);
        button3.setOnClickListener(cl);
        button4.setOnClickListener(cl);
        button5.setOnClickListener(cl);
        button6.setOnClickListener(cl);
        button7.setOnClickListener(cl);
        button8.setOnClickListener(cl);
        button9.setOnClickListener(cl);
        button0.setOnClickListener(cl);
        buttonC.setOnClickListener(cl);

    }

    // 환율
    public void setMoney(){
        exchangeMoney = InfoID.exchange/InfoID.KRW*(Integer.parseInt(edit1.getText().toString()));
        money.setText(String.valueOf(Math.round(exchangeMoney*100)/100.0));
    }

    //sign in
    public void onClick(View view1){
        returnToBack();
    }

    public void returnToBack(){
        String cost = edit1.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("type", category);
        intent.putExtra("cost", cost);
        intent.putExtra("payment", payment);
        setResult(RESULT_OK, intent);
        finish();
    }

}

