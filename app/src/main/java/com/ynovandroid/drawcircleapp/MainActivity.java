package com.ynovandroid.drawcircleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private DrawCircleOnTouch drawCircleOnTouch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawCircleOnTouch = findViewById(R.id.canvas);

        drawCircleOnTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        drawCircleOnTouch.addCircle();
                    case MotionEvent.ACTION_MOVE:
                        drawCircleOnTouch.touchHandler(x, y);
                        break;
                    case MotionEvent.ACTION_UP:
                        drawCircleOnTouch.performClick();
                        drawCircleOnTouch.nextCircle();
                        break;
                }

                return true;
            }
        });
    }
}
