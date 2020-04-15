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

import java.util.ArrayList;

public class WalletCost extends AppCompatActivity {
    ArrayList<WalletCostItem> items = new ArrayList<WalletCostItem>();
    WalletCostAdapter adapter = new WalletCostAdapter();

    ListView listView;
    ImageButton back;
    Button add;
    String place;
    TextView title;

    int walletPosition;

    SQLiteDatabase database;
    int date, mainPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_wallet_cost);

        title = findViewById(R.id.textView);

        Intent intent = getIntent();
        mainPosition = intent.getIntExtra("mainPosition", 0);
        walletPosition = intent.getIntExtra("wallet_position", 0);
        date = intent.getIntExtra("date", 0);
        place = intent.getStringExtra("place");

        title.setText(place);

        openDatabase("database");

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        settingList();
        //adapter.addItem(new WalletCostItem(1, 1, 1200));
        //adapter.addItem(new WalletCostItem(5, 1, 800));

        back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("position", walletPosition);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        add = findViewById(R.id.btn_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WalletInput.class);
                startActivityForResult(intent, 105);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("position", walletPosition);
        setResult(RESULT_OK, intent);
        finish();
    }

    class WalletCostAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(WalletCostItem item) {
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
            WalletCostItemView view = new WalletCostItemView(getApplicationContext());
            final WalletCostItem item = items.get(position);
            if(item.getCategory()==1) {
                view.setCategory(R.drawable.ic_lodging_24px);
                view.setType("Lodging");
            }
            else if(item.getCategory()==2) {
                view.setCategory(R.drawable.ic_food_24px);
                view.setType("Food");
            }
            else if(item.getCategory()==3) {
                view.setCategory(R.drawable.ic_shopping_24px);
                view.setType("Shopping");
            }
            else if(item.getCategory()==4) {
                view.setCategory(R.drawable.ic_tourism_24px);
                view.setType("Tourism");
            }
            else if(item.getCategory()==5) {
                view.setCategory(R.drawable.ic_bus_24px);
                view.setType("Transport");
            }
            else if(item.getCategory()==6) {
                view.setCategory(R.drawable.ic_etc_24px);
                view.setType("Etc");
            }

            if(item.getPayment()==2){
                view.setPayment(R.drawable.ic_card_24px);
            }
            else if(item.getPayment()==1){
                view.setPayment(R.drawable.ic_cash_24px);
            }
            view.setBudget(item.getBudget());

            ImageButton del = view.findViewById(R.id.del);
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    items.remove(position);
                    adapter.notifyDataSetChanged();
                }
            });

            return view;
        }
    }

    //sign in
    public void onClick(View view1){
        Intent intent = new Intent(this, WalletInput.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 105) {
            if (intent != null) {
                String cost = intent.getStringExtra("cost");
                int type = intent.getIntExtra("type", 0);
                int payment = intent.getIntExtra("payment", 0);
                adapter.addItem(new WalletCostItem(type, payment, Double.parseDouble(cost)));
                adapter.notifyDataSetChanged();
                int position = adapter.getCount() - 1;

                String sql = "insert into  CostTable(wallet_position, date, type, cost, payment, position, place, main_position) values(?, ?, ?, ?, ?, ?, ?, ?)";
                Object[] params1 = {walletPosition, date, type, Double.parseDouble(cost), payment, position, place, mainPosition};
                database.execSQL(sql, params1);
            }
        }
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
            String sql = "select type, cost, payment from "+ "CostTable"+" where date = "+date+" and wallet_position = "+walletPosition+" and main_position = "+mainPosition;
            Cursor cursor = database.rawQuery(sql, null);
            //println("조회된 데이터 개수: "+cursor.getCount());

            for(int i=0; i<cursor.getCount(); i++){
                cursor.moveToNext();
                int type = cursor.getInt(0);
                double cost = cursor.getDouble(1);
                int payment = cursor.getInt(2);
                //int month = cursor.getInt(2);

                Log.d("database", "#"+i+"->"+type+", "+cost+", "+payment);
                adapter.addItem(new WalletCostItem(type, payment, (int)cost));
            }

            cursor.close();
        }
    }

}
