package com.example.example.feature.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.example.R;
import com.example.example.data.ProfileReadData;
import com.example.example.data.ProfileReadResponse;
import com.example.example.network.InfoID;
import com.example.example.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends Fragment {

//    private ServiceApi service;

    Button editButton;
    TextView name, age, country, titleName;
    ImageView image;
    Bitmap imageB;
    byte[] bytes;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // $네트워크를 위한 serviceApi 객체 생성 -
//        service = RetrofitClient.getClient().create(ServiceApi.class);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_profile, container, false);
        editButton = (Button)rootView.findViewById(R.id.btn_edit);

        name = rootView.findViewById(R.id.name);
        age = rootView.findViewById(R.id.age);
        country = rootView.findViewById(R.id.country);
        image = rootView.findViewById(R.id.image);
        titleName = rootView.findViewById(R.id.textView2);

        // $프로필 정보 DB 에서 불러오기
        setProfile(InfoID.userId);

        /*BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
        if(drawable == null){
            bytes = null;
        }
        else{
            imageB = drawable.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageB.compress(Bitmap.CompressFormat.PNG, 100, stream);
            bytes = stream.toByteArray();
        }*/

        // $프로필 수정 페이지로 이동 & 프로필 정보 보내기
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfileModify.class);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("age", Integer.parseInt(age.getText().toString()));
                intent.putExtra("country", country.getText().toString());
                intent.putExtra("image", bytes);
                startActivityForResult(intent, 101);
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == 101){
            if(intent != null){
                Bitmap image;
                String name = intent.getStringExtra("name");
                int age = intent.getIntExtra("age", 0);
                String country = intent.getStringExtra("country");
                //이미지 파일 용량 개큰건 바이트로 변환해도 안넘어와서 db 사용해야할 듯.
                if( intent.getByteArrayExtra("image")== null){
                    image = null;
                }
                else{
                    byte[] byteArr = intent.getByteArrayExtra("image");
                    image = BitmapFactory.decodeByteArray(byteArr, 0, byteArr.length);
                }

                this.titleName.setText(name);
                this.name.setText(name);
                this.age.setText(String.valueOf(age));
                this.country.setText(country);
                if(image == null){
                    this.image.setBackgroundResource(R.drawable.ic_profime_circle);
                }
                else{
                    this.image.setBackground(new ShapeDrawable(new OvalShape()));
                    this.image.setClipToOutline(true);
                    this.image.setImageBitmap(image);
                }
            }
        }

    }

    // $프로필 설정 통신 메소드
    private void setProfile(int userId) {
        RetrofitClient.service.userProfileRead(userId).enqueue(new Callback<ProfileReadResponse>() {
            @Override
            public void onResponse(Call<ProfileReadResponse> call, Response<ProfileReadResponse> response) {
                ProfileReadResponse result = response.body();
                if(result.getCode() == 200){
                    name.setText(result.getUserName());
                    age.setText(String.valueOf(result.getUserAge()));
                    country.setText(result.getUserCountry());
                    titleName.setText(result.getUserName());
                }
            }
            @Override
            public void onFailure(Call<ProfileReadResponse> call, Throwable t) {
                Log.e("프로필 설정 에러 발생", t.getMessage());
            }
        });
    }

    /*public void onClick(View view){

        Intent intent = new Intent(getContext(), ProfileModify.class);
        startActivity(intent);
    }*/
}
