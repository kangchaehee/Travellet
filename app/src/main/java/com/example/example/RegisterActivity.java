package com.example.example;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {

    Button btn_female, btn_male, btn_register;
    boolean femaleState=false, maleState=false, registerState=false;

    private Spinner spinner;
    private Spinner spinner2;

    private final int GET_GALLERY_IMAGE = 200;
    private ImageView selfie;


    //private static final int PICK_FROM_CAMERA;
    //private static final int PICK_FROM_ALBUM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Spinner spinner = findViewById(R.id.country);
        Spinner spinner2 = findViewById(R.id.age);

            //array_age
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.array_age, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new MyOnItemSelectedListener());
            //이건 화살표 색깔
        spinner2.getBackground().setColorFilter(Color.parseColor("#c8cbd3"), PorterDuff.Mode.SRC_ATOP);


            //array_country
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.array_country, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
            //이건 화살표 색깔
        spinner.getBackground().setColorFilter(Color.parseColor("#c8cbd3"), PorterDuff.Mode.SRC_ATOP);


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

        selfie = (ImageView)findViewById(R.id.selfie);
        selfie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            selfie.setImageURI(selectedImageUri);
        }
    }


    public class MyOnItemSelectedListener implements OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            ((TextView)view).setTextColor(getResources().getColor(R.color.soft_grey));

            ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.soft_grey));
            ((TextView) parent.getChildAt(0)).setTextSize(14);
            //((TextView) parent.getChildAt(0)).setFontFeatureSettings(getResources().getFont(R.font.roboto_regular));

            //Toast.makeText(parent.getContext(),
            //        "Your country is "+parent.getItemAtPosition(position),
            //        Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // Do nothing
        }
    }


    /*
    public class MyOnItemSelectedListener2 implements OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            ((TextView)view).setTextColor(getResources().getColor(R.color.soft_grey));

            ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.soft_grey));
            ((TextView) parent.getChildAt(0)).setTextSize(14);
            //((TextView) parent.getChildAt(0)).setFontFeatureSettings(getResources().getFont(R.font.roboto_regular));

            //Toast.makeText(parent.getContext(),
            //        "Your country is "+parent.getItemAtPosition(position),
            //        Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // Do nothing
        }
    }
     */


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
