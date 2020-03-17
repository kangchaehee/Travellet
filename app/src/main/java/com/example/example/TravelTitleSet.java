package com.example.example;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TravelTitleSet extends AppCompatActivity {

    EditText edittitle;
    Button btn_Next;
    String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_title_set);

        edittitle = (EditText) findViewById(R.id.edittitle);


        btn_Next = (Button) findViewById(R.id.btn_Next);
        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title == null){
                    edittitle.setHintTextColor(getColor(R.color.coral_red));
                }
                else
                    returnToBack();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 50){
            if(intent != null){
                title = intent.getStringExtra("edittitle");
                edittitle.setTextColor(getColor(R.color.soft_black));
                edittitle.setText(title);
            }
        }
    }

    public void returnToBack() {

        title = edittitle.getText().toString();
        if (title == null) {
            Log.d("null", "null");
        } else
            Log.d("title", title);

        }

    //Main Empty
    public void onButtonClick(View view1){
        finish();
    }

    //travelcalendar
    public void onClick(View view){

        Intent intent = new Intent(TravelTitleSet.this, TravelCalendar.class);
        startActivity(intent);
        overridePendingTransition(0, 0);

    }
}
