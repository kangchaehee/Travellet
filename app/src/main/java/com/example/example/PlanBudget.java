package com.example.example;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlanBudget extends AppCompatActivity {

    ArrayList<PlanBudgetItem> items = new ArrayList<PlanBudgetItem>();
    PlanBudgetAdapter adapter = new PlanBudgetAdapter();

    ListView listView;
    ImageButton back;
    Button add;

    int type, tType;
    double tBudget=0, lodgingB=0, foodB=0, tourismB=0, shoppingB=0, etcB=0, budget=0;

    String memo, title;


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

        TextView memoTxt = findViewById(R.id.textView2);
        memoTxt.setText(memo);
        TextView titleTxt = findViewById(R.id.textView);
        titleTxt.setText(title);

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        adapter.addItem(new PlanBudgetItem(type, 0));
        adapter.addItem(new PlanBudgetItem(tType, tBudget));

        back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
                adapter.addItem(new PlanBudgetItem(type, Double.parseDouble(budget)));
                adapter.notifyDataSetChanged();
            }
        }
        if(requestCode == 106){
            if(intent != null){
                String memo = intent.getStringExtra("memo");
                String budget = intent.getStringExtra("budget");
                int type = intent.getIntExtra("type", 0);
                int position = intent.getIntExtra("position", 0);
                items.get(position).setBudget(Double.parseDouble(budget));
                items.get(position).setCategory(type);
                budget += items.get(position).getBudget();
                adapter.notifyDataSetChanged();
            }
        }
    }

    public void returnToBack(){
        Intent intent = new Intent();
        intent.putExtra("total", budget);
        setResult(RESULT_OK, intent);
        finish();
    }
}
