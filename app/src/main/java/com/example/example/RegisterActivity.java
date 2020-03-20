package com.example.example;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class RegisterActivity extends AppCompatActivity {


    Button btn_female, btn_male, btn_register;
    boolean femaleState=false, maleState=false, registerState=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       // Spinner spinner2 = findViewById(R.id.age);
        Spinner spinner = findViewById(R.id.country);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.array_country, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

        btn_female = (Button) findViewById(R.id.btn_female);
        btn_male = (Button) findViewById(R.id.btn_male);
        btn_register = (Button) findViewById(R.id.btn_register);


        btn_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!femaleState){
                    femaleState = true;
                    btn_female.setBackgroundResource(R.drawable.button_background_full);
                    btn_female.setTextColor(getResources().getColor(R.color.white, getTheme()));

                    maleState = false;
                    btn_male.setBackgroundResource(R.drawable.border_24r_blue);
                    btn_male.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

                else {
                    femaleState = false;
                    btn_female.setBackgroundResource(R.drawable.border_24r_blue);
                    btn_female.setTextColor(getResources().getColor(R.color.blue, getTheme()));
                }
            }
        });


        btn_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!maleState){
                    maleState = true;
                    btn_male.setBackgroundResource(R.drawable.button_background_full);
                    btn_male.setTextColor(getResources().getColor(R.color.white, getTheme()));

                    femaleState = false;
                    btn_female.setBackgroundResource(R.drawable.border_24r_blue);
                    btn_female.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

                else {
                    maleState = false;
                    btn_male.setBackgroundResource(R.drawable.border_24r_blue);
                    btn_male.setTextColor(getResources().getColor(R.color.blue, getTheme()));
                }
            }
        });

    }

    public class MyOnItemSelectedListener implements OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //Toast.makeText(parent.getContext(),
            //        "Your country is "+parent.getItemAtPosition(position),
            //        Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // Do nothing
        }
    }


    public void onClicked(View view){
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);

    }

    //signin 으로
    public void onClick(View view){

        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }


    //signin 으로
    public void onButtonClick(View v){
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);

    }


}
