package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.example.data.ProfileResponse;
import com.example.example.data.ProfileUpdateData;
import com.example.example.data.ProfileUpdateResponse;
import com.example.example.network.AppHelper;
import com.example.example.network.InfoID;
import com.example.example.network.RetrofitClient;
import com.example.example.network.ServiceApi;
import com.example.example.volley.ResponseInfo;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileModify extends AppCompatActivity {

    private ServiceApi service;

    Spinner spinner;
    Spinner spinner2;

    private final int GET_GALLERY_IMAGE = 200;
    private ImageView selfie;

    EditText Edittext_name;
    String exchangeCountry;

    ImageButton back;
    Button save;

    String country, name;
    int age;
    Bitmap image;
    int ageIndex, countryIndex;
    byte[] bytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile_modify);

        // $프로필 정보 가져오기
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        country = intent.getStringExtra("country");
        age = intent.getIntExtra("age", 0);
        /*if( intent.getByteArrayExtra("image")== null){
            image = null;
        }
        else{
            bytes = intent.getByteArrayExtra("image");
            image = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }*/

        // $네트워크를 위한 serviceApi 객체 생성
        service = RetrofitClient.getClient().create(ServiceApi.class);

        Edittext_name = findViewById(R.id.Edittext_name);
        spinner = findViewById(R.id.country);
        spinner2 = findViewById(R.id.age);
        selfie = (ImageView)findViewById(R.id.profile);

        Edittext_name.setText(name);

        /*if(image == null){
            selfie.setBackgroundResource(R.drawable.ic_profime_circle);
        }
        else{
            selfie.setBackground(new ShapeDrawable(new OvalShape()));
            selfie.setClipToOutline(true);
            selfie.setImageBitmap(image);
        }*/

        String[] ageArr = getResources().getStringArray(R.array.array_age);
        String[] countryArr = getResources().getStringArray(R.array.array_country);

        for(int i=0; i<ageArr.length; i++){
            if(age == Integer.parseInt(ageArr[i])){
                ageIndex = i;
                break;
            }
        }

        for(int i=0; i<countryArr.length; i++){
            if(country.equals(countryArr[i])){
                countryIndex = i;
                break;
            }
        }

        // $나이 스피너
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.array_age, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(ageIndex);
        spinner2.setOnItemSelectedListener(new ProfileModify.MyOnItemSelectedListener());
            //이건 화살표 색깔
        spinner2.getBackground().setColorFilter(Color.parseColor("#c8cbd3"), PorterDuff.Mode.SRC_ATOP);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                age = Integer.parseInt(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


         // $국가 스피너
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.array_country, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(countryIndex);
        spinner.setOnItemSelectedListener(new ProfileModify.MyOnItemSelectedListener());
            //화살표 색깔
        spinner.getBackground().setColorFilter(Color.parseColor("#c8cbd3"), PorterDuff.Mode.SRC_ATOP);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // $이미지 관련인듯?
        selfie.setClipToOutline(true);
        selfie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

        // $뒤로가기 버튼 이벤트
        back = (ImageButton) findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // $프로필 저장 버튼 이벤트
        save = (Button) findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 프로필 수정 DB에 업데이트
                updateProfile(new ProfileUpdateData(InfoID.userId, Edittext_name.getText().toString(), age, country));
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

    // $프로필 수정 통신 메소드
    private void updateProfile(ProfileUpdateData data) {
        service.userProfileUpdate(data).enqueue(new Callback<ProfileUpdateResponse>() {
            @Override
            public void onResponse(Call<ProfileUpdateResponse> call, Response<ProfileUpdateResponse> response) {
                ProfileUpdateResponse result = response.body();
                exchangeCountry = country;

                if (result.getCode() == 200) {
                    requestExchange();
                    returnToBack();
                }
            }
            @Override
            public void onFailure(Call<ProfileUpdateResponse> call, Throwable t) {
                Log.e("프로필 수정 에러 발생", t.getMessage());
            }
        });
    }

    // $프로필 페이지로 이동
    public void returnToBack(){
        Intent intent = getIntent();
        intent.putExtra("country", country);
        intent.putExtra("age", age);
        intent.putExtra("name", Edittext_name.getText().toString());
        BitmapDrawable drawable = (BitmapDrawable) selfie.getDrawable();
        if(drawable == null){
            bytes=null;
        }
        else{
            image = drawable.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, stream);
            bytes = stream.toByteArray();
        }
        intent.putExtra("image", bytes);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                try {

                    image = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);

                    selfie.setBackground(new ShapeDrawable(new OvalShape()));
                    selfie.setClipToOutline(true);
                    selfie.setImageBitmap(image);

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

    // country
    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
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

    //age
    //public class MyOnItemSelectedListener2 implements AdapterView.OnItemSelectedListener {
    //    @Override
    //    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //Toast.makeText(parent.getContext(),
            //        "Your country is "+parent.getItemAtPosition(position),
            //        Toast.LENGTH_SHORT).show();
    //    }
    //    @Override
    //    public void onNothingSelected(AdapterView<?> arg0) {
            // Do nothing
    //    }
    //}

}
