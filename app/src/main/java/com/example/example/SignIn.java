package com.example.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.regex.Pattern;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;



public class SignIn extends AppCompatActivity {

    EditText Edittext_email, Edittext_pw;
    Button btn_register;

    String email;
    String pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);

        Edittext_email = (EditText) findViewById(R.id.Edittext_email);
        Edittext_pw = (EditText) findViewById(R.id.Edittext_pw);


        //이메일형식체크
        //if(!android.util.Patterns.EMAIL_ADDRESS.matcher(Edittext_email).matches())
        //{
        //    Toast.makeText(this,"Please enter your email",Toast.LENGTH_SHORT).show();
        //    dialog.dismiss();
        //    return;
        //}

        //비밀번호 유효성
        //if(!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", Edittext_pw))
        //{
        //    Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
        //    dialog.dismiss();
        //    return;
        //}


        //btn_register = (Button) findViewById(R.id.btn_register);
        //btn_register.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        if(email == null){
        //           selectTitleText.setTextColor(getColor(R.color.coral_red));
        //        }

        //        else
        //            returnToBack();
        //    }
        //});
    }
    //Main Activity
    public void onClick(View view1){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //버튼 눌렀을 때 색 변화 만들기.

    }

    //register
    public void onButtonClick(View view2){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }
}
