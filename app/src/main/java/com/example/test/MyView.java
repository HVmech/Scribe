package com.example.test;

import android.content.Context;
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


    static {
        System.loadLibrary("test");
    }

    List<Ball> balls = new ArrayList<Ball>();
    public MyView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        for(Ball ball : balls){
            ball.drawBall(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Ball ball = new Ball(event.getX(), event.getY());
        balls.add(ball);
        startThreadOfBall(ball);
        invalidate();
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
    }

    native float calculatePointXOfBall(float y);
    native Ball calculateNewConditionOfBall(Ball ball);

}
