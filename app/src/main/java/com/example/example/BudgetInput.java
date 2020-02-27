package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

public class BudgetInput extends AppCompatActivity {

    private Button button1, button2, button3, buttonP, button4, button5, button6, buttonX, button7, button8, button9, buttonD, button0, buttonPOINT, buttonC, buttonM;

    private EditText edit;
    private int a;
    private int where=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_input);

        Toast.makeText(getApplicationContext(),"Calculator",Toast.LENGTH_SHORT).show();

        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        buttonP=(Button)findViewById(R.id.buttonP);
        button4=(Button)findViewById(R.id.button4);
        button5=(Button)findViewById(R.id.button5);
        button6=(Button)findViewById(R.id.button6);
        buttonX=(Button)findViewById(R.id.buttonX);
        button7=(Button)findViewById(R.id.button7);
        button8=(Button)findViewById(R.id.button8);
        button9=(Button)findViewById(R.id.button9);
        buttonD=(Button)findViewById(R.id.buttonD);
        button0=(Button)findViewById(R.id.button0);
        buttonPOINT=(Button)findViewById(R.id.buttonPOINT);
        buttonC=(Button)findViewById(R.id.buttonC);
        buttonM=(Button)findViewById(R.id.buttonM);
        edit=(EditText)findViewById(R.id.edit1);

        View.OnClickListener cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==button1){
                    edit.setText(edit.getText().toString()+1);
                }
                else if(v==button2){
                    edit.setText(edit.getText().toString()+2);
                }
                else if(v==button3){
                    edit.setText(edit.getText().toString()+3);
                }
                else if(v==buttonP){
                    a=Integer.valueOf(edit.getText().toString().trim());
                    edit.setText("");
                    where=1;
                }
                else if(v==button4){
                    edit.setText(edit.getText().toString()+4);
                }
                else if(v==button5){
                    edit.setText(edit.getText().toString()+5);
                }
                else if(v==button6){
                    edit.setText(edit.getText().toString()+6);
                }
                else if(v==buttonX){
                    a=Integer.valueOf(edit.getText().toString().trim());
                    edit.setText("");
                    where=2;
                }
                else if(v==button7){
                    edit.setText(edit.getText().toString()+7);
                }
                else if(v==button8){
                    edit.setText(edit.getText().toString()+8);
                }
                else if(v==button9){
                    edit.setText(edit.getText().toString()+9);
                }
                else if(v==buttonD){
                    a=Integer.valueOf(edit.getText().toString().trim());
                    edit.setText("");
                    where=3;
                }
                else if(v==button0){
                    edit.setText(edit.getText().toString()+0);
                }
                //이거 지금 기능은 = 인데, . 소수점으로 바꿔야함.
                else if(v==buttonPOINT) {
                    if (where == 1) {
                        a = a + Integer.valueOf(edit.getText().toString().trim());
                        edit.setText(Integer.toString(a));
                    }
                    else if(where==2){
                        a = a * Integer.valueOf(edit.getText().toString().trim());
                        edit.setText(Integer.toString(a));
                    }
                    else if(where==3){
                        a = a / Integer.valueOf(edit.getText().toString().trim());
                        edit.setText(Integer.toString(a));
                    }
                    else if(where==4){
                        a = a - Integer.valueOf(edit.getText().toString().trim());
                        edit.setText(Integer.toString(a));
                    }
                }
                else if(v==buttonC){
                    edit.setText("");
                }
                else if(v==buttonM){
                    a=Integer.valueOf(edit.getText().toString().trim());
                    edit.setText("");
                    where=4;
                }
            }

        };
        button1.setOnClickListener(cl);
        button2.setOnClickListener(cl);
        button3.setOnClickListener(cl);
        buttonP.setOnClickListener(cl);
        button4.setOnClickListener(cl);
        button5.setOnClickListener(cl);
        button6.setOnClickListener(cl);
        buttonX.setOnClickListener(cl);
        button7.setOnClickListener(cl);
        button8.setOnClickListener(cl);
        button9.setOnClickListener(cl);
        buttonD.setOnClickListener(cl);
        button0.setOnClickListener(cl);
        buttonPOINT.setOnClickListener(cl);
        buttonC.setOnClickListener(cl);
        buttonM.setOnClickListener(cl);
    }

    //sign in
    public void onClick(View view1){

        Intent intent = new Intent(this, WalletList.class);
        startActivity(intent);
    }

}

