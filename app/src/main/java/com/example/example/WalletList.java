package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class WalletList extends AppCompatActivity {

    ImageButton btn_list;

    ListView listView;
    static ArrayList<WalletListSubItem> items = new ArrayList<WalletListSubItem>();
    WalletSubAdapter adapter = new WalletSubAdapter();

    //TextView transBudget;

    DeleteDialog oDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_list);

        listView = (ListView) findViewById(R.id.con);
        listView.setAdapter(adapter);

        btn_list = (ImageButton) findViewById(R.id.btn_list);
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //리스트 뜨게 해주기.

            }
        });


    }


    class WalletSubAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(WalletListSubItem item) {
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
            WalletListSubItemView view= new WalletListSubItemView(getApplicationContext());
            final WalletListSubItem item = items.get(position);

            view.setCategory(item.getCategory());
            view.setCategory_memo(item.getCategory_memo());
            view.setMoney(item.getMoney());
            view.setLodging(item.getLodging());
            view.setPayment(item.getPayment());

            return view;
        }

    }

}
