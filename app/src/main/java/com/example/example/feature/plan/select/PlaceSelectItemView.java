package com.example.example.feature.plan.select;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.example.R;

public class PlaceSelectItemView extends LinearLayout {

    TextView title, address;

    public PlaceSelectItemView(Context context) {
        super(context);
        init(context);
    }

    public PlaceSelectItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_place_select_item, this, true);

        title = (TextView) findViewById(R.id.title);
        address = (TextView) findViewById(R.id.addr);

    }

    public void setTitle(String name) {
        this.title.setText(name);
    }

    public void setAddress(String addr) {
        this.address.setText(addr);
    }
}


