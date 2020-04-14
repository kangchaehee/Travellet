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
    double total, total2, lodgingB, foodB, leisureB, shoppingB, transportB, etcB, remain;
    int mainPosition;

    TextView lodgingPer, foodPer, leisurePer, shoppingPer, transportPer, etcPer;
    TextView lodgingBudget, foodBudget, leisureBudget, shoppingBudget, transportBudget, etcBudget, totalBudget;
    TextView lodgingText, foodText, shoppingText, tourismText, etcText;
    SeekBar lodgingBar, foodBar, leisureBar, shoppingBar, transportBar, etcBar;
    LinearLayout lodgingLin, foodLin, tourLin, shopLin, etcLin;

    Intent intent2;

    SQLiteDatabase database;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_travel_estimated_budget);

        lodgingText = findViewById(R.id.text1);
        foodText = findViewById(R.id.text2);
        shoppingText = findViewById(R.id.text3);
        tourismText = findViewById(R.id.text4);
        etcText = findViewById(R.id.text5);

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
        total = 0;
        remain = intent.getDoubleExtra("total", 0);
        Log.d("estimate remain", remain+"");
        total2 = remain;
        mainPosition = intent.getIntExtra("mainPosition", 0);

        openDatabase("database");
        if (database != null) {
            String sql = "create table if not exists " + "CategoryBudgetTable" + "(_id integer PRIMARY KEY autoincrement, category integer, budget double, main_position integer)";
            database.execSQL(sql);
            sql = "select category from "+ "CategoryBudgetTable where "+"main_position = "+mainPosition;
            Cursor cursor = database.rawQuery(sql, null);
            if(cursor.getCount() != 6){
                sql = "insert into CategoryBudgetTable(category, budget, main_position) values(?, ?, ?)";
                Object[] params1 = {1, 0.0, mainPosition};
                Object[] params2 = {2, 0.0, mainPosition};
                Object[] params3 = {3, 0.0, mainPosition};
                Object[] params4 = {4, 0.0, mainPosition};
                Object[] params5 = {5, 0.0, mainPosition};
                Object[] params6 = {6, 0.0, mainPosition};
                database.execSQL(sql, params1);
                database.execSQL(sql, params2);
                database.execSQL(sql, params3);
                database.execSQL(sql, params4);
                database.execSQL(sql, params5);
                database.execSQL(sql, params6);
            }

            String sql1 = "select type, budget from BudgetTable"+" where main_position = "+mainPosition;
            Cursor cursor2 = database.rawQuery(sql1, null);
            for(int i=0; i<cursor2.getCount(); i++){
                cursor2.moveToNext();
                int t = cursor2.getInt(0);
                double budget = cursor2.getDouble(1);
                if(budget == 0.0){
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
                        case 6:
                            etc += 1;
                            break;
                        default:
                            break;
                    }
                }

                total += budget;
                Log.d("database", "#"+i+"->"+t+", "+budget+", "+total);
            }

            cursor.close();
            cursor2.close();
        }

        remain -= total;
        totalBudget.setText(String.valueOf((int)remain));
        lodgingText.append(" - "+lodging);
        foodText.append(" - "+food);
        shoppingText.append(" - "+shopping);
        tourismText.append(" - "+tourism);
        etcText.append(" - "+etc);

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

        Log.d("remain: ", remain+"");

        //카테고리별 예산 max=total
        lodgingBar.setMax(((int)remain)/1000);
        foodBar.setMax(((int)remain)/1000);
        leisureBar.setMax(((int)remain)/1000);
        shoppingBar.setMax(((int)remain)/1000);
        etcBar.setMax(((int)remain)/1000);

        //각 카테고리 예산 텍스트 조정
        lodgingBudget.setText(""+lodgingB);
        foodBudget.setText(""+foodB);
        leisureBudget.setText(""+leisureB);
        shoppingBudget.setText(""+shoppingB);
        //transportBudget.setText("$ "+transportB);
        etcBudget.setText(""+etcB);

        //카테고리 비율에 맞게 텍스트랑 프로그레스 바 조정
        lodgingBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //progress가 바뀐 선택 값
                total2 = remain-foodB-shoppingB - leisureB - etcB;
                totalBudget.setText(String.valueOf((int)total2-progress*1000));
                int percent= (progress*1000)*100/(int)remain;
                lodgingPer.setText(String.valueOf((percent))+"%");
                lodgingBudget.setText("₩ "+String.valueOf(progress*1000));
                lodgingB = progress*1000;
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
                total2 = remain-lodgingB-shoppingB-leisureB - etcB;
                totalBudget.setText(String.valueOf((int)total2-progress*1000));
                int percent= (progress*1000)*100/(int)remain;
                foodPer.setText(String.valueOf(percent)+"%");
                foodBudget.setText(String.valueOf(progress*1000));
                foodB = progress*1000;
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
                total2 = remain-lodgingB-foodB-leisureB - etcB;
                totalBudget.setText(String.valueOf((int)total2-progress*1000));
                int percent= (progress*1000)*100/(int)remain;
                shoppingPer.setText(String.valueOf(percent)+"%");
                shoppingBudget.setText("₩ "+String.valueOf(progress*1000));
                shoppingB = progress*1000;
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
                total2 = remain-lodgingB-shoppingB-foodB - etcB;
                totalBudget.setText(String.valueOf((int)total2-progress*1000));
                int percent= (progress*1000)*100/(int)remain;
                leisurePer.setText(String.valueOf(percent)+"%");
                leisureBudget.setText("₩ "+String.valueOf(progress*1000));
                leisureB = progress*1000;
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
                total2 = remain-lodgingB-shoppingB-leisureB-foodB;
                totalBudget.setText(String.valueOf((int)total2-progress*1000));
                int percent= (progress*1000)*100/(int)remain;
                etcPer.setText(String.valueOf(percent)+"%");
                etcBudget.setText("₩ "+String.valueOf(progress*1000));
                etcB = progress*1000;
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
        if(database != null){
            String sql = "update CategoryBudgetTable set budget = ? where category = 1"+" and main_position = "+mainPosition;
            Object[] params1= {lodgingB};
            database.execSQL(sql, params1);
            sql = "update CategoryBudgetTable set budget = ? where category = 2"+" and main_position = "+mainPosition;
            Object[] params2 = {foodB};
            database.execSQL(sql, params2);
            sql = "update CategoryBudgetTable set budget = ? where category = 3"+" and main_position = "+mainPosition;
            Object[] params3 = {shoppingB};
            database.execSQL(sql, params3);
            sql = "update CategoryBudgetTable set budget = ? where category = 4"+" and main_position = "+mainPosition;
            Object[] params4 = {leisureB};
            database.execSQL(sql, params4);
            sql = "update CategoryBudgetTable set budget = ? where category = 5"+" and main_position = "+mainPosition;
            Object[] params5 = {transportB};
            database.execSQL(sql, params5);
            sql = "update CategoryBudgetTable set budget = ? where category = 6"+" and main_position = "+mainPosition;
            Object[] params6 = {etcB};
            database.execSQL(sql, params6);
            /*String sql1 = "select category, budget from CategoryBudgetTable";
            Cursor cursor2 = database.rawQuery(sql1, null);
            for(int i=0; i<cursor2.getCount(); i++){
                cursor2.moveToNext();
                int t = cursor2.getInt(0);
                double budget = cursor2.getDouble(1);

                Log.d("category budget", "#"+i+"->"+t+", "+budget);
            }
            cursor2.close();*/
            String sql1="";
            if(lodging != 0){
                sql1 = "update BudgetTable set budget = ? where type = 1 and budget = 0.0"+" and main_position = "+mainPosition;
                Object[] params7 = {Math.round((lodgingB/lodging)*100)/100.0};
                database.execSQL(sql1, params7);
            }
            if(food != 0){
                sql1 = "update BudgetTable set budget = ? where type = 2 and budget = 0.0"+" and main_position = "+mainPosition;
                Object[] params8 = {Math.round((foodB/food)*100)/100.0};
                database.execSQL(sql1, params8);
            }
            if(shopping != 0){
                sql1 = "update BudgetTable set budget = ? where type = 3 and budget = 0.0"+" and main_position = "+mainPosition;
                Object[] params9 = {Math.round((shoppingB/shopping)*100)/100.0};
                database.execSQL(sql1, params9);
            }
            if(tourism != 0){

                sql1 = "update BudgetTable set budget = ? where type = 4 and budget = 0.0"+" and main_position = "+mainPosition;
                Object[] params10 = {Math.round((leisureB/tourism)*100)/100.0};
                database.execSQL(sql1, params10);
            }
            if(etc != 0){
                sql1 = "update BudgetTable set budget = ? where type = 6 and budget = 0.0"+" and main_position = "+mainPosition;
                Object[] params11 = {Math.round((etcB/etc)*100)/100.0};
                database.execSQL(sql1, params11);
            }
        }

        Intent intent = new Intent();
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
