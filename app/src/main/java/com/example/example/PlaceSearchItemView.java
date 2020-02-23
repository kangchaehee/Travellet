package com.example.example;


import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PlaceSearchItemView extends LinearLayout {

    ImageView placeListThumb;
    TextView placeListName, placeListAddr, placeListType;

    public PlaceSearchItemView(Context context) {
        super(context);
        init(context);
    }

    public PlaceSearchItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_place_search_item, this, true);

        placeListThumb = (ImageView) findViewById(R.id.placeListThumb);
        placeListName = (TextView) findViewById(R.id.placeListName);
        placeListAddr = (TextView) findViewById(R.id.placeListAddr);
        placeListType = (TextView) findViewById(R.id.placeListType);

    }

    public void setPlaceListThumb(String thumb) {
        GradientDrawable drawable = (GradientDrawable) getContext().getDrawable(R.drawable.image_rounding);
        placeListThumb.setBackground(drawable);
        placeListThumb.setClipToOutline(true);
        Glide.with(getContext()).load(thumb).into(placeListThumb);
    }

    public void setPlaceListName(String name) {
        this.placeListName.setText(name);
    }

    public void setPlaceListAddr(String addr) {
        this.placeListAddr.setText(addr);
    }

    public void setPlaceListType(String type) {
        this.placeListType.setText(type);
    }


}
