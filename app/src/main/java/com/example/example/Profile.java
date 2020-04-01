package com.example.example;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class Profile extends Fragment {

    Button editButton;
    TextView name, age, country;
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_profile, container, false);
        editButton = (Button)rootView.findViewById(R.id.btn_edit);

        //기존에 등록되어 있는 프로필 정보를 프로필 수정 화면에서도 볼 수 있도록 넘김.
        name = rootView.findViewById(R.id.name);
        age = rootView.findViewById(R.id.age);
        country = rootView.findViewById(R.id.country);
        image = rootView.findViewById(R.id.image);


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
                /*if( intent.getByteArrayExtra("image")== null){
                    image = null;
                }
                else{
                    byte[] byteArr = intent.getByteArrayExtra("image");
                    image = BitmapFactory.decodeByteArray(byteArr, 0, byteArr.length);
                }*/

                this.name.setText(name);
                this.age.setText(String.valueOf(age));
                this.country.setText(country);
                /*if(image == null){
                    this.image.setBackgroundResource(R.drawable.ic_profime_circle);
                }
                else{
                    this.image.setBackground(new ShapeDrawable(new OvalShape()));
                    this.image.setClipToOutline(true);
                    this.image.setImageBitmap(image);
                }*/
            }
        }

    }

    /*public void onClick(View view){

        Intent intent = new Intent(getContext(), ProfileModify.class);
        startActivity(intent);
    }*/
}
