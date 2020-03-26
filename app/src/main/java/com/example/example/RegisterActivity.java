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
import android.widget.EditText;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {

    Button btn_female, btn_male, btn_register;
    boolean femaleState=false, maleState=false;

    private Spinner spinner;
    private Spinner spinner2;

    private final int GET_GALLERY_IMAGE = 200;
    private ImageView selfie;

    EditText Edittext_email, Edittext_pw, Edittext_name;
    String email, password, name;

    //private static final int PICK_FROM_CAMERA;
    //private static final int PICK_FROM_ALBUM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Edittext_email = (EditText) findViewById(R.id.Edittext_email);
        Edittext_pw = (EditText) findViewById(R.id.Edittext_pw);
        Edittext_name = (EditText) findViewById(R.id.Edittext_name);

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

            // 아이디 이메일 양식인지 확인
        Edittext_email = (EditText) findViewById(R.id.Edittext_email);

        Edittext_email.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    Pattern p = Pattern.compile("^[a-zA-X0-9]@[a-zA-Z0-9].[a-zA-Z0-9]");
                    Matcher m = p.matcher((Edittext_email).getText().toString());

                    if ( !m.matches()){

                        Toast.makeText(RegisterActivity.this,"Please enter in email format.",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Edittext_email.getText().toString();
                String password = Edittext_pw.getText().toString();
                String name = Edittext_name.getText().toString();
                if(email.length() == 0 || password.length() == 0 | name.length() == 0){
                    Edittext_email.setHintTextColor(getColor(R.color.coral_red));
                    Edittext_pw.setHintTextColor(getColor(R.color.coral_red));
                    Edittext_name.setHintTextColor(getColor(R.color.coral_red));

                }else{

                    Intent intent = new Intent(RegisterActivity.this, SignIn.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }

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

            ((TextView)view).setTextColor(getResources().getColor(R.color.soft_black));

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

    /*
        //signin 으로
    public void onClick(View view){

        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    } */

        //signin 으로
    public void onButtonClick(View v){
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);

    }

}
