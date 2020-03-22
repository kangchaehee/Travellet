package com.example.example;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TravelTitleSet extends AppCompatActivity {

    EditText Edittext_title;
    Button btn_Next;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_title_set);

        Edittext_title = (EditText) findViewById(R.id.Edittext_title);

        btn_Next = (Button) findViewById(R.id.btn_Next);
        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = Edittext_title.getText().toString();
                if(title == null || title.length() == 0){
                    Edittext_title.setHintTextColor(getColor(R.color.coral_red));
                    //btn_Next.isClickable(false);
                }else{
                    Intent intent = new Intent(TravelTitleSet.this, TravelCalendar.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 50){
            String title = Edittext_title.getText().toString();
            if(title != null){
                //title2 = intent.getStringExtra("Edittext_title");
                Edittext_title.setTextColor(getColor(R.color.soft_black));
                Edittext_title.setText(title);
            }
        }
    }

    public void returnToBack() {
        title = Edittext_title.getText().toString();
        if (title == null) {
            Log.d("null", "null");
        } else
            Log.d("memo", title);


        Intent intent = new Intent();

        setResult(RESULT_OK, intent);
        finish();
    }



    //Main Empty
    public void onButtonClick(View view1){

        Intent intent = new Intent(TravelTitleSet.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    /*
    //travelcalendar
    public void onClick(View view){

        Intent intent = new Intent(TravelTitleSet.this, TravelCalendar.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

     */
}
