package com.example.example;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WalletAllFragment extends Fragment {

    ListView listView1, listView2;
    ArrayList<WalletMainItem> itemsMain = new ArrayList<WalletMainItem>();

    ArrayList<WalletListSubItem> itemsList = new ArrayList<WalletListSubItem>();
    WalletSubAdapter adapterList = new WalletSubAdapter();

    ImageButton viewList;
    boolean listState = false;

    int startYear, startMonth, startDay, startDoW, endYear, endMonth, endDay, endDoW;

    TextView day, period;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_wallet_all, container, false);

        viewList = rootView.findViewById(R.id.btn_list);
        listView2 = rootView.findViewById(R.id.viewList);
        listView2.setAdapter(adapterList);

        //리스트 추가된 거 확인하려면 얘네 주석 해제하면 됨.
        //adapterMain.addItem(new WalletMainItem("AM 10:00", "Lotte Hotel", "Lodging", 1200.0, 1000.0));

        //adapterList.addItem(new WalletListSubItem("Lodging", "Lotte Hotel", 1000.0, R.drawable.ic_lodging_24px, R.drawable.ic_card_24px));


        viewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!listState) {
                    listState = true;
                    viewList.setBackgroundResource(R.drawable.ic_view_list_selected);
                    listView2.setVisibility(View.VISIBLE);
                } else {
                    listState = false;
                    viewList.setBackgroundResource(R.drawable.ic_view_list);
                    listView2.setVisibility(View.GONE);
                }
            }
        });
        return rootView;
    }

    class WalletSubAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return itemsList.size();
        }

        public void addItem(WalletListSubItem item) {
            itemsList.add(item);
        }

        @Override
        public Object getItem(int position) {
            return itemsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            WalletListSubItemView view = new WalletListSubItemView(getContext());
            final WalletListSubItem item = itemsList.get(position);

            view.setCategory(item.getCategory());
            view.setPlace(item.getPlace());
            view.setMoney(item.getMoney());
            view.setCategory_ic(item.getCategory_ic());
            view.setPayment(item.getPayment());

            return view;
        }
    }
}
