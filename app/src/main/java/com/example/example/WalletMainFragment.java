package com.example.example;

import androidx.lifecycle.ViewModelProviders;

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

import java.util.ArrayList;

public class WalletMainFragment extends Fragment {

    ListView listView1, listView2;
    ArrayList<WalletMainItem> itemsMain = new ArrayList<WalletMainItem>();
    WalletAdapter adapterMain = new WalletAdapter();

    ArrayList<WalletListSubItem> itemsList = new ArrayList<WalletListSubItem>();
    WalletSubAdapter adapterList = new WalletSubAdapter();

    ImageButton viewList;
    boolean listState = false;


    public static WalletMainFragment newInstance() {
        return new WalletMainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.wallet_main_fragment, container, false);

        viewList = rootView.findViewById(R.id.btn_list);
        listView1 = rootView.findViewById(R.id.mainList);
        listView1.setAdapter(adapterMain);

        listView2 = rootView.findViewById(R.id.viewList);
        listView2.setAdapter(adapterList);

        adapterMain.addItem(new WalletMainItem("AM 10:00", "Lotte Hotel", "Lodging", 1200.0, 1000.0));

        adapterList.addItem(new WalletListSubItem("Lodging", "Lotte Hotel", 1000.0, R.drawable.ic_lodging_24px, R.drawable.ic_card_24px));


        viewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!listState){
                    listState = true;
                    viewList.setBackgroundResource(R.drawable.ic_view_list_selected);
                    listView1.setVisibility(View.GONE);
                    listView2.setVisibility(View.VISIBLE);
                }

                else{
                    listState = false;
                    viewList.setBackgroundResource(R.drawable.ic_view_list);
                    listView1.setVisibility(View.VISIBLE);
                    listView2.setVisibility(View.GONE);
                }
            }
        });
        return rootView;
    }

    class WalletAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return itemsMain.size();
        }

        public void addItem(WalletMainItem item) {
            itemsMain.add(item);
        }

        @Override
        public Object getItem(int position) {
            return itemsMain.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            WalletMainItemView view = new WalletMainItemView(getContext());
            final WalletMainItem item = itemsMain.get(position);
            view.setW_time(item.getW_time());
            view.setW_title(item.getW_title());
            view.setW_memo(item.getW_memo());
            view.setW_cost(item.getW_cost());
            view.setW_budget(item.getW_budget());

            RelativeLayout i = view.findViewById(R.id.item);
            i.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), WalletCost.class);
                    startActivity(intent);
                }
            });

            return view;
        }
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
            WalletListSubItemView view= new WalletListSubItemView(getContext());
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
