package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

public class BudgetInput extends AppCompatActivity {

    int category=1;
    String memo="null";

    EditText editMemo;

    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;
    private ImageButton buttonC;

    Button btn_add;
    ImageButton back;
    private EditText edit1;
    private int a;
    private int where=0;

    ImageButton lodging, food, shopping, tourism, transport, etc;
    boolean lodgingState=false, foodState=false, shoppingState=false, tourismState=false, transportState=false, etcState=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_budget_input);

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


        back = (ImageButton) findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lodging = (ImageButton) findViewById(R.id.lodging);
        food = (ImageButton) findViewById(R.id.food);
        shopping = (ImageButton) findViewById(R.id.shopping);
        tourism = (ImageButton) findViewById(R.id.tourism);
        transport = (ImageButton) findViewById(R.id.transport);
        etc = (ImageButton) findViewById(R.id.etc);

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

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit1 == null){
                    edit1.setTextColor(getColor(R.color.coral_red));
                }

                else
                    returnToBack();
            }
        });

        View.OnClickListener cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == button1) {
                    edit1.setText(edit1.getText().toString() + 1);
                } else if (v == button2) {
                    edit1.setText(edit1.getText().toString() + 2);
                } else if (v == button3) {
                    edit1.setText(edit1.getText().toString() + 3);
                } else if (v == button4) {
                    edit1.setText(edit1.getText().toString() + 4);
                } else if (v == button5) {
                    edit1.setText(edit1.getText().toString() + 5);
                } else if (v == button6) {
                    edit1.setText(edit1.getText().toString() + 6);
                } else if (v == button7) {
                    edit1.setText(edit1.getText().toString() + 7);
                } else if (v == button8) {
                    edit1.setText(edit1.getText().toString() + 8);
                } else if (v == button9) {
                    edit1.setText(edit1.getText().toString() + 9);
                } else if (v == button0) {
                    edit1.setText(edit1.getText().toString() + 0);
                } else if (v == buttonC) {
                    edit1.setText("");
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


    public void returnToBack(){

        memo = editMemo.getText().toString();
        if(memo == null){
            Log.d("null", "null");
        }
        else
            Log.d("memo", memo);
        if(memo.length()<1){
            switch (category){
                case 1:
                    memo = "Lodging";
                    break;

                case 2:
                    memo = "Food";
                    break;

                case 3:
                    memo = "Shopping";
                    break;

                case 4:
                    memo = "Tourism";
                    break;

                case 5:
                    memo = "etc";
                    break;

                default:
                    memo = "null";
                    break;
            }
        }
        Intent intent = new Intent();
        intent.putExtra("memo", memo);
        intent.putExtra("type", category);

        setResult(RESULT_OK, intent);
        finish();
    }


}

