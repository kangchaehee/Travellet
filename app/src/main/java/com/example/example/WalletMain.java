package com.example.example;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class WalletMain extends Fragment {

    ListView listView;
    ArrayList<WalletMainItem> items = new ArrayList<WalletMainItem>();
    WalletAdapter adapter = new WalletAdapter();

    ImageButton viewList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_wallet_main, container, false);
        viewList = rootView.findViewById(R.id.viewList);

        viewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WalletList.class);

            }
        });

        return rootView;

    }

    class WalletAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(WalletMainItem item) {
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
            WalletMainItemView view = new WalletMainItemView(getContext());
            final WalletMainItem item = items.get(position);
            view.setW_time(item.getW_time());
            view.setW_title(item.getW_title());
            view.setW_memo(item.getW_memo());
            view.setW_cost(item.getW_cost());
            view.setW_budget(item.getW_budget());

            return view;
        }
    }
}