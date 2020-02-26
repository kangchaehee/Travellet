package com.example.example;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TransportDialog extends Dialog {

    TransportDialog m_tDialog;
    int transport;

    ImageButton walk;
    ImageButton bus;
    ImageButton subway;
    ImageButton taxi;
    ImageButton car;

    public TransportDialog(Context context){
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.5f;
        getWindow().setAttributes(lpWindow);
        setContentView(R.layout.transport_dialog);

        m_tDialog = this;

        walk = (ImageButton) findViewById(R.id.walk);
        bus = (ImageButton) findViewById(R.id.bus);
        subway = (ImageButton) findViewById(R.id.subway);
        taxi = (ImageButton) findViewById(R.id.taxi);
        car = (ImageButton) findViewById(R.id.car);

        if (transport == 2){
            bus.setBackgroundResource(R.drawable.ic_bus__pressed);
        }


        walk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(transport != 1){
                    transport = 1;
                    walk.setBackgroundResource(R.drawable.ic_walk_pressed);
                    bus.setBackgroundResource(R.drawable.ic_bus);
                    subway.setBackgroundResource(R.drawable.ic_subway);
                    taxi.setBackgroundResource(R.drawable.ic_taxi);
                    car.setBackgroundResource(R.drawable.ic_car);
                }

                else {
                    transport = -1;
                    walk.setBackgroundResource(R.drawable.ic_walk);
                }

            }
        });

        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(transport != 2){
                    transport = 2;
                    walk.setBackgroundResource(R.drawable.ic_walk);
                    bus.setBackgroundResource(R.drawable.ic_bus__pressed);
                    subway.setBackgroundResource(R.drawable.ic_subway);
                    taxi.setBackgroundResource(R.drawable.ic_taxi);
                    car.setBackgroundResource(R.drawable.ic_car);
                }

                else {
                    transport = -1;
                    bus.setBackgroundResource(R.drawable.ic_bus);
                }
            }
        });

        subway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(transport != 3){
                    transport = 3;
                    walk.setBackgroundResource(R.drawable.ic_walk);
                    bus.setBackgroundResource(R.drawable.ic_bus);
                    subway.setBackgroundResource(R.drawable.ic_subway__pressed);
                    taxi.setBackgroundResource(R.drawable.ic_taxi);
                    car.setBackgroundResource(R.drawable.ic_car);
                }

                else {
                    transport = -1;
                    subway.setBackgroundResource(R.drawable.ic_subway);
                }
            }
        });

        taxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(transport != 4){
                    transport = 4;
                    walk.setBackgroundResource(R.drawable.ic_walk);
                    bus.setBackgroundResource(R.drawable.ic_bus);
                    subway.setBackgroundResource(R.drawable.ic_subway);
                    taxi.setBackgroundResource(R.drawable.ic_taxi__pressed);
                    car.setBackgroundResource(R.drawable.ic_car);
                }

                else {
                    transport = -1;
                    taxi.setBackgroundResource(R.drawable.ic_taxi);
                }
            }
        });



        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(transport != 5){
                    transport = 5;
                    walk.setBackgroundResource(R.drawable.ic_walk);
                    bus.setBackgroundResource(R.drawable.ic_bus);
                    subway.setBackgroundResource(R.drawable.ic_subway);
                    taxi.setBackgroundResource(R.drawable.ic_taxi);
                    car.setBackgroundResource(R.drawable.ic_car__pressed);
                }

                else {
                    transport = -1;
                    car.setBackgroundResource(R.drawable.ic_car);
                }
            }
        });


        Button ok = (Button) findViewById(R.id.button);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickOk(v);
            }
        });
    }

    public void onClickOk(View view){

        this.dismiss();
    }

    public int getTransport(){
        return transport;
    }

    public void setTransport(int t){
        transport = t;
    }
}
