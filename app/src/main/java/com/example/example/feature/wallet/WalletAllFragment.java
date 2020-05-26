package com.example.example.feature.wallet;

import android.content.Context;
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

import java.util.ArrayList;

public class WalletAllFragment extends Fragment {

    ListView listView1, listView2;
    ArrayList<WalletMainItem> itemsMain = new ArrayList<WalletMainItem>();

    ArrayList<WalletListSubItem> itemsList = new ArrayList<WalletListSubItem>();
    WalletSubAdapter adapterList = new WalletSubAdapter();

    ImageButton viewList;
    boolean listState = false;

    int startYear, startMonth, startDay, startDoW, endYear, endMonth, endDay, endDoW;
    int periodInt;

    TextView costText, budgetText;
    SQLiteDatabase database;

    int mainPosition;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_wallet_all, container, false);

        viewList = rootView.findViewById(R.id.btn_list);
        listView2 = rootView.findViewById(R.id.viewList);
        listView2.setAdapter(adapterList);

        costText = rootView.findViewById(R.id.costText);
        budgetText = rootView.findViewById(R.id.budgetText);

        LinearLayout con = rootView.findViewById(R.id.lin);
        openDatabase("database");

        if (getArguments() != null) {
            mainPosition = getArguments().getInt("mainPosition", 0);
            periodInt = getArguments().getInt("period", 0);
            startYear = getArguments().getInt("startYear", 0);
            startDay = getArguments().getInt("startDay", 0);
            startMonth = getArguments().getInt("startMonth", 0);
            for (int i = 0; i < periodInt; i++) {
                int date = i+1;
                WalletAllSub walletItem = new WalletAllSub(getContext());
                walletItem.setDay(date, mainPosition);
                walletItem.setPeriod(startYear, startMonth, startDay+i);
                walletItem.setListMainView();
                con.addView(walletItem);
            }
        }

        getDayTotalBudget();
        getDayTotalCost();



        viewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!listState) {
                    itemsList.clear();
                    listState = true;
                    for(int i=0; i<periodInt; i++){
                        int date = i+1;
                        settingListSub(date);
                    }
                    viewList.setBackgroundResource(R.drawable.ic_view_list_selected);
                    con.setVisibility(View.GONE);
                    listView2.setVisibility(View.VISIBLE);
                } else {
                    listState = false;
                    viewList.setBackgroundResource(R.drawable.ic_view_list);
                    con.setVisibility(View.VISIBLE);
                    listView2.setVisibility(View.GONE);
                }
            }
        });
        return rootView;
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
            WalletListSubItemView view = new WalletListSubItemView(getContext());
            final WalletListSubItem item = itemsList.get(position);

            view.setCategory(item.getCategory());
            view.setPlace(item.getPlace());
            view.setMoney(item.getMoney());
            view.setCategory_ic(item.getCategory_ic());
            view.setPayment(item.getPayment());

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



    public  void settingListSub(int dayNum){
        if(database != null){
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

    public void getDayTotalBudget(){

        double dayTotal = 0;
        String sql = "select budget from BudgetTable"+" where main_position = "+mainPosition;
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
        String sql = "select cost from CostTable"+" where main_position = "+mainPosition;
        Cursor cursor = database.rawQuery(sql, null);
        for(int i=0; i<cursor.getCount(); i++){
            cursor.moveToNext();
            dayCost += cursor.getDouble(0);
        }
        cursor.close();
        costText.setText(String.valueOf(dayCost));
    }
}
