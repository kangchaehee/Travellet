package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;

public class ProfileModify extends AppCompatActivity {

    Spinner spinner;
    Spinner spinner2;

    private final int GET_GALLERY_IMAGE = 200;
    private ImageView selfie;

    EditText Edittext_name;

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

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        country = intent.getStringExtra("country");
        age = intent.getIntExtra("age", 0);
        image = (Bitmap) intent.getParcelableExtra("image");

        Edittext_name = findViewById(R.id.Edittext_name);
        spinner = findViewById(R.id.country);
        spinner2 = findViewById(R.id.age);
        selfie = (ImageView)findViewById(R.id.profile);

        Edittext_name.setText(name);
        if(image == null){
            selfie.setBackgroundResource(R.drawable.ic_profime_circle);
        }
        else{
            selfie.setBackground(new ShapeDrawable(new OvalShape()));
            selfie.setClipToOutline(true);
            selfie.setImageBitmap(image);
        }

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
            //array_age
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


            //country
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

        selfie.setClipToOutline(true);
        selfie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

        back = (ImageButton) findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save = (Button) findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToBack();
            }
        });
    }

    public void returnToBack(){
        Intent intent = getIntent();
        intent.putExtra("country", country);
        intent.putExtra("age", age);
        intent.putExtra("name", Edittext_name.getText().toString());
        BitmapDrawable drawable = (BitmapDrawable) selfie.getDrawable();
        if(drawable == null){
            image = null;
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
