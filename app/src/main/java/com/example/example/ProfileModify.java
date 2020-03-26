package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ProfileModify extends AppCompatActivity {

    Spinner spinner;
    Spinner spinner2;

    private final int GET_GALLERY_IMAGE = 200;
    private ImageView selfie;

    EditText Edittext_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_modify);

        Edittext_name = findViewById(R.id.Edittext_name);

        spinner = findViewById(R.id.country);
        spinner2 = findViewById(R.id.age);

        //array_age
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.array_age, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new ProfileModify.MyOnItemSelectedListener());
        //이건 화살표 색깔
        spinner2.getBackground().setColorFilter(Color.parseColor("#c8cbd3"), PorterDuff.Mode.SRC_ATOP);

        //country
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.array_country, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new ProfileModify.MyOnItemSelectedListener());

        selfie = (ImageView)findViewById(R.id.profile);
        selfie.setClipToOutline(true);
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

    public void onButtonClick(View view){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    public void onClick(View view){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
}
