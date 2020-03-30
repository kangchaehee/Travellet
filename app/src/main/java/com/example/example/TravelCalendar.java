package com.example.example;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.yongbeom.aircalendar.AirCalendarDatePickerActivity;
import com.yongbeom.aircalendar.core.AirCalendarIntent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TravelCalendar extends AppCompatActivity {
    public final static int REQUEST_CODE = 1;

    public static RelativeLayout rl_iv_back_btn_bg;
    public static TextView tv_done_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_calendar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //rl_iv_back_btn_bg = (RelativeLayout) findViewById(com.yongbeom.aircalendar.R.id.rl_iv_back_btn_bg);
        //tv_done_btn = (TextView) findViewById(R.id.rl_done_btn);

        AirCalendarIntent intent = new AirCalendarIntent(this);
        intent.setSelectButtonText("Select");
        intent.setResetBtnText("Reset");
        intent.setWeekStart(Calendar.MONDAY);


        tv_done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent intent = new Intent(TravelCalendar.this, MainActivity.class);
                startActivity(intent);
            }
        });



//        ArrayList<String> weekDay = new ArrayList<>();
//        weekDay.add("M");
//        weekDay.add("T");
//        weekDay.add("W");
//        weekDay.add("T");
//        weekDay.add("F");
//        weekDay.add("S");
//        weekDay.add("S");
//        intent.setCustomWeekDays(weekDay);
//        intent.setWeekDaysLanguage(AirCalendarIntent.Language.EN);
//        intent.isSingleSelect(false);
//        intent.isBooking(false); // DEFAULT false
//        intent.isSelect(true); // DEFAULT false
//        intent.setBookingDateArray();
//        intent.setStartDate(2017, 12, 1);
//        intent.setEndDate(2017, 12, 1);
//        intent.isMonthLabels(false);
//        intent.setActiveMonth(3);
//        intent.setStartYear(2018);
//        intent.setMaxYear(2030);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
//            AirCalendarDatePickerActivity.RESULT_SELECT_START_DATE
//            AirCalendarDatePickerActivity.RESULT_SELECT_END_DATE
//            AirCalendarDatePickerActivity.RESULT_SELECT_START_VIEW_DATE
//            AirCalendarDatePickerActivity.RESULT_SELECT_END_VIEW_DATE
//            AirCalendarDatePickerActivity.RESULT_FLAG
//            AirCalendarDatePickerActivity.RESULT_TYPE
//            AirCalendarDatePickerActivity.RESULT_STATE

            if(data != null){
                Toast.makeText(this, "Select Date range : " + data.getStringExtra(AirCalendarDatePickerActivity.RESULT_SELECT_START_DATE) + " ~ " + data.getStringExtra(AirCalendarDatePickerActivity.RESULT_SELECT_END_DATE), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
