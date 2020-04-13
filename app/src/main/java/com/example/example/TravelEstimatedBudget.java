package com.example.example;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class TravelEstimatedBudget extends AppCompatActivity {
    int lodging=0, food=0, tourism=0, shopping=0, transport=0, etc=0;
    double total, total2, lodgingB, foodB, leisureB, shoppingB, transportB, etcB;

    TextView lodgingPer, foodPer, leisurePer, shoppingPer, transportPer, etcPer;
    TextView lodgingBudget, foodBudget, leisureBudget, shoppingBudget, transportBudget, etcBudget, totalBudget;
    SeekBar lodgingBar, foodBar, leisureBar, shoppingBar, transportBar, etcBar;
    LinearLayout lodgingLin, foodLin, tourLin, shopLin, etcLin;

    Intent intent2;

    SQLiteDatabase database;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_travel_estimated_budget);

        lodgingPer = findViewById(R.id.lodgingPer);
        foodPer = findViewById(R.id.foodPer);
        leisurePer = findViewById(R.id.leisurePer);
        shoppingPer = findViewById(R.id.shoppingPer);
        //transportPer = findViewById(R.id.transportPer);
        etcPer = findViewById(R.id.etcPer);

        lodgingBar = findViewById(R.id.progress1);
        foodBar = findViewById(R.id.progress2);
        leisureBar = findViewById(R.id.progress3);
        shoppingBar = findViewById(R.id.progress4);
        //transportBar = findViewById(R.id.progress5);
        etcBar = findViewById(R.id.progress6);

        lodgingBudget = findViewById(R.id.lodgingBudget);
        foodBudget = findViewById(R.id.foodBudget);
        leisureBudget = findViewById(R.id.leisureBudget);
        shoppingBudget = findViewById(R.id.shoppingBudget);
        //transportBudget = findViewById(R.id.transportBudget);
        etcBudget = findViewById(R.id.etcBudget);
        totalBudget = findViewById(R.id.money);

        lodgingLin = findViewById(R.id.lodging_lin);
        foodLin = findViewById(R.id.food_lin);
        tourLin = findViewById(R.id.tourism_lin);
        shopLin = findViewById(R.id.shopping_lin);
        etcLin = findViewById(R.id.etc_lin);

        //토탈에서 남은 금액이랑 금액 설정 안한거 몇갠지
        Intent intent = getIntent();
        total = intent.getDoubleExtra("total", 0);
        total2 = total;
        totalBudget.setText(String.valueOf(total));

        openDatabase("database");
        if (database != null) {
            String sql = "create table if not exists " + "CategoryBudgetTable" + "(_id integer PRIMARY KEY autoincrement, category integer, budget double)";
            database.execSQL(sql);
            sql = "select category from "+ "CategoryBudgetTable";
            Cursor cursor = database.rawQuery(sql, null);
            if(cursor.getCount() != 6){
                sql = "insert into CategoryBudgetTable(category, budget) values(?, ?)";
                Object[] params1 = {1, 0.0};
                Object[] params2 = {2, 0.0};
                Object[] params3 = {3, 0.0};
                Object[] params4 = {4, 0.0};
                Object[] params5 = {5, 0.0};
                Object[] params6 = {6, 0.0};
                database.execSQL(sql, params1);
                database.execSQL(sql, params2);
                database.execSQL(sql, params3);
                database.execSQL(sql, params4);
                database.execSQL(sql, params5);
                database.execSQL(sql, params6);
            }

            String sql1 = "select type, budget from BudgetTable";
            Cursor cursor2 = database.rawQuery(sql1, null);
            for(int i=0; i<cursor2.getCount(); i++){
                cursor2.moveToNext();
                int t = cursor2.getInt(0);
                double budget = cursor2.getDouble(1);
                switch (t){
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
                    default:
                        break;
                }

                Log.d("database", "#"+i+"->"+t+", "+budget);
            }

            cursor.close();
            cursor2.close();
        }

        if(lodging == 0)
            lodgingLin.setVisibility(View.GONE);
        //food = intent.getIntExtra("food", 0);
        if(food == 0)
            foodLin.setVisibility(View.GONE);
        //shopping = intent.getIntExtra("shopping", 0);
        if(shopping == 0)
            shopLin.setVisibility(View.GONE);
        //tourism = intent.getIntExtra("tourism", 0);
        if(tourism == 0)
            tourLin.setVisibility(View.GONE);
        //etc = intent.getIntExtra("etc", 0);
        if(etc == 0)
            etcLin.setVisibility(View.GONE);

        //카테고리별 예산 max=total
        lodgingBar.setMax((int)total/200);
        foodBar.setMax((int)total/200);
        leisureBar.setMax((int)total/200);
        shoppingBar.setMax((int)total/200);
        etcBar.setMax((int)total/200);

        //각 카테고리 예산 텍스트 조정
        lodgingBudget.setText("$ "+lodgingB);
        foodBudget.setText("$ "+foodB);
        leisureBudget.setText("$ "+leisureB);
        shoppingBudget.setText("$ "+shoppingB);
        //transportBudget.setText("$ "+transportB);
        etcBudget.setText("$ "+etcB);

        //카테고리 비율에 맞게 텍스트랑 프로그레스 바 조정
        lodgingBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //progress가 바뀐 선택 값
                total2 = total-foodB-shoppingB - leisureB - etc;
                totalBudget.setText(String.valueOf((int)total2-progress*200));
                int percent= (progress*200)*100/(int)total;
                lodgingPer.setText(String.valueOf((percent))+"%");
                lodgingBudget.setText("₩ "+String.valueOf(progress*200));
                lodgingB = progress*200;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //total2 = total2-lodgingB;
            }
        });

        foodBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //progress가 바뀐 선택 값
                total2 = total-lodgingB-shoppingB-leisureB - etc;
                totalBudget.setText(String.valueOf((int)total2-progress*200));
                int percent= (progress*200)*100/(int)total;
                foodPer.setText(String.valueOf(percent)+"%");
                foodBudget.setText("₩ "+String.valueOf(progress*200));
                foodB = progress*200;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //total2 = total2 - foodB;
            }
        });

        shoppingBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //progress가 바뀐 선택 값
                total2 = total-lodgingB-shoppingB-leisureB - etc;
                totalBudget.setText(String.valueOf((int)total2-progress*200));
                int percent= (progress*200)*100/(int)total;
                shoppingPer.setText(String.valueOf(percent)+"%");
                shoppingBudget.setText("₩ "+String.valueOf(progress*200));
                shoppingB = progress*200;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        leisureBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //progress가 바뀐 선택 값
                total2 = total-lodgingB-shoppingB-leisureB - etc;
                totalBudget.setText(String.valueOf((int)total2-progress*200));
                int percent= (progress*200)*100/(int)total;
                leisurePer.setText(String.valueOf(percent)+"%");
                leisureBudget.setText("₩ "+String.valueOf(progress*200));
                leisureB = progress*200;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        etcBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //progress가 바뀐 선택 값
                total2 = total-lodgingB-shoppingB-leisureB - etc;
                totalBudget.setText(String.valueOf((int)total2-progress*200));
                int percent= (progress*200)*100/(int)total;
                etcPer.setText(String.valueOf(percent)+"%");
                etcBudget.setText("₩ "+String.valueOf(progress*200));
                etcB = progress*200;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        //최종 금액, 타입별 예산
        intent2 = getIntent();
        /*total = intent2.getIntExtra("budget", 0);
        lodgingB = total*lodging/100;
        foodB = total*food/100;
        leisureB = total*leisure/100;
        shoppingB = total*shopping/100;
        //transportB = total*transport/100;*/
        //etcB = total*etc/100;
    }

    // travel budget set
    public void onButtonClick(View view1){
        finish();
    }

    // plan initial
    public void onClick(View view){
        Intent intent = new Intent(this, Navigation.class);

        if(database != null){
            String sql = "update CategoryBudgetTable set budget = ? where category = 1";
            Object[] params1= {lodgingB};
            database.execSQL(sql, params1);
            sql = "update CategoryBudgetTable set budget = ? where category = 2";
            Object[] params2 = {foodB};
            database.execSQL(sql, params2);
            sql = "update CategoryBudgetTable set budget = ? where category = 3";
            Object[] params3 = {shoppingB};
            database.execSQL(sql, params3);
            sql = "update CategoryBudgetTable set budget = ? where category = 4";
            Object[] params4 = {leisureB};
            database.execSQL(sql, params4);
            sql = "update CategoryBudgetTable set budget = ? where category = 5";
            Object[] params5 = {transportB};
            database.execSQL(sql, params2);
            sql = "update CategoryBudgetTable set budget = ? where category = 6";
            Object[] params6 = {etcB};
            database.execSQL(sql, params2);
        }
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

}
