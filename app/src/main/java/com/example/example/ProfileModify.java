package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ProfileModify extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_modify);

        Spinner spinner = findViewById(R.id.country);
        //Spinner spinner2 = findViewById(R.id.age);

        //country
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.array_country, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new ProfileModify.MyOnItemSelectedListener());


        //ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
        //        this, R.array.array_age, android.R.layout.simple_spinner_item);
        //adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner2.setAdapter(adapter2);

        //spinner2.setOnItemSelectedListener(new ProfileModify.MyOnItemSelectedListener2());
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
