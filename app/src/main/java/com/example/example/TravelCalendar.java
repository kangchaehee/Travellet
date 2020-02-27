package com.example.example;

import androidx.appcompat.app.AppCompatActivity;


import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;

import androidx.recyclerview.widget.OrientationHelper;

import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.applikeysolutions.cosmocalendar.view.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;



public class TravelCalendar extends AppCompatActivity {

    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_calendar);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        initViews();
    }

    private void initViews() {
        calendarView = (CalendarView) findViewById(R.id.calendar_view);
        calendarView.setCalendarOrientation(OrientationHelper.HORIZONTAL);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.clear_selections:
                clearSelectionsMenuClick();
                return true;

            case R.id.show_selections:
                List<Calendar> days = calendarView.getSelectedDates();

                String result="";
                for( int i=0; i<days.size(); i++)
                {
                    Calendar calendar = days.get(i);
                    final int day = calendar.get(Calendar.DAY_OF_MONTH);
                    final int month = calendar.get(Calendar.MONTH);
                    final int year = calendar.get(Calendar.YEAR);
                    String week = new SimpleDateFormat("EE").format(calendar.getTime());
                    String day_full = year + "년 "+ (month+1)  + "월 " + day + "일 " + week + "요일";
                    result += (day_full + "\n");
                }
                Toast.makeText(TravelCalendar.this, result, Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void clearSelectionsMenuClick() {
        calendarView.clearSelections();

    }

    //preference set
    public void onButtonClick(View view1){

        Intent intent = new Intent(this, TravelPreferenceSet.class);
        startActivity(intent);
    }

    // Budget show
    public void onClick(View view){

        Intent intent = new Intent(this, TravelBudgetShow.class);
        startActivity(intent);
    }

}
