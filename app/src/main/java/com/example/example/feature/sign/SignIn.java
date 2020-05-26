package com.example.example.feature.sign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.example.feature.main.Main;
import com.example.example.R;
import com.example.example.feature.SplashActivity;
import com.example.example.data.LoginData;
import com.example.example.data.LoginResponse;
import com.example.example.network.AppHelper;
import com.example.example.network.InfoID;
import com.example.example.network.RetrofitClient;
import com.example.example.network.ServiceApi;

import com.example.example.volley.ResponseInfo;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignIn extends AppCompatActivity {

    private ServiceApi service;
//    private ProgressBar ProgressView;

    Button btn_register, btn_signin;

    // $이메일과 비밀번호
    private EditText Edittext_email;
    private EditText Edittext_pw;

    // $이메일이랑 비밀번호 입력 잘 됐는지 확인
    boolean testEmail = false, testPw = false, testSign=false;

    String email, password;

    String exchangeCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        service = RetrofitClient.getClient().create(ServiceApi.class);
//        ProgressView = (ProgressBar) findViewById(R.id.login_progress);

        // 스플래쉬
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);

        // 이메일, 패스워드
        Edittext_email = (EditText) findViewById(R.id.Edittext_email);
        Edittext_pw = (EditText) findViewById(R.id.Edittext_pw);
        btn_signin = (Button) findViewById(R.id.btn_signin);

        email = Edittext_email.getText().toString();
        password = Edittext_pw.getText().toString();


        // $이메일 유효성 검사
        Edittext_email.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    Edittext_email.setTextColor(getResources().getColor(R.color.soft_black));
                }

                else{
                    Pattern p = Pattern.compile("^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$");
                    Matcher m = p.matcher((Edittext_email).getText().toString());

                    if (!m.matches()){
                        Toast.makeText(SignIn.this,"Please enter in email format.",Toast.LENGTH_LONG).show();
                        Edittext_email.setTextColor(getResources().getColor(R.color.coral_red));
                        testEmail = false;
                    }

                    else
                        testEmail = true;
                    InputMethodManager input = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    input.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                }
            }
        });

        Edittext_email.addTextChangedListener(new TextWatcher() {
            String str;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                str =s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Pattern p = Pattern.compile("^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$");
                Matcher m = p.matcher((Edittext_email).getText().toString());

                if (!m.matches()){
                    testEmail = false;
                }
                else
                    testEmail = true;
                if(testPw && testEmail){
                    testSign = true;
                    btn_signin.setBackgroundResource(R.drawable.button_background_full);
                    btn_signin.setTextColor(getResources().getColor(R.color.white));
                }
                else{
                    testSign = false;
                    btn_signin.setBackgroundResource(R.drawable.button_background_border);
                    btn_signin.setTextColor(getResources().getColor(R.color.blue));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Edittext_pw.addTextChangedListener(new TextWatcher() {
            String str;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                str = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.length()>1){
                        testPw = true;
                    }
                    else{
                        testPw = false;
                    }
                //testEmail, testPw 모두 true 이면 sign in 활성화
                if(testPw && testEmail){
                    testSign = true;
                    btn_signin.setBackgroundResource(R.drawable.button_background_full);
                    btn_signin.setTextColor(getResources().getColor(R.color.white));
                }
                else{
                    testSign = false;
                    btn_signin.setBackgroundResource(R.drawable.button_background_border);
                    btn_signin.setTextColor(getResources().getColor(R.color.blue));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // $로그인 버튼 이벤트
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = Edittext_email.getText().toString();
                password = Edittext_pw.getText().toString();
                //testSign -> true 일 때만 로그인 가능
                if(testSign){
                    startLogin(new LoginData(email, password));
//                  showProgress(true);
                }
            }
        });

        // $한번만 만들어지도록
        if(AppHelper.requestQueue == null){
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    // $환율 요청
    public void requestExchange(){
        String url = AppHelper.host+"?app_id="+AppHelper.appId;

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("onResponse:", "응답 받음");
                        processResponse(response);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("에러 발생", error.getMessage());
                    }
                }
        );
        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
        Log.d("request", "요청 보냄");
    }

    // $환율 요청 응답
    public void processResponse(String response){
        Gson gson = new Gson();
        ResponseInfo info = gson.fromJson(response, ResponseInfo.class);
        if(info.base != null){
            InfoID.KRW = info.rates.KRW;
            switch (exchangeCountry){
                case "Japan":
                    InfoID.exchange = info.rates.JPY;
                    break;
                case "China":
                    InfoID.exchange = info.rates.CNY;
                    break;
                case "HongKong":
                    InfoID.exchange = info.rates.HKD;
                    break;
                case "Singapore":
                    InfoID.exchange = info.rates.SGD;
                    break;
                case "Taipei":
                    InfoID.exchange = info.rates.TWD;
                    break;
                case "Thailand":
                    InfoID.exchange = info.rates.THB;
                    break;
                case "Malaysia":
                    InfoID.exchange = info.rates.MYR;
                    break;
                case "Australia":
                    InfoID.exchange = info.rates.AUD;
                    break;
                case "U.S.A":
                    InfoID.exchange = info.rates.USD;
                    break;
                case "Canada":
                    InfoID.exchange = info.rates.CAD;
                    break;
                case "U.K":
                    InfoID.exchange = info.rates.GBP;
                    break;
                case "Germany": case "France":
                    InfoID.exchange = info.rates.EUR;
                    break;
                case "Russia":
                    InfoID.exchange = info.rates.RUB;
                    break;
                case "India":
                    InfoID.exchange = info.rates.INR;
                    break;
                case "Philippines":
                    InfoID.exchange = info.rates.PHP;
                    break;
                case "Indonesia":
                    InfoID.exchange = info.rates.IDR;
                    break;
                case "Vietnam":
                    InfoID.exchange = info.rates.VND;
                    break;
                default:
                    InfoID.exchange = info.rates.KRW;
            }
            Log.d("exchange: ", String.valueOf(InfoID.exchange));
            Log.d("KRW: ", String.valueOf(InfoID.KRW));
        }
    }

    private void startLogin(LoginData data) {
        service.userLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                Toast.makeText(SignIn.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                InfoID.userId = result.getUserId();
                exchangeCountry = result.getUserCountry();
                Log.d("signUserID", String.valueOf(InfoID.userId));
                Log.d("signCountry", String.valueOf(exchangeCountry));
//                showProgress(false);

                if (result.getCode() == 200) {
                    requestExchange();
                    Intent intent = new Intent(SignIn.this, Main.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(SignIn.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
//                showProgress(false);
            }
        });
    }

    // $회원가입 버튼 이벤트 - 회원 가입 창으로 이동
    public void onButtonClick(View view2){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}
