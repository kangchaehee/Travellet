package com.example.example;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlanBudget extends AppCompatActivity {

    ArrayList<PlanBudgetItem> items = new ArrayList<PlanBudgetItem>();
    PlanBudgetAdapter adapter = new PlanBudgetAdapter();

    ListView listView;
    ImageButton back;
    Button add;

    int type, tType;
    double tBudget=0, lodgingB=0, foodB=0, tourismB=0, shoppingB=0, etcB=0, budget=0;
    int lodging=0, food=0, shopping=0, tourism=0, etc=0;
    int date, position, planPosition, mainPosition;

    String memo=" ", title;

    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_plan_budget);

        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
        tType = intent.getIntExtra("transportT", 0);

        switch(tType){
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
        tBudget = intent.getDoubleExtra("transportB", 0);
        memo = intent.getStringExtra("memo");
        title = intent.getStringExtra("title");
        date = intent.getIntExtra("date", 0);
        planPosition = intent.getIntExtra("position", 0);
        mainPosition = intent.getIntExtra("mainPosition", 0);

        TextView memoTxt = findViewById(R.id.textView2);
        memoTxt.setText(memo);
        TextView titleTxt = findViewById(R.id.textView);
        titleTxt.setText(title);

        openDatabase("database");

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        settingList();

        back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToBack();
            }
        });

        add = findViewById(R.id.btn_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BudgetInput.class);
                startActivityForResult(intent, 105);
            }
        });
    }

    class PlanBudgetAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(PlanBudgetItem item) {
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
            PlanBudgetItemView view = new PlanBudgetItemView(getApplicationContext());
            final PlanBudgetItem item = items.get(position);
            if(item.getCategory()==1) {
                view.setCategory(R.drawable.ic_lodging_24px);
                view.setType("Lodging");
                lodgingB += item.getBudget();
            }
            else if(item.getCategory()==2) {
                view.setCategory(R.drawable.ic_food_24px);
                view.setType("Food");
                foodB += item.getBudget();
            }
            else if(item.getCategory()==3) {
                view.setCategory(R.drawable.ic_shopping_24px);
                view.setType("Shopping");
                shoppingB += item.getBudget();
            }
            else if(item.getCategory()==4) {
                view.setCategory(R.drawable.ic_tourism_24px);
                view.setType("Tourism");
                tourismB += item.getBudget();
            }
            else if(item.getCategory()==7) {
                view.setCategory(R.drawable.ic_walk_24px);
                view.setType("Transport");
                tBudget +=item.getBudget();
            }
            else if(item.getCategory()==8) {
                view.setCategory(R.drawable.ic_bus_24px);
                view.setType("Transport");
                tBudget +=item.getBudget();
            }
            else if(item.getCategory()==9) {
                view.setCategory(R.drawable.ic_subway_24px);
                view.setType("Transport");
                tBudget +=item.getBudget();
            }
            else if(item.getCategory()==10) {
                view.setCategory(R.drawable.ic_taxi_24px);
                view.setType("Transport");
                tBudget +=item.getBudget();
            }
            else if(item.getCategory()==11) {
                view.setCategory(R.drawable.ic_car_24px);
                view.setType("Transport");
                tBudget +=item.getBudget();
            }
            else if(item.getCategory()==6) {
                view.setCategory(R.drawable.ic_etc_24px);
                view.setType("Etc");
                etcB += item.getBudget();
            }
            view.setBudget(item.getBudget());
            budget += item.getBudget();

            ImageButton del = view.findViewById(R.id.del);
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    items.remove(position);
                    adapter.notifyDataSetChanged();
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), BudgetInput.class);
                    intent.putExtra("position", position);
                    intent.putExtra("budget", item.getBudget());
                    intent.putExtra("type", item.getCategory());
                    startActivityForResult(intent, 106);
                }
            });

            return view;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == 105){
            if(intent != null){
                String memo = intent.getStringExtra("memo");
                String budget = intent.getStringExtra("budget");
                int type = intent.getIntExtra("type", 0);
                if(Double.parseDouble(budget) == 0.0){
                    switch(type){
                        case 1:
                            lodging +=1;
                            break;
                        case 2:
                            food +=1;
                            break;
                        case 3:
                            shopping+=1;
                            break;
                        case 4:
                            tourism +=1;
                            break;
                        case 6:
                            etc+=1;
                            break;
                    }
                }

                adapter.addItem(new PlanBudgetItem(type, Double.parseDouble(budget)));
                adapter.notifyDataSetChanged();
                int position = adapter.getCount()-1;

                String sql = "insert into  BudgetTable(plan_position, date, type, budget, position, main_position) values(?, ?, ?, ?, ?, ?)";
                Object[] params1 = {planPosition, date, type, Double.parseDouble(budget), position, mainPosition};
                database.execSQL(sql, params1);
            }
        }
        if(requestCode == 106){
            if(intent != null){
                String memo = intent.getStringExtra("memo");
                String budget = intent.getStringExtra("budget");
                int type = intent.getIntExtra("type", 0);
                //Toast.makeText(getApplicationContext(), String.valueOf(Double.parseDouble(budget)>0), Toast.LENGTH_LONG).show();
                if(Double.parseDouble(budget)>0){
                    switch(type){
                        case 1:
                            lodging -=1;
                            break;
                        case 2:
                            food -=1;
                            break;
                        case 3:
                            shopping-=1;
                            break;
                        case 4:
                            tourism -=1;
                            break;
                        case 6:
                            etc-=1;
                            break;
                    }
                }
                int position = intent.getIntExtra("position", 0);
                items.get(position).setBudget(Double.parseDouble(budget));
                items.get(position).setCategory(type);
                //budget += items.get(position).getBudget();
                adapter.notifyDataSetChanged();

                String sql = "update  BudgetTable set type = ?, budget = ? where date = "+date+" and plan_position = "+planPosition+" and position = "+position+" and main_position = "+mainPosition;
                Object[] params1 = {type, Double.parseDouble(budget)};
                database.execSQL(sql, params1);
            }
        }
    }

    @Override
    public void onBackPressed() {
        returnToBack();
    }

    public void returnToBack(){
        Intent intent = new Intent();
        intent.putExtra("lodging", lodging);
        intent.putExtra("food", food);
        intent.putExtra("shopping", shopping);
        intent.putExtra("tourism", tourism);
        intent.putExtra("etc", etc);
        intent.putExtra("lodgingBudget", lodgingB);
        intent.putExtra("foodBudget", foodB);
        intent.putExtra("shoppingBudget", shoppingB);
        intent.putExtra("tourismBudget", tourismB);
        intent.putExtra("etcBudget", etcB);
        Log.d("total1", "lodging="+lodging+"\nfood="+food+"\nshopping="+shopping+"\ntourism="+tourism+"\netc="+etc);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void openDatabase(String databaseName){
        DatabaseHelper helper = new DatabaseHelper(getApplicationContext(), databaseName, null, 4);
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

                sql = "create table if not exists " + "BudgetTable" + "(_id integer PRIMARY KEY autoincrement, date integer, plan_id integer, type integer, budget double, memo text)";
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
                    sql = "create table if not exists " + "BudgetTable" + "(_id integer PRIMARY KEY autoincrement, date integer, plan_id integer, type integer, budget double, memo text)";
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
            String sql = "select type, budget, plan_position from "+ "BudgetTable"+" where date = "+date+" and plan_position = "+planPosition+" and main_position = "+mainPosition;
            Cursor cursor = database.rawQuery(sql, null);
            //println("조회된 데이터 개수: "+cursor.getCount());

            for(int i=0; i<cursor.getCount(); i++){
                cursor.moveToNext();
                int type = cursor.getInt(0);
                double budget = cursor.getDouble(1);
                int plan_position = cursor.getInt(2);
                //int month = cursor.getInt(2);

                Log.d("database", "#"+i+"->"+type+", "+budget+", "+plan_position);
                adapter.addItem(new PlanBudgetItem(type, budget));
            }

            cursor.close();
        }
    }
}
