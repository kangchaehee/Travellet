package com.example.example;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainActivityPastFragment extends Fragment {

    ListView listView;
    ArrayList<MainItem> items = new ArrayList<MainItem>();
    MainPlanAdapter adapter = new MainPlanAdapter();
    double[] budgetList = new double[5];
    DeleteDialog oDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_main_past, container, false);

        listView = (ListView) rootView.findViewById(R.id.listView);

        items.clear();
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        adapter.addItem(new MainItem("D+100", "happy trip", "2020.01.04", "2020.02.01"));
        adapter.addItem(new MainItem("D+15", "hello", "2020.01.05", "2020.02.02"));
        adapter.addItem(new MainItem("D+16", "lalala", "2020.01.06", "2020.02.03"));


        return rootView;
    }

    class MainPlanAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(MainItem item) {
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
            MainItemView view= new MainItemView(getContext());
            final MainItem item = items.get(position);
            view.setdDay(item.getdDay());
            view.setTitle(item.getTripTitle());
            view.setPeriod(item.getStartDay()+"-"+item.getEndDay());

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

