package com.rtlab.numerenorocoase;

import android.view.MotionEvent;
import android.view.View;

public class Effect {

    //makes the button 5% smaller and then back to 100%. It gives the feel of being pushed
    public static void buttonEffect(final View button){

        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        button.setScaleX(0.95f);
                        button.setScaleY(0.95f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        button.setScaleX(1f);
                        button.setScaleY(1f);
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }
}
