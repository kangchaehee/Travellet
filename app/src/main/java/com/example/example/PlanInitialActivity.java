import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.example.DeleteActivity;
import com.example.example.DeleteDialog;
import com.example.example.PlaceSearchActivity;
import com.example.example.PlanInitialSubItem;
import com.example.example.PlanInitialSubItemView;
import com.example.example.PlanInputActivity;
import com.example.example.R;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PlanInitialActivity extends AppCompatActivity {
    // github test
    Button addButton, placeSearch;

    ListView listView;
    ArrayList<PlanInitialSubItem> items = new ArrayList<PlanInitialSubItem>();
    PlanSubAdapter adapter = new PlanSubAdapter();

    String time, name, memo, tbText, tText;
    int tIc, tBudget;

    DeleteDialog oDialog;

    DeleteActivity cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_plan_initial);

        listView = (ListView) findViewById(R.id.con);
        listView.setAdapter(adapter);

        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlanInputActivity.class);
                startActivityForResult(intent, 101);

            }
        });

        placeSearch = (Button) findViewById(R.id.placeSearch);
        placeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlaceSearchActivity.class);

                startActivityForResult(intent, 102);
            }
        });


        oDialog = new DeleteDialog(this);

    }

    class PlanSubAdapter extends BaseAdapter {

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
            view.setTransBudgetText(item.getTransBudgetText());
            view.setTransportText(item.getTransportText());
            view.setTransportIc(item.getTransportIc());
            view.setTransportBudget(item.getTransportBudget());

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

                    /*Intent intent = new Intent(getApplicationContext(), DeleteActivity.class);
                    startActivity(intent);*/
                }
            });

            ImageButton addT = (ImageButton) view.findViewById(R.id.transportAdd);
            addT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            return view;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 101){
            if(intent != null){
                int hour = intent.getIntExtra("hour", 0);
                int min = intent.getIntExtra("min", 0);
                String ap;
                if(hour > 12) {
                    ap = "PM";
                    hour -= 12;
                }
                else
                    ap = "AM";
                time = ap+ " "+hour+":"+min;
                name = intent.getStringExtra("title");
                memo = intent.getStringExtra("memo");
                adapter.addItem(new PlanInitialSubItem(time, name, memo, tbText, tText, tIc, tBudget));
                adapter.notifyDataSetChanged();
            }
        }
    }



}
