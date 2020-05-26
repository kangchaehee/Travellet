package com.example.example.feature.wallet;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.example.R;
import com.example.example.feature.wallet.cost.WalletCost;

import java.util.ArrayList;

public class WalletMainFragment extends Fragment {

    ListView listView1, listView2;
    ArrayList<WalletMainItem> itemsMain = new ArrayList<WalletMainItem>();
    WalletAdapter adapterMain = new WalletAdapter();

    ArrayList<WalletListSubItem> itemsList = new ArrayList<WalletListSubItem>();
    WalletSubAdapter adapterList = new WalletSubAdapter();

    ImageButton viewList;
    boolean listState = false;

    int startYear, startMonth, startDay, startDoW, endYear, endMonth, endDay, endDoW;

    TextView day, period, budgetText, costText;

    SQLiteDatabase database;
    int dayNum, mainPosition;


    public static WalletMainFragment newInstance() {
        return new WalletMainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.wallet_main_fragment, container, false);

        day = rootView.findViewById(R.id.day);
        period = rootView.findViewById(R.id.period);
        budgetText = rootView.findViewById(R.id.budgetText);
        costText = rootView. findViewById(R.id.costText);

        if(getArguments() != null){
            dayNum = getArguments().getInt("index");
            day.setText("DAY " + dayNum);
            startYear = getArguments().getInt("startYear", 0);
            startDay = getArguments().getInt("startDay", 0);
            startMonth = getArguments().getInt("startMonth", 0)+1;
            mainPosition =getArguments().getInt("mainPosition", 0);
            period.setText(startYear + "." + startMonth + "." + startDay) ;


        }

        openDatabase("database");

        if (database != null) {
            //_id 는 내부적으로 생성되는 아이디!
            Log.d("database", "CostTable");
            //String sql = "create table if not exists " + "CostTable" + "(_id integer PRIMARY KEY autoincrement, wallet_position, date, type, cost, payment, position, place)";
            //atabase.execSQL(sql);
        }
        settingListMain(dayNum);
        getDayTotalBudget();
        getDayTotalCost();

        viewList = rootView.findViewById(R.id.btn_list);
        listView1 = rootView.findViewById(R.id.mainList);
        listView1.setAdapter(adapterMain);

        listView2 = rootView.findViewById(R.id.viewList);
        listView2.setAdapter(adapterList);

        //리스트 추가된 거 확인하려면 얘네 주석 해제하면 됨.
        //adapterMain.addItem(new WalletMainItem("AM 10:00", "Lotte Hotel", "Lodging", 1200.0, 1000.0));

        //adapterList.addItem(new WalletListSubItem("Lodging", "Lotte Hotel", 1000.0, R.drawable.ic_lodging_24px, R.drawable.ic_card_24px));


        viewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!listState){
                    listState = true;
                    settingListSub(dayNum);
                    viewList.setBackgroundResource(R.drawable.ic_view_list_selected);
                    listView1.setVisibility(View.GONE);
                    listView2.setVisibility(View.VISIBLE);
                }

                else{
                    listState = false;
                    viewList.setBackgroundResource(R.drawable.ic_view_list);
                    listView1.setVisibility(View.VISIBLE);
                    listView2.setVisibility(View.GONE);
                }
            }
        });
        return rootView;
    }

    class WalletAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return itemsMain.size();
        }

        public void addItem(WalletMainItem item) {
            itemsMain.add(item);
        }

        @Override
        public Object getItem(int position) {
            return itemsMain.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            WalletMainItemView view = new WalletMainItemView(getContext());
            final WalletMainItem item = itemsMain.get(position);
            view.setW_time(item.getW_time());
            view.setW_title(item.getW_title());
            view.setW_memo(item.getW_memo());
            view.setW_cost(item.getW_cost());
            view.setW_budget(item.getW_budget());

            LinearLayout i = view.findViewById(R.id.wallet_linear);
            i.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), WalletCost.class);
                    intent.putExtra("wallet_position", position);
                    intent.putExtra("date", dayNum);
                    intent.putExtra("place", item.getW_title());
                    intent.putExtra("mainPosition", mainPosition);
                    startActivityForResult(intent, 101);
                }
            });

            return view;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == 101){
            if(intent != null){
                int position = intent.getIntExtra("position", 0);
                double totalCost = getDayCost(position);
                String sql = "update WalletTable set total_cost = ? where date = "+dayNum+" and position = "+position+" and main_position = "+mainPosition;
                Object[] params1 = {totalCost};
                database.execSQL(sql, params1);
                for(int i=0; i<adapterMain.getCount(); i++){

                }
                itemsMain.clear();
                settingListMain(dayNum);
                getDayTotalCost();
            }
        }
    }

    class WalletSubAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return itemsList.size();
        }

        public void addItem(WalletListSubItem item) {
            itemsList.add(item);
        }

        @Override
        public Object getItem(int position) {
            return itemsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            WalletListSubItemView view= new WalletListSubItemView(getContext());
            final WalletListSubItem item = itemsList.get(position);

            view.setCategory(item.getCategory());
            view.setPlace(item.getPlace());
            view.setMoney(item.getMoney());
            view.setCategory_ic(item.getCategory_ic());
            view.setPayment(item.getPayment());

            return view;
        }
    }

    public double getDayCost(int p){
        double b = 0;
        String sql = "select cost from CostTable where date = "+dayNum+" and wallet_position = "+p+" and main_position = "+mainPosition;
        Cursor cursor2 = database.rawQuery(sql, null);
        for(int j=0; j<cursor2.getCount(); j++){
            cursor2.moveToNext();
            b += cursor2.getDouble(0);

            Log.d("pass", String.valueOf(b));
        }
        cursor2.close();

        return b;
    }

    public void getDayTotalBudget(){

        double dayTotal = 0;
        String sql = "select budget from BudgetTable where date = "+dayNum+" and main_position = "+mainPosition;
        Cursor cursor = database.rawQuery(sql, null);
        for(int i=0; i<cursor.getCount(); i++){
            cursor.moveToNext();
            dayTotal += cursor.getDouble(0);
        }
        cursor.close();
        budgetText.setText(String.valueOf(dayTotal));
    }

    public void getDayTotalCost(){

        double dayCost = 0;
        String sql = "select cost from CostTable where date = "+dayNum+" and main_position = "+mainPosition;
        Cursor cursor = database.rawQuery(sql, null);
        for(int i=0; i<cursor.getCount(); i++){
            cursor.moveToNext();
            dayCost += cursor.getDouble(0);
        }
        cursor.close();
        costText.setText(String.valueOf(dayCost));
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

    public  void settingListMain(int dayNum){
        if(database != null){
            itemsMain.clear();
            String sql = "select date, year, month, day, type, place, hour, min, memo, total_budget, total_cost from "+ "WalletTable"+" where date = "+dayNum+" and main_position = "+mainPosition;
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
                double total_budget = cursor.getDouble(9);
                double total_cost = cursor.getDouble(10);

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

                Log.d("database", "#"+i+"->"+date+", "+year+", "+month+", "+day+", "+type+", "+place+", "+hour+", "+min+", "+memo+", "+total_budget);
                adapterMain.addItem(new WalletMainItem(time, place, memo, total_cost, total_budget));
                adapterMain.notifyDataSetChanged();
            }

            cursor.close();
        }
    }

    public  void settingListSub(int dayNum){
        if(database != null){
            itemsList.clear();
            String sql = "select type, place, cost, payment from "+ "CostTable"+" where date = "+dayNum+" and main_position = "+mainPosition;
            Cursor cursor = database.rawQuery(sql, null);
            //println("조회된 데이터 개수: "+cursor.getCount());

            for(int i=0; i<cursor.getCount(); i++){
                String typeStr="";
                cursor.moveToNext();
                int type = cursor.getInt(0);
                switch (type){
                    case 1:
                        typeStr = "Lodging";
                        break;
                    case 2:
                        typeStr = "Food";
                        break;
                    case 3:
                        typeStr = "Shopping";
                        break;
                    case 4:
                        typeStr = "Tourism";
                        break;
                    case 5:
                        typeStr = "Transport";
                        break;
                    case 6:
                        typeStr = "Etc";
                        break;
                }
                String place = cursor.getString(1);
                double cost = cursor.getDouble(2);
                int payment = cursor.getInt(3);

                Log.d("database", "#"+i+"->"+type+", "+place+", "+cost+", "+payment);
                adapterList.addItem(new WalletListSubItem(typeStr, place, cost, type, payment));
                adapterList.notifyDataSetChanged();
            }

            cursor.close();
        }
    }
}
