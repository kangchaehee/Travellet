package com.example.example.feature.sign;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.example.R;
import com.example.example.data.user.SignUpData;
import com.example.example.data.user.SignUpResponse;
import com.example.example.network.RetrofitClient;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    //private static final int PICK_FROM_CAMERA;
    //private static final int PICK_FROM_ALBUM;

    Button btn_female, btn_male, btn_register;
    boolean femaleState=false, maleState=false;

    private Spinner spinner;
    private Spinner spinner2;

    private ProgressBar ProgressView;

    private final int GET_GALLERY_IMAGE = 200;
    private ImageView selfie;

    EditText Edittext_email, Edittext_pw, Edittext_name;
    // $회원가입 입력 정보
    String email, password, name, sex, country;
    int age;

    // serviceApi 객체 변수 선언
//    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // $네트워크를 위한 serviceApi 객체 생성
//        service = RetrofitClient.getClient().create(ServiceApi.class);

        // $로딩을 위한 진행바
        ProgressView = (ProgressBar) findViewById(R.id.join_progress);

        // $에디트텍스트 선언
        Edittext_email = (EditText) findViewById(R.id.Edittext_email);
        Edittext_pw = (EditText) findViewById(R.id.Edittext_pw);
        Edittext_name = (EditText) findViewById(R.id.Edittext_name);

        // $스피너 선언
        spinner = findViewById(R.id.country);
        spinner2 = findViewById(R.id.age);


        // $국가 스피너
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.array_country, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView)view).setTextColor(getResources().getColor(R.color.soft_black));
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.soft_grey));
                ((TextView) parent.getChildAt(0)).setTextSize(14);
                country = parent.getItemAtPosition(position).toString();
                //Log.d("country", country);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
            // $아래 화살표 색 설정
        spinner.getBackground().setColorFilter(Color.parseColor("#c8cbd3"), PorterDuff.Mode.SRC_ATOP);

        // $나이 스피너
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.array_age, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView)view).setTextColor(getResources().getColor(R.color.soft_black));
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.soft_grey));
                ((TextView) parent.getChildAt(0)).setTextSize(14);
                age =  Integer.parseInt(parent.getItemAtPosition(position).toString());
                //Log.d("age", String.valueOf(age));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
            // $아래 화살표 색 설정
        spinner2.getBackground().setColorFilter(Color.parseColor("#c8cbd3"), PorterDuff.Mode.SRC_ATOP);


        // $여자 버튼 이벤트
        btn_female = (Button) findViewById(R.id.btn_female);
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
        // $남자 버튼 이벤트
        btn_male = (Button) findViewById(R.id.btn_male);
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

        // $프로필 이벤트
        selfie = (ImageView)findViewById(R.id.selfie);
        selfie.setClipToOutline(true);
        selfie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

        // $이메일 유효성 검사
        Edittext_email = (EditText) findViewById(R.id.Edittext_email);
        Edittext_email.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    Pattern p = Pattern.compile("^[a-zA-X0-9]@[a-zA-Z0-9].[a-zA-Z0-9]");
                    Matcher m = p.matcher((Edittext_email).getText().toString());
                    if ( !m.matches()){
                        Toast.makeText(Register.this,"Please enter in email format.",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        // $회원가입 버튼 이벤트
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // $이메일, 비밀번호, 이름 문자열로 변환
                email = Edittext_email.getText().toString();
                password = Edittext_pw.getText().toString();
                name = Edittext_name.getText().toString();
                // $성별 구분
                if(maleState)
                    sex = "Male";
                else
                    sex = "Female";

                // $회원가입 버튼 활성화/비활성화
                if(email.length() == 0 || password.length() == 0 | name.length() == 0){
                    Edittext_email.setHintTextColor(getColor(R.color.coral_red));
                    Edittext_pw.setHintTextColor(getColor(R.color.coral_red));
                    Edittext_name.setHintTextColor(getColor(R.color.coral_red));
                }else{
                    // $회원가입 통신 메소드 실행
                    startJoin(new SignUpData(name, email, password, sex, age, country));
                    showProgress(true);
                    // $로그인 페이지로 전환
//                    Intent intent = new Intent(RegisterActivity.this, SignIn.class);
//                    startActivity(intent);
//                    overridePendingTransition(0, 0);
                }
            }
        });
    }


    // $회원가입 통신 메소드
    private void startJoin(SignUpData data) {

        RetrofitClient.service.userSignUp(data).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                SignUpResponse result = response.body();
                Toast.makeText(Register.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);
                if (result.getCode() == 200) {
                    finish();
                }
            }
            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(Register.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }

    // $진행바 메소드
    private void showProgress(boolean show) {
        ProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }


    // $프로필 관련
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                try {
                    Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);

                    selfie.setBackground(new ShapeDrawable(new OvalShape()));
                    selfie.setClipToOutline(true);
                    selfie.setImageBitmap(bm);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    // $로그인 창으로 이동
    public void onClicked(View v){
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }
    public void onButtonClick(View v){
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }

}
