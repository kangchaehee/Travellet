package com.example.example;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;

public class BitmapButton extends AppCompatButton {
    public BitmapButton(Context context) {
        super(context);
        init(context);
    }

    public BitmapButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        setBackgroundResource(R.drawable.btn_unpressed);
        float textSize = getResources().getDimension(R.dimen.text_size);
        setTextSize(textSize);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       int action = event.getAction();

       switch(action){
           case MotionEvent.ACTION_BUTTON_PRESS:
                setBackgroundResource(R.drawable.btn_pressed);

                break;
           case MotionEvent.ACTION_BUTTON_RELEASE:
               setBackgroundResource(R.drawable.btn_unpressed);

               break;
        }
        invalidate();

       return true;
    }
}
