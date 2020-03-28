package com.example.example;

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

import java.util.ArrayList;

public class PlanBudget extends AppCompatActivity {

    ArrayList<PlanBudgetItem> items = new ArrayList<PlanBudgetItem>();
    PlanBudgetAdapter adapter = new PlanBudgetAdapter();

    ListView listView;
    ImageButton back;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_plan_budget);

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        adapter.addItem(new PlanBudgetItem(1, 1200));
        adapter.addItem(new PlanBudgetItem(2, 800));

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
}
