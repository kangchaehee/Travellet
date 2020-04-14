package com.example.example;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.odsay.odsayandroidsdk.API;
import com.odsay.odsayandroidsdk.ODsayData;
import com.odsay.odsayandroidsdk.ODsayService;
import com.odsay.odsayandroidsdk.OnResultCallbackListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class PlanInitialFragment extends Fragment {
    Button addButton, placeSearch;
    Button withFriends;
    TextView day, period;


    ImageView calculation, budget;
    TextView calculationText, budgetText;

    ListView listView;
    ArrayList<PlanInitialSubItem> items = new ArrayList<PlanInitialSubItem>();
    PlanSubAdapter adapter = new PlanSubAdapter();

    String time, name, memo;
    int type, dayNum;
    double x, y;
    TextView transBudget;
    FragmentCallBack callback;

    String[] timeList, titleList, memoList;
    double[] budgetList;

    DeleteDialog oDialog;
    TransportDialog tDialog;

    int startYear, startMonth, startDay, endYear, endMonth, endDay;
    double budgetTotal=0, budgetRemainder=0;
    double transportTotal=0;
    double lodgingBudget=0, foodBudget=0, shoppingBudget=0, tourismBudget=0, etcBudget=0;
    int lodging=0, food=0, tourism=0, shopping=0, transport=0, etc=0;
    int periodInt;
    int mainPosition;


    SQLiteDatabase database;


    @Override
    public void onPause() {
        super.onPause();

        timeList = new String[adapter.getCount()];
        titleList = new String[adapter.getCount()];
        memoList = new String[adapter.getCount()];
        budgetList = new double[adapter.getCount()];
        for(int i=0; i<adapter.getCount(); i++){
            timeList[i] = items.get(i).getPlaceTime();
            titleList[i] = items.get(i).getPlaceName();
            memoList[i] = items.get(i).getPlaceMemo();
            budgetList[i] = items.get(i).getTransBudget();
        }

        if(callback != null){
            callback.getPlanInfo(adapter.getCount(), timeList, titleList, memoList, budgetList);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_plan_initial, container, false);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        day = rootView.findViewById(R.id.day);
        period = rootView.findViewById(R.id.period);

        calculationText = rootView.findViewById(R.id.calculationText);
        budget = rootView.findViewById(R.id.budget);
        budgetText = rootView.findViewById(R.id.budgetText);
        calculation = (ImageView) rootView.findViewById(R.id.calculation);
        addButton = (Button) rootView.findViewById(R.id.addButton);
        calculation.setBackgroundResource(R.drawable.ic_budget_calculation_selected);

        if(getArguments() != null){
            dayNum = getArguments().getInt("index");
            budgetText.setVisibility(View.VISIBLE);
            budget.setVisibility(View.VISIBLE);
            calculation.setVisibility(View.VISIBLE);
            calculationText.setVisibility(View.VISIBLE);
            addButton.setVisibility(View.VISIBLE);

            day.setText("DAY " + dayNum);
            startYear = getArguments().getInt("startYear", 0);
            startDay = getArguments().getInt("startDay", 0);
            startMonth = getArguments().getInt("startMonth", 0)+1;
            budgetTotal = getArguments().getDouble("total", 0);
            period.setText(startYear + "." + startMonth + "." + startDay) ;
            periodInt = getArguments().getInt("period", 0);
            mainPosition = getArguments().getInt("mainPosition", 0);

        }

        openDatabase("database");

        //database.execSQL("drop table MainTable");
        //database.execSQL("drop table PlanTable");
        //database.execSQL("drop table BudgetTable");
        //database.execSQL("drop table WalletTable");
        //database.execSQL("drop table CostTable");
        //database.execSQL("drop table CategoryBudgetTable");

        if (database != null) {
            //_id 는 내부적으로 생성되는 아이디!
            Log.d("database", "PlanTable");
            String sql = "create table if not exists " + "PlanTable" + "(_id integer PRIMARY KEY autoincrement, date integer, year integer, month integer, day integer, type integer, place text, hour integer, min integer, memo text, transport integer, total_budget double, x double, y double, position integer, main_position integer)";
            database.execSQL(sql);

            sql = "create table if not exists " + "BudgetTable" + "(_id integer PRIMARY KEY autoincrement, date integer, type integer, budget double, memo text, plan_position integer, position integer, main_position integer)";
            database.execSQL(sql);

            sql = "create table if not exists " + "WalletTable" + "(_id integer PRIMARY KEY autoincrement, date integer, year integer, month integer, day integer, type integer, place text, hour integer, min integer, memo text, total_budget double, total_cost double, position integer, main_position integer)";
            database.execSQL(sql);

            sql = "create table if not exists " + "CostTable" + "(_id integer PRIMARY KEY autoincrement, wallet_position, date, type, cost, payment, position, place, main_position integer)";
            database.execSQL(sql);



        }
        for(int i=0; i<adapter.getCount(); i++){
            double totalBudget = getDayBudget(i, dayNum);
            String sql = "update PlanTable set total_budget = ? where date = "+dayNum+" and position = "+i+" and main_position = "+mainPosition;
            Object[] params = {totalBudget};
            database.execSQL(sql, params);
            sql = "update WalletTable set total_budget = ? where date = "+dayNum+" and position = "+i+" and main_position = "+mainPosition;
            Object[] params1 = {totalBudget};
            database.execSQL(sql, params1);
        }
        items.clear();
        settingList(dayNum);
        getDayTotal();
        listView = (ListView) rootView.findViewById(R.id.con);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlanInputActivity.class);
                startActivityForResult(intent, 101);
            }
        });

        calculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalExp();
                Intent intent = new Intent(getContext(), TravelEstimatedBudget.class);
                budgetRemainder = budgetTotal;
                intent.putExtra("total", budgetTotal);
                Log.d("estimate: ", budgetTotal+"");
                intent.putExtra("lodging", lodging);
                intent.putExtra("food", food);
                intent.putExtra("shopping", shopping);
                intent.putExtra("tourism", tourism);
                intent.putExtra("etc", etc);
                intent.putExtra("mainPosition", mainPosition);
                startActivityForResult(intent, 200);
            }
        });

        calculationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalExp();
                Intent intent = new Intent(getContext(), TravelEstimatedBudget.class);
                budgetRemainder = budgetTotal;
                intent.putExtra("total", budgetRemainder);
                Log.d("estimate: ", budgetTotal+"");
                intent.putExtra("lodging", lodging);
                intent.putExtra("food", food);
                intent.putExtra("shopping", shopping);
                intent.putExtra("tourism", tourism);
                intent.putExtra("etc", etc);
                intent.putExtra("mainPosition", mainPosition);
                startActivityForResult(intent, 200);
            }
        });

        placeSearch = (Button) rootView.findViewById(R.id.placeSearch);
        placeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlaceSearchActivity.class);

                startActivity(intent);
            }
        });

        withFriends = (Button) rootView.findViewById(R.id.withFriends);
        withFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WithFriendsActivity.class);
                startActivity(intent);
            }
        });

        oDialog = new DeleteDialog(getContext());
        // Inflate the layout for this fragment
        return rootView;
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

            LinearLayout i = view.findViewById(R.id.place_linear);
            i.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), PlanInputActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("time", item.getPlaceTime());
                    intent.putExtra("place", item.getPlaceName());
                    intent.putExtra("memo", item.getPlaceMemo());
                    intent.putExtra("type", item.getPlaceType());
                    intent.putExtra("x", item.getX());
                    intent.putExtra("y", item.getY());
                    startActivityForResult(intent, 102);
                }
            });


            ImageButton del = (ImageButton) view.findViewById(R.id.deleteButton);
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
                                String sql = "delete from PlanTable where position = " + position+" and main_position = "+mainPosition;
                                database.execSQL(sql);
                                sql = "delete from BudgetTable where plan_position =" + position+" and main_position = "+mainPosition;
                                selectData("PlanTable");
                            }
                        }
                    });
                }
            });

            transBudget = (TextView) view.findViewById(R.id.TransBudgetText);
            final ImageButton addT = (ImageButton) view.findViewById(R.id.transport_ic);
            final TextView transText = (TextView) view.findViewById(R.id.transportText);

            transText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tDialog = new TransportDialog(getContext());
                    tDialog.callFunction(item.getTransport());
                    tDialog.show();
                    tDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            int transport = tDialog.getTransport();
                            if(transport == 1){
                                addT.setBackgroundResource(R.drawable.ic_walk_24px);
                                transText.setText("Walk");
                            }

                            if(transport == 2){
                                addT.setBackgroundResource(R.drawable.ic_bus_24px);
                                transText.setText("Bus");
                            }

                            if(transport == 3){
                                addT.setBackgroundResource(R.drawable.ic_subway_24px);
                                transText.setText("Subway");
                            }

                            if(transport == 4){
                                addT.setBackgroundResource(R.drawable.ic_taxi_24px);
                                transText.setText("Taxi");
                            }

                            if(transport == 5){
                                addT.setBackgroundResource(R.drawable.ic_car_24px);
                                transText.setText("Car");
                            }
                            int tType=0;
                            switch(transport){
                                case 1:
                                    tType = 7;
                                    break;
                                case 2:
                                    tType = 8;
                                    break;
                                case 3:
                                    tType = 9;
                                    break;
                                case 4:
                                    tType = 10;
                                    break;
                                case 5:
                                    tType = 11;
                                    break;
                            }
                            item.setTransport(transport);
                            if(database != null){
                                String sql = "update PlanTable set transport = ? where date = "+dayNum+" and position = "+position+" and main_position = "+mainPosition;
                                Object[] params = {transport};
                                database.execSQL(sql, params);
                                sql = "update  BudgetTable set type = ?, budget = ? where date = "+dayNum+" and plan_position = "+position+" and position = "+1+" and main_position = "+mainPosition;
                                Object[] params1 = {tType, 0};
                                database.execSQL(sql, params1);
                                selectData("PlanTable");
                            }else {
                            }
                        }
                    });
                }
            });

            ImageView budgetImg = view.findViewById(R.id.transportBudget);
            TextView budgetTxt = view.findViewById(R.id.TransBudgetText);

            budgetImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), PlanBudget.class);
                    intent.putExtra("position", position);
                    intent.putExtra("transportB", items.get(position).getTransBudget());
                    intent.putExtra("transportT", items.get(position).getTransport());
                    intent.putExtra("type", items.get(position).getPlaceType());
                    intent.putExtra("memo", items.get(position).getPlaceMemo());
                    intent.putExtra("title", items.get(position).getPlaceName());
                    intent.putExtra("date", dayNum);
                    intent.putExtra("mainPosition", mainPosition);
                    startActivityForResult(intent, 103);
                }
            });

            budgetTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), PlanBudget.class);
                    intent.putExtra("position", position);
                    intent.putExtra("transportB", items.get(position).getTransBudget());
                    intent.putExtra("transportT", items.get(position).getTransport());
                    intent.putExtra("type", items.get(position).getPlaceType());
                    intent.putExtra("memo", items.get(position).getPlaceMemo());
                    intent.putExtra("title", items.get(position).getPlaceName());
                    intent.putExtra("date", dayNum);
                    intent.putExtra("mainPosition", mainPosition);
                    startActivityForResult(intent, 103);
                }
            });


            addT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tDialog = new TransportDialog(getContext());
                    tDialog.callFunction(item.getTransport());
                    tDialog.show();
                    tDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            int transport = tDialog.getTransport();
                            if(transport == 1){
                                addT.setBackgroundResource(R.drawable.ic_walk_24px);
                                transText.setText("Walk");
                            }

                            if(transport == 2){
                                addT.setBackgroundResource(R.drawable.ic_bus_24px);
                                transText.setText("Bus");
                            }

                            if(transport == 3){
                                addT.setBackgroundResource(R.drawable.ic_subway_24px);
                                transText.setText("Subway");
                            }

                            if(transport == 4){
                                addT.setBackgroundResource(R.drawable.ic_taxi_24px);
                                transText.setText("Taxi");
                            }

                            if(transport == 5){
                                addT.setBackgroundResource(R.drawable.ic_car_24px);
                                transText.setText("Car");
                            }
                            item.setTransport(transport);
                            int tType=0;
                            switch(transport){
                                case 1:
                                    tType = 7;
                                    break;
                                case 2:
                                    tType = 8;
                                    break;
                                case 3:
                                    tType = 9;
                                    break;
                                case 4:
                                    tType = 10;
                                    break;
                                case 5:
                                    tType = 11;
                                    break;
                            }
                            if(database != null){
                                String sql = "update PlanTable set transport = ? where date = "+dayNum+" and position = "+position+" and main_position = "+mainPosition;
                                Object[] params = {transport};
                                database.execSQL(sql, params);
                                sql = "update  BudgetTable set type = ?, budget = ? where date = "+dayNum+" and plan_position = "+position+" and position = "+1+" and main_position = "+mainPosition;
                                Object[] params1 = {tType, 0};
                                database.execSQL(sql, params1);
                                selectData("PlanTable");
                            }else {
                            }
                        }

                    });

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
                int hour = intent.getIntExtra("hour", 0);
                int min = intent.getIntExtra("min", 0);
                String ap;
                String sHour, sMin;
                if(hour > 12) {
                    ap = "PM";
                    hour -= 12;
                }
                else
                    ap = "AM";

                if(hour<10)
                    sHour = "0"+hour;
                else
                    sHour = String.valueOf(hour);

                if(min<10)
                    sMin = "0"+hour;
                else
                    sMin = String.valueOf(min);

                time = ap+ " "+sHour+":"+sMin;
                name = intent.getStringExtra("title");
                memo = intent.getStringExtra("memo");
                type = intent.getIntExtra("type", 1);
                switch(type){
                    case 1:
                        lodging += 1;
                        break;
                    case 2:
                        food += 1;
                        break;
                    case 3:
                        shopping += 1;
                        break;
                    case 4:
                        tourism += 1;
                        break;
                    case 6:
                        etc += 1;
                        break;
                }
                Toast.makeText(getContext(), "lodging="+lodging+"\nfood="+food+"\nshopping="+shopping+"\ntourism="+tourism+"\netc="+etc, Toast.LENGTH_LONG).show();
                x = intent.getDoubleExtra("x", 0);
                y = intent.getDoubleExtra("y", 0);
                //transport =1;

                adapter.addItem(new PlanInitialSubItem(time, name, memo, 1, type, x, y, 0));
                adapter.notifyDataSetChanged();
                int position = adapter.getCount()-1;

                if(database != null){
                    String sql = "insert into PlanTable(date, year, month, day, type, place, hour, min, memo, transport, total_budget, x, y, position, main_position) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    Object[] params = {dayNum, startYear, startMonth, startDay, type, name, hour, min, memo, 1, 0.0, x, y, position, mainPosition};
                    database.execSQL(sql, params);
                    sql = "insert into WalletTable(date, year, month, day, type, place, hour, min, memo, total_budget, total_cost, position, main_position) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    Object[] params3 = {dayNum, startYear, startMonth, startDay, type, name, hour, min, memo, 0.0, 0.0, position, mainPosition};
                    database.execSQL(sql, params3);
                    sql = "insert into BudgetTable(plan_position, date, type, budget, position, main_position) values(?, ?, ?, ?, ?, ?)";
                    Object[] params1 = {position, dayNum, type, 0.0, 0, mainPosition};
                    database.execSQL(sql, params1);
                    sql = "insert into BudgetTable(plan_position, date, type, budget, position, main_position) values(?, ?, ?, ?, ?, ?)";
                    Object[] params2 = {position, dayNum, 7, 0.0, 1, mainPosition};
                    database.execSQL(sql, params2);
                    selectData("PlanTable");
                    //selectData("BudgetTable");
                }else {
                }
            }
        }

        if(requestCode == 102){
            if(intent != null){
                int position = intent.getIntExtra("position", 0);
                int hour = intent.getIntExtra("hour", 0);
                int min = intent.getIntExtra("min", 0);
                String ap;
                String sHour, sMin;
                if(hour > 12) {
                    ap = "PM";
                    hour -= 12;
                }
                else
                    ap = "AM";

                if(hour<10)
                    sHour = "0"+hour;
                else
                    sHour = String.valueOf(hour);

                if(min<10)
                    sMin = "0"+hour;
                else
                    sMin = String.valueOf(min);

                time = ap+ " "+sHour+":"+sMin;
                name = intent.getStringExtra("title");
                memo = intent.getStringExtra("memo");
                type = intent.getIntExtra("type", 1);

                switch(items.get(position).getPlaceType()){
                    case 1:
                        lodging -= 1;
                        break;
                    case 2:
                        food -= 1;
                        break;
                    case 3:
                        shopping -= 1;
                        break;
                    case 4:
                        tourism -= 1;
                        break;
                    case 5:
                        etc -= 1;
                        break;
                }

                switch(type){
                    case 1:
                        lodging += 1;
                        break;
                    case 2:
                        food += 1;
                        break;
                    case 3:
                        shopping += 1;
                        break;
                    case 4:
                        tourism += 1;
                        break;
                    case 5:
                        etc += 1;
                        break;
                }
                Toast.makeText(getContext(), "lodging="+lodging+"\nfood="+food+"\nshopping="+shopping+"\ntourism="+tourism+"\netc="+etc, Toast.LENGTH_LONG).show();
                x = intent.getDoubleExtra("x", 0);
                y = intent.getDoubleExtra("y", 0);
                //transport =1;
                items.get(position).setPlaceTime(time);
                items.get(position).setPlaceName(name);
                items.get(position).setPlaceMemo(memo);
                items.get(position).setPlaceType(type);
                items.get(position).setX(x);
                items.get(position).setY(y);

                if(database != null){
                    String sql = "update PlanTable set type = ?, place = ?, hour = ?, min = ?, memo = ?, x = ?, y = ? where date = "+dayNum+" and position = "+position+" and main_position = "+mainPosition;
                    Object[] params = {type, name, hour, min, memo, x, y};
                    database.execSQL(sql, params);
                    sql = "update WalletTable set type = ?, place = ?, hour = ?, min =?, memo = ? where date = "+dayNum+" and position = "+position+" and main_position = "+mainPosition;
                    Object[] params2 = {type, name, hour, min, memo};
                    database.execSQL(sql, params2);
                    sql = "update  BudgetTable set type = ? where date = "+dayNum+" and plan_position = "+position+" and position = "+0+" and main_position = "+mainPosition;
                    Object[] params1 = {type};
                    database.execSQL(sql, params1);
                    selectData("PlanTable");
                }else {
                }

                adapter.notifyDataSetChanged();
            }
        }

        if(requestCode == 103){
            if(intent != null){
                lodging += intent.getIntExtra("lodging", 0);
                food += intent.getIntExtra("food", 0);
                shopping += intent.getIntExtra("shopping", 0);
                tourism += intent.getIntExtra("tourism", 0);
                etc += intent.getIntExtra("etc", 0);
                lodgingBudget += intent.getDoubleExtra("lodgingBudget", 0);
                foodBudget += intent.getDoubleExtra("foodBudget", 0);
                shoppingBudget += intent.getDoubleExtra("shppingBudget", 0);
                tourismBudget += intent.getDoubleExtra("tourismBudget", 0);
                etcBudget += intent.getDoubleExtra("etcBudget", 0);
                for(int i=0; i<adapter.getCount(); i++){
                    double totalBudget = getDayBudget(i, dayNum);
                    String sql = "update PlanTable set total_budget = ? where date = "+dayNum+" and position = "+i+" and main_position = "+mainPosition;
                    Object[] params = {totalBudget};
                    database.execSQL(sql, params);
                    sql = "update WalletTable set total_budget = ? where date = "+dayNum+" and position = "+i+" and main_position = "+mainPosition;
                    Object[] params1 = {totalBudget};
                    database.execSQL(sql, params1);
                }
                items.clear();
                settingList(dayNum);
                getDayTotal();

                Log.d("total", "lodging="+lodging+"\nfood="+food+"\nshopping="+shopping+"\ntourism="+tourism+"\netc="+etc);
            }
        }

        if(requestCode == 200){
            if(intent != null){
                for(int i=0; i<adapter.getCount(); i++){
                    for(int j=1; j<= periodInt ; j++){
                        double totalBudget = getDayBudget(i, dayNum);
                        String sql = "update PlanTable set total_budget = ? where date = "+j+" and position = "+i+" and main_position = "+mainPosition;
                        Object[] params = {totalBudget};
                        database.execSQL(sql, params);

                        sql = "update WalletTable set total_budget = ? where date = "+j+" and position = "+i+" and main_position = "+mainPosition;
                        Object[] params1 = {totalBudget};
                        database.execSQL(sql, params1);
                    }
                }
                items.clear();
                settingList(dayNum);
                getDayTotal();
            }
        }
    }

    public void getDayTotal(){

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

    public double getDayBudget(int planPosition, int dayNum){
        double b = 0;
        String sql = "select budget from BudgetTable where date = "+dayNum+" and plan_position = "+planPosition+" and main_position = "+mainPosition;
        Cursor cursor2 = database.rawQuery(sql, null);
        for(int j=0; j<cursor2.getCount(); j++){
            cursor2.moveToNext();
            b += cursor2.getDouble(0);

            Log.d("pass", String.valueOf(b));
        }
        cursor2.close();

        return b;
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
                /*String sql = "create table if not exists " + "PlanTable" + "(_id integer PRIMARY KEY autoincrement, date integer, year integer, month integer, day integer, type integer, place text, hour integer, min integer, memo text, transport integer, total_budget double, x double, y double, position integer)";
                db.execSQL(sql);

                sql = "create table if not exists " + "BudgetTable" + "(_id integer PRIMARY KEY autoincrement, date integer, type integer, budget double, memo text, plan_position integer, position integer)";
                db.execSQL(sql);*/

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
                    /*String sql = "create table if not exists " + "PlanTable" + "(_id integer PRIMARY KEY autoincrement, date integer, year integer, month integer, day integer, type integer, place text, hour integer, min integer, memo text, transport integer, total_budget double, x double, y double, position integer)";
                    db.execSQL(sql);

                    //_id 는 내부적으로 생성되는 아이디!
                    sql = "create table if not exists " + "BudgetTable" + "(_id integer PRIMARY KEY autoincrement, date integer, type integer, budget double, memo text, plan_position integer, position integer)";
                    db.execSQL(sql);*/

                    //println("테이블 새로 생성됨.");
                } else {
                    //println("먼저 데이터베이스를 오픈하세요.");
                }
            }
        }
    }


    public void selectData(String tableName){
        //println("selectData() 호출됨.");

        if(database != null){
            ///String sql = "select date, year, month, day, type, place, hour, min, memo, transport, total_budget from "+ tableName;
            String sql = "select date, year, month, day, type, place, hour, min, memo, transport, total_budget, position from "+ tableName;

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
                int position = cursor.getInt(11);

                Log.d("database", "#"+i+"->"+date+", "+year+", "+month+", "+day+", "+type+", "+place+", "+hour+", "+min+", "+memo+", "+transport+", "+total_budget+", "+position);
            }

            cursor.close();
        }
    }

    public void totalExp(){
        double[] xList = new double[adapter.getCount()];
        double[] yList = new double[adapter.getCount()];
        int[] transportList = new int[adapter.getCount()];
        for(int i=0; i<adapter.getCount(); i++){
            xList[i] = items.get(i).getX();
            yList[i] = items.get(i).getY();
            transportList[i] = items.get(i).getTransport();
        }

        for(int j=0; j<adapter.getCount()-1; j++){
            returnTransportExp(j, xList[j], yList[j], xList[j+1], yList[j+1], transportList[j]);
        }
    }

    public void returnTransportExp(int j, double SX, double SY, double EX, double EY, int transport){
        Log.d("x, y : %s", String.valueOf(SX) + String.valueOf(EX));
        int searchType;
        final double[] transportExp = {0};
        final int x=j;
        if(transport==2 || transport == 3) {
            if (transport == 2)
                searchType = 2;
            else
                searchType = 1;

            Log.d("searchType : ", String.valueOf(searchType));

            // 싱글톤 생성, Key 값을 활용하여 객체 생성
            ODsayService odsayService = ODsayService.init(getContext(), "TNRXyW/xqRXnX/zMI8tA2fbn4N+HPUuaIySAow33Qvs");
            // 서버 연결 제한 시간(단위(초), default : 5초)
            odsayService.setReadTimeout(5000);
            // 데이터 획득 제한 시간(단위(초), default : 5초)
            odsayService.setConnectionTimeout(5000);
            // 콜백 함수 구현
            // API 호출

            OnResultCallbackListener onResultCallbackListener = new OnResultCallbackListener() {
                // 호출 성공 시 실행
                @Override
                public void onSuccess(ODsayData odsayData, API api) {

                    try {
                        // API Value에 API 호출 메소드 명 입력.
                        if (api == API.SEARCH_PUB_TRANS_PATH) {
                            JSONArray path = odsayData.getJson().getJSONObject("result").getJSONArray("path");
                            JSONObject info = path.getJSONObject(0).getJSONObject("info");
                            transportExp[0] = info.getInt("payment");
                            Log.d("json", String.valueOf(info));
                            Log.d("payment : %s", String.valueOf(info.getInt("payment")));
                            items.get(x).setTransBudget(transportExp[0]);
                            if(database != null){
                                String sql = "update BudgetTable set budget = ? where date = "+dayNum +" and plan_position = "+x+" and position = 1"+" and main_position = "+mainPosition;
                                Object[] object = {transportExp[0]};
                                database.execSQL(sql, object);
                            }
                            //adapter.notifyDataSetChanged();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                // 호출 실패 시 실행
                @Override
                public void onError(int i, String s, API api) {
                    if (api == API.SEARCH_PUB_TRANS_PATH) {
                    }
                }
            };

            odsayService.requestSearchPubTransPath(String.valueOf(SX), String.valueOf(SY), String.valueOf(EX), String.valueOf(EY), "0", "0", String.valueOf(searchType), onResultCallbackListener);
            //객체 초기화 -> (출발지 x좌표, 출발지 y좌표, 도착지 x좌표, 도착지 y좌표, opt가 뭐였지..., 정렬 기준, 교통 수단)
            Log.d("odSay : ", "odsayService.requestSearchPubTransPath");

        }

        else if(transport == 4){
            transportExp[0] = taxiFare(SX, SY, EX, EY);
            transportTotal += transportExp[0];
            items.get(x).setTransBudget(transportExp[0]);
            if(database != null){
                String sql = "update BudgetTable set budget = ? where date = "+dayNum +" and plan_position = "+x+" and position = 1"+" and main_position = "+mainPosition;
                Object[] object = {transportExp[0]};
                database.execSQL(sql, object);
            }
            //adapter.notifyDataSetChanged();
        }

        else {
            transportExp[0] = 0;
            transportTotal += transportExp[0];
            items.get(x).setTransBudget(transportExp[0]);
            //adapter.notifyDataSetChanged();
        }
    }

    private double taxiFare(double SX, double SY, double EX, double EY) {
        URL url= null;
        String str, receiveMsg="";
        InputStream is = null;
        JSONArray jsonArray;
        double taxiFare=0;


        try {
            String serviceKey = "l7xx45199929f5df446a85d295e1e5eebe6a";
            //startX=126.98217734415019&startY=37.56468648536046&
            StringBuilder urlBuilder = new StringBuilder("https://apis.openapi.sk.com/tmap/routes"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("version", "UTF-8") + "=" + URLEncoder.encode("2", "UTF-8")); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("appKey", "UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8")); /*Tmap에서 발급받은 인증키*/
            urlBuilder.append("&" + URLEncoder.encode("endX", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(EX), "UTF-8")); /*목적지 X좌표*/
            urlBuilder.append("&" + URLEncoder.encode("endY", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(EY), "UTF-8")); /*목적지 Y좌표*/
            urlBuilder.append("&" + URLEncoder.encode("startX", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(SX), "UTF-8")); /*출발지 X좌표*/
            urlBuilder.append("&" + URLEncoder.encode("startY", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(SY), "UTF-8")); /*출발지 Y좌표*/

            url = new URL(urlBuilder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            is = url.openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuffer buffer = new StringBuffer();
            while ((str = rd.readLine()) != null) {
                buffer.append(str);
            }
            receiveMsg = buffer.toString();
            Log.i("receiveMsg : ", receiveMsg);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("receiveMsg : ", "error");
        }

        try {
            JSONObject json = new JSONObject(receiveMsg);
            JSONObject features = json.getJSONArray("features").getJSONObject(0).getJSONObject("properties");
            taxiFare = features.getInt("taxiFare");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return taxiFare;
    }

    public  void settingList(int dayNum){
        if(database != null){
            String sql = "select date, year, month, day, type, place, hour, min, memo, transport, total_budget, x, y from "+ "PlanTable"+" where date = "+dayNum+" and main_position = "+mainPosition;
            Log.d("mainPosition", mainPosition+"");
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
                if(hour > 12) {
                    ap = "PM";
                    hour -= 12;
                }
                else
                    ap = "AM";

                if(hour<10)
                    sHour = "0"+hour;
                else
                    sHour = String.valueOf(hour);

                if(min<10)
                    sMin = "0"+hour;
                else
                    sMin = String.valueOf(min);

                time = ap+ " "+sHour+":"+sMin;

                Log.d("database", "#"+i+"->"+date+", "+year+", "+month+", "+day+", "+type+", "+place+", "+hour+", "+min+", "+memo+", "+transport+", "+total_budget);
                adapter.addItem(new PlanInitialSubItem(time, place, memo, transport, type, x, y, total_budget));
                adapter.notifyDataSetChanged();
            }

            cursor.close();
        }
    }

}
