package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    MainActivityUpcomingFragment fragment1;
    MainActivityPastFragment fragment2;
    ImageButton up_delete, past_delete;
    Button btn_upcoming, btn_past;

    boolean upcomingState=false, pastState=false;
    //DeleteDialog oDialog;

    //ArrayList<PastFragment> items = new ArrayList<PastFragment>();
    //ArrayList<UpcomingFragment> item = new ArrayList<UpcommingFragment>();
    //MainActivity.PlanSubAdapter adapter = new PlanInitialActivity.PlanSubAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new MainActivityUpcomingFragment();
        fragment2 = new MainActivityPastFragment();

        up_delete = (ImageButton) findViewById(R.id.up_delete);
        past_delete = (ImageButton) findViewById(R.id.past_delete);

        btn_upcoming = (Button) findViewById(R.id.btn_upcoming);
        btn_upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {

                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                if(!upcomingState){
                    upcomingState = true;
                    btn_past.setTextColor(getColor(R.color.soft_grey));
                    btn_upcoming.setTextColor(getColor(R.color.black));

                    pastState = false;
                    btn_past.setTextColor(getColor(R.color.soft_grey));
                }
                else {
                    upcomingState = false;
                    btn_upcoming.setTextColor(getColor(R.color.soft_grey));
                }
            }
        });

        btn_past = (Button) findViewById(R.id.btn_past);
        btn_past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();

                if(!pastState){
                    pastState = true;
                    btn_upcoming.setTextColor(getColor(R.color.soft_grey));
                    btn_past.setTextColor(getColor(R.color.black));

                    upcomingState = false;
                    btn_upcoming.setTextColor(getColor(R.color.soft_grey));
                }
                else {
                    pastState = false;
                    btn_past.setTextColor(getColor(R.color.soft_grey));
                }
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
