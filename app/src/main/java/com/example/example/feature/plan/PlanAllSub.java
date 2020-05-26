package com.example.example.feature.plan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.example.R;
import com.example.example.feature.plan.PlanInitialSubItem;
import com.example.example.feature.plan.PlanInitialSubItemView;

import java.util.ArrayList;

public class PlanAllSub extends LinearLayout {
    TextView day, period;
    ListView listView;
    ArrayList<PlanInitialSubItem> items = new ArrayList<PlanInitialSubItem>();
    PlanSubAdapter adapter = new PlanSubAdapter();
    SQLiteDatabase database;
    int date, mainPosition;

    public PlanAllSub(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public PlanAllSub(Context context) {
        super(context);

        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_plan_all_sub, this, true);

        day = findViewById(R.id.day);
        period = findViewById(R.id.period);
        listView = findViewById(R.id.con);
    }

    public void setDay(int date, int mainPosition){
        day.setText("DAY "+date);
        this.date = date;
        this.mainPosition = mainPosition;
    }

    public void setPeriod(int year, int month, int day){
        period.setText(year+"."+month+"."+day);
    }

    public void setListView(){
        openDatabase("database");

        items.clear();
        settingList(date);
        listView.setAdapter(adapter);
        setListViewHeightBasedOnItems(listView);
        if(adapter.getCount() == 0){
            day.setVisibility(GONE);
            period.setVisibility(GONE);
        }
    }

    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                float px = 320 * (listView.getResources().getDisplayMetrics().density);
                item.measure(View.MeasureSpec.makeMeasureSpec((int) px, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);
            // Get padding
            int totalPadding = listView.getPaddingTop() + listView.getPaddingBottom();

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight + totalPadding;
            listView.setLayoutParams(params);
            listView.requestLayout();
            //setDynamicHeight(listView);
            return true;

        } else {
            return false;
        }

    }


    class PlanSubAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(PlanInitialSubItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            PlanInitialSubItemView view= new PlanInitialSubItemView(getContext());
            final PlanInitialSubItem item = items.get(position);
            view.setPlaceTime(item.getPlaceTime());
            view.setPlaceName(item.getPlaceName());
            view.setPlaceMemo(item.getPlaceMemo());
            int t = item.getTransport();
            if(t == 1){
                view.setTransportIc(R.drawable.ic_walk_24px);
                view.setTransportText("Walk");
            }

            if(t == 2){
                view.setTransportIc(R.drawable.ic_bus_24px);
                view.setTransportText("Bus");
            }

            if(t == 3){
                view.setTransportIc(R.drawable.ic_subway_24px);
                view.setTransportText("Subway");
            }

            if(t == 4){
                view.setTransportIc(R.drawable.ic_taxi_24px);
                view.setTransportText("Taxi");
            }

            if(t == 5){
                view.setTransportIc(R.drawable.ic_car_24px);
                view.setTransportText("Car");
            }

            view.setTransBudgetText(String.valueOf(item.getTransBudget()));

            return view;
        }
    }

    public void openDatabase(String databaseName){
        DatabaseHelper helper = new DatabaseHelper(getContext(), databaseName, null, 4);
        database = helper.getWritableDatabase();
    }

    class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            if(db != null){
                //_id 는 내부적으로 생성되는 아이디!
                String sql = "create table if not exists " + "PlanTable" + "(_id integer PRIMARY KEY autoincrement, date integer, year integer, month integer, day integer, type integer, place text, hour integer, min integer, memo text, transport integer, total_budget double, x double, y double, position integer)";
                db.execSQL(sql);

                sql = "create table if not exists " + "BudgetTable" + "(_id integer PRIMARY KEY autoincrement, date integer, type integer, budget double, memo text, plan_position integer, position integer)";
                db.execSQL(sql);

                //println("테이블 생성됨.");
            }else{
                //println("먼저 데이터베이스를 오픈하세요.");
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //println("onUpgrade 호출됨: "+oldVersion + ", " + newVersion);

            if(newVersion > 1) {
                db.execSQL("drop table if exists " + "PlanTable");
                db.execSQL("drop table if exists " + "BudgetTable");
                //println("테이블 삭제함");

                if (db != null) {
                    //_id 는 내부적으로 생성되는 아이디!
                    String sql = "create table if not exists " + "PlanTable" + "(_id integer PRIMARY KEY autoincrement, date integer, year integer, month integer, day integer, type integer, place text, hour integer, min integer, memo text, transport integer, total_budget double, x double, y double, position integer)";
                    db.execSQL(sql);

                    //_id 는 내부적으로 생성되는 아이디!
                    sql = "create table if not exists " + "BudgetTable" + "(_id integer PRIMARY KEY autoincrement, date integer, type integer, budget double, memo text, plan_position integer, position integer)";
                    db.execSQL(sql);

                    //println("테이블 새로 생성됨.");
                } else {
                    //println("먼저 데이터베이스를 오픈하세요.");
                }
            }
        }
    }

    public  void settingList(int dayNum){
        if(database != null){
            String sql = "select date, year, month, day, type, place, hour, min, memo, transport, total_budget, x, y from "+ "PlanTable"+" where date = "+dayNum+" and main_position = "+mainPosition;
            Cursor cursor = database.rawQuery(sql, null);
            //println("조회된 데이터 개수: "+cursor.getCount());

            for(int i=0; i<cursor.getCount(); i++){
                cursor.moveToNext();
                int date = cursor.getInt(0);
                int year = cursor.getInt(1);
                int month = cursor.getInt(2);
                int day = cursor.getInt(3);
                int type = cursor.getInt(4);
                String place = cursor.getString(5);
                int hour = cursor.getInt(6);
                int min = cursor.getInt(7);
                String memo = cursor.getString(8);
                int transport = cursor.getInt(9);
                double total_budget = cursor.getDouble(10);
                double x = cursor.getDouble(11);
                double y = cursor.getDouble(12);

                String ap;
                String sHour, sMin;
                if(hour >= 12) {
                    ap = "PM";
                    if(hour >12)
                        hour -= 12;
                }
                else
                    ap = "AM";

                if(hour<10)
                    sHour = "0"+hour;
                else
                    sHour = String.valueOf(hour);

                if(min<10)
                    sMin = "0"+min;
                else
                    sMin = String.valueOf(min);

                String time = ap+ " "+sHour+":"+sMin;

                Log.d("database", adapter.getCount()+"");
                adapter.addItem(new PlanInitialSubItem(time, place, memo, transport, type, x, y, total_budget));
                adapter.notifyDataSetChanged();

            }

            cursor.close();
        }
    }


}
