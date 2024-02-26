package com.example.test;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public class MyView extends View {

    private Bitmap image;


    static {
        System.loadLibrary("test");
    }

    List<Ball> balls = new ArrayList<Ball>();
    public MyView(Context context, AttributeSet attrs){
        super(context, attrs);
        image = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setHeight(h);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        for(Ball ball : balls){
            ball.drawBall(canvas);
        }

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        int sizeRect = 40;
        int xBorder = (getHeight()-sizeRect)/2;
        int yBorder = 0;
        canvas.drawRect(xBorder, yBorder, xBorder + sizeRect, yBorder + sizeRect, paint );
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            Ball ball = new Ball(event.getX(), event.getY(), image);
            balls.add(ball);
            startThreadOfBall(ball);
            invalidate();
        }

        return super.onTouchEvent(event);
    }

    /*This method to start new thread calculate position of ball*/
    private void startThreadOfBall(Ball ball){
        Thread thread = new Thread(() -> {
            // Current code will be work in new thread
            while(ball.isAlive) {
                Ball nBall = calculateNewConditionOfBall(ball);
                System.gc(); //cleaning memory, because thrown away old object ball

                /* I don't get reference old object!!!*/

                postInvalidate();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    native Ball calculateNewConditionOfBall(Ball ball);
    native void setHeight(float h);
    native int getY(int sizeCircul);

}
