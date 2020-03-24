package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    UpcomingFragment fragment1;
    PastFragment fragment2;
    ImageButton up_delete, past_delete;

    //DeleteDialog oDialog;

    //ArrayList<PastFragment> items = new ArrayList<PastFragment>();
    //ArrayList<UpcomingFragment> item = new ArrayList<UpcommingFragment>();
    //MainActivity.PlanSubAdapter adapter = new PlanInitialActivity.PlanSubAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new UpcomingFragment();
        fragment2 = new PastFragment();

        Button btn_upcoming, btn_past;

        up_delete = (ImageButton) findViewById(R.id.up_delete);
        past_delete = (ImageButton) findViewById(R.id.past_delete);
        //
        Button button = (Button) findViewById(R.id.btn_upcoming);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

            }
        });

        Button button2 = (Button) findViewById(R.id.btn_past);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
            }
        });

    }

    /*class PlanSubAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(PlanInitialSubItem item) {
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
            PlanInitialSubItemView view= new PlanInitialSubItemView(getApplicationContext());
            final PlanInitialSubItem item = items.get(position);
            view.setPlaceTime(item.getPlaceTime());
            view.setPlaceName(item.getPlaceName());
            view.setPlaceMemo(item.getPlaceMemo());

            ImageButton del = (ImageButton) view.findViewById(R.id.deleteButton);
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
    }*/

    //travel title set
    public void onClick(View view1){

        Intent intent = new Intent(this, TravelTitleSet.class);
        startActivity(intent);
    }

}
