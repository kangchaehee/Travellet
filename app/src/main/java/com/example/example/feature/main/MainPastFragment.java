package com.example.example.feature.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.example.R;
import com.example.example.feature.plan.Navigation;

public class MainPastFragment extends Fragment {

    ListView listView;
    ArrayList<MainItem> items = new ArrayList<MainItem>();
    MainPlanAdapter adapter = new MainPlanAdapter();
    double[] budgetList = new double[5];
    DeleteDialog oDialog;
    int[] imageList = {R.drawable.seoul1, R.drawable.seoul2, R.drawable.seoul3, R.drawable.seoul4, R.drawable.seoul5};

    int startYear, startMonth, startDay, endYear, endMonth, endDay;
    String dDay;
    String travelTitle;
    float budget, lodgingBudget, foodBudget, leisureBudget, shoppingBudget, transportBudget, etcBudget;

    TextView t;

    SQLiteDatabase database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_main_past, container, false);

        listView = (ListView) rootView.findViewById(R.id.listView);
        t = (TextView) rootView.findViewById(R.id.t);

        items.clear();
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        if(getArguments() != null){
            startYear = getArguments().getInt("startYear", 0);
            startMonth = getArguments().getInt("startMonth", 0);
            startDay = getArguments().getInt("startDay", 0);
            endYear = getArguments().getInt("endYear", 0);
            endMonth = getArguments().getInt("endMonth", 0);
            endDay = getArguments().getInt("endDay", 0);
            travelTitle = getArguments().getString("travelTitle");
            dDay = getArguments().getString("dDay");
            budget = getArguments().getFloat("budget", 0);
            lodgingBudget = getArguments().getFloat("lodgingBudget", 0);
            foodBudget = getArguments().getFloat("foodBudget", 0);
            leisureBudget = getArguments().getFloat("leisureBudget", 0);
            shoppingBudget = getArguments().getFloat("shoppingBudget", 0);
            transportBudget = getArguments().getFloat("transportBudget", 0);
            etcBudget = getArguments().getFloat("etcBudget", 0);
            adapter.addItem(new MainItem(dDay, travelTitle, startYear+"."+startMonth+"."+startDay, endYear+"."+endMonth+"."+endDay));
        }
        oDialog = new DeleteDialog(getContext());

        //adapter.addItem(new MainItem("D+100", "happy trip", "2020.01.04", "2020.02.01"));
        //adapter.addItem(new MainItem("D+15", "hello", "2020.01.05", "2020.02.02"));
        //adapter.addItem(new MainItem("D+16", "lalala", "2020.01.06", "2020.02.03"));
        openDatabase("database");
        if(database != null){
            String sql = "create table if not exists " + "MainTable" + "(_id integer PRIMARY KEY autoincrement, position integer, start_year integer, start_month integer, start_day integer, end_year integer, end_month integer, end_day integer, d_day integer, title text, total_budget double, lodging_budget double, food_budget double, shopping_budget double, tourism_budget double, etc_budget double, transport_budget double, state integer)";
            database.execSQL(sql);
        }

        settingList();

        return rootView;
    }

    class MainPlanAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(MainItem item) {
            items.add(item);
            t.setVisibility(View.GONE);
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
            MainItemView view= new MainItemView(getContext());
            final MainItem item = items.get(position);
            view.setImage(imageList[position%5]);
            view.setdDay(item.getdDay());
            view.setTitle(item.getTripTitle());
            view.setPeriod(item.getStartDay()+"-"+item.getEndDay());

            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Navigation.class);
                    intent.putExtra("startYear", startYear);
                    intent.putExtra("startMonth", startMonth);
                    intent.putExtra("startDay", startDay);
                    intent.putExtra("endYear", endYear);
                    intent.putExtra("endMonth", endMonth);
                    intent.putExtra("endDay", endDay);
                    intent.putExtra("travelTitle", travelTitle);
                    intent.putExtra("budget", budget);
                    intent.putExtra("lodgingBudget", lodgingBudget);
                    intent.putExtra("foodBudget", foodBudget);
                    intent.putExtra("leisureBudget", leisureBudget);
                    intent.putExtra("shoppingBudget", shoppingBudget);
                    intent.putExtra("transportBudget", transportBudget);
                    intent.putExtra("etcBudget", etcBudget);
                    startActivity(intent);
                }
            });

            ImageButton del = (ImageButton) view.findViewById(R.id.up_delete);
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    oDialog.setCancelable(false);
                    oDialog.show();

                    oDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            boolean del = oDialog.getDelete();
                            if(del){
                                items.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    });

                }
            });

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

    public  void settingList(){
        if(database != null){
            //adapter.addItem(new MainItem(dDay, travelTitle, startYear+"."+startMonth+"."+startDay, endYear+"."+endMonth+"."+endDay));
            String sql = "select d_day, title, start_year, start_month, start_day, end_year, end_month, end_day from "+ "MainTable"+" where state = 1";
            Cursor cursor = database.rawQuery(sql, null);
            //println("조회된 데이터 개수: "+cursor.getCount());

            for(int i=0; i<cursor.getCount(); i++){
                cursor.moveToNext();
                int dDay = cursor.getInt(0);
                String travelTitle = cursor.getString(1);
                int startYear = cursor.getInt(2);
                int startMonth = cursor.getInt(3);
                int startDay = cursor.getInt(4);
                int endYear = cursor.getInt(5);
                int endMonth = cursor.getInt(6);
                int endDay = cursor.getInt(7);

                //Log.d("database", "#"+i+"->"+date+", "+year+", "+month+", "+day+", "+type+", "+place+", "+hour+", "+min+", "+memo+", "+total_budget);
                adapter.addItem(new MainItem("D+"+dDay, travelTitle, startYear+"."+startMonth+"."+startDay, endYear+"."+endMonth+"."+endDay));
            }

            cursor.close();
        }
    }

}

