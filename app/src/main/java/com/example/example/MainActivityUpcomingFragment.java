package com.example.example;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainActivityUpcomingFragment extends Fragment {
    ListView listView;
    ArrayList<MainItem> items = new ArrayList<MainItem>();
    MainPlanAdapter adapter = new MainPlanAdapter();
    double[] budgetList = new double[5];
    DeleteDialog oDialog;


    int startYear, startMonth, startDay, endYear, endMonth, endDay;
    String dDay;
    String travelTitle;
    float budget, lodgingBudget, foodBudget, leisureBudget, shoppingBudget, transportBudget, etcBudget;

    TextView t;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_main_upcoming, container, false);

        listView = (ListView) rootView.findViewById(R.id.listView);
        items.clear();
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        t = (TextView) rootView.findViewById(R.id.t);

        if(getArguments() != null){
            startYear = getArguments().getInt("startYear", 0);
            startMonth = getArguments().getInt("startMonth", 0);
            startDay = getArguments().getInt("startDay", 0);
            endYear = getArguments().getInt("endYear", 0);
            endMonth = getArguments().getInt("endMonth", 0);
            endDay = getArguments().getInt("endDay", 0);
            travelTitle = getArguments().getString("travelTitle");
            Toast.makeText(getContext(), travelTitle, Toast.LENGTH_SHORT).show();
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
            //adapter.addItem(new MainItem("D-10", "happy trip", "2020.01.04", "2020.02.01"));


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
            view.setdDay(item.getdDay());
            view.setTitle(item.getTripTitle());
            view.setPeriod(item.getStartDay()+" - "+item.getEndDay());

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
}
