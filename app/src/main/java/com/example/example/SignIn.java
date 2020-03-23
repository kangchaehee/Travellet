package com.example.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
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

    Button btn_register, btn_signin;

        //이메일과 비밀번호
    private EditText Edittext_email;
    private EditText Edittext_pw;

    String id, password;

    View underbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

            //스플래쉬
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);

            // 이메일, 패스워드
        Edittext_email = (EditText) findViewById(R.id.Edittext_email);
        Edittext_pw = (EditText) findViewById(R.id.Edittext_pw);

        String email = Edittext_email.getText().toString();
        String password = Edittext_pw.getText().toString();

            // 아이디 이메일 양식인지 확인
        Edittext_email.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    Pattern p = Pattern.compile("^[a-zA-X0-9]@[a-zA-Z0-9].[a-zA-Z0-9]");
                    Matcher m = p.matcher((Edittext_email).getText().toString());

                    if ( !m.matches()){

                        Toast.makeText(SignIn.this,"Please enter in email format.",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btn_signin = (Button) findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Edittext_email.getText().toString();
                String password = Edittext_pw.getText().toString();
                if(email.length() == 0 || password.length() == 0){
                    Edittext_email.setHintTextColor(getColor(R.color.coral_red));
                    Edittext_pw.setHintTextColor(getColor(R.color.coral_red));
                }else{
                    Intent intent = new Intent(SignIn.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }

            }
        });

    }
    /*
        //sign in 버튼. 누르면 Main Activity로
    public void onClick(View view1) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
    }
     */

        //register 버튼. 누르면 register로
    public void onButtonClick(View view2){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }
}
