package com.ynovandroid.drawcircleapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawCircleOnTouch extends View {

    private Paint paint;

    private List<Circle> circles = new ArrayList<>();
    private int currentCircle = 0;

    private Random r = new Random();
    private int color;

    public DrawCircleOnTouch(Context context) {
        super(context);
        init(context, null);
    }

    public DrawCircleOnTouch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DrawCircleOnTouch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        if (attrs != null) {
            TypedArray arguments = context.obtainStyledAttributes(attrs, R.styleable.DrawCircleOnTouch);
            color = arguments.getColor(R.styleable.DrawCircleOnTouch_circle_color, -1);
            if (color != -1){
                paint.setColor(color);
            }

            arguments.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        if (!circles.isEmpty()){
            for (Circle circle: circles) {
                paint.setColor(circle.color);
                canvas.drawCircle(circle.posX, circle.posY, circle.rayon, paint);
            }
        }
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    private int generateRayon(){
        int min = 50;
        int max = getWidth() / 3;
       return r.nextInt((max + 1) - min) + min;
    }

    private int generateColor(){
        if (this.color == -1){
            return Color.argb(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));
        }else{
            return this.color;
        }
    }

    public void touchHandler(int x, int y){
        this.circles.get(currentCircle).posX = x;
        this.circles.get(currentCircle).posY = y;
        invalidate();
    }

    public void addCircle(){
        Circle nCircle = new Circle();
        nCircle.rayon = generateRayon();
        nCircle.color = generateColor();
        this.circles.add(nCircle);
    }

    public void nextCircle(){
        this.currentCircle++;
    }
}
