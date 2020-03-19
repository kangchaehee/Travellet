package com.example.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

import android.content.Intent;
import android.graphics.Color;
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

    Button btn_register, btn_signin;

        //이메일과 비밀번호
    private EditText Edittext_email;
    private EditText Edittext_pw;
    //private String email = "";
    //private String password = "";

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

        String id = Edittext_email.getText().toString();
        final String password = Edittext_pw.getText().toString();

            // 아이디 이메일 양식인지 확인
        Edittext_email.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    Pattern p = Pattern.compile("^[a-zA-X0-9]@[a-zA-Z0-9].[a-zA-Z0-9]");
                    Matcher m = p.matcher((Edittext_email).getText().toString());

                    if ( !m.matches()){
                        Toast.makeText(SignIn.this, "Write on Email form", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Button.OnClickListener onClick = new Button.OnClickListener(){
            public void onClick(View v){

                //EditText내용을 가져오기
                String id = Edittext_email.getText().toString();
                String password= Edittext_pw.getText().toString();

                    //스페이스바만 눌러서 넘기는 경우도 안된다고 할때
                id = id.trim();
                password = password.trim();

                    //null값이 넘어올때의 처리
                if((id.getBytes().length <= 0) || (password.getBytes().length <=0)){

                    Toast.makeText(SignIn.this, "Fill out the form.", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

        //sign in 버튼. 누르면 Main Activity로
    public void onClick(View view1) {

        String id = Edittext_email.getText().toString();
        String password= Edittext_pw.getText().toString();

        if ((id.getBytes().length <= 0)||(password.getBytes().length <= 0)){

            btn_signin.setEnabled(false);

        } else{

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            //버튼 눌렀을 때 색 변화 만들기.
        }
    }


        //register 버튼. 누르면 register로
    public void onButtonClick(View view2){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }
}
