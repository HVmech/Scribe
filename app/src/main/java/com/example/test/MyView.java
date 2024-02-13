package com.example.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

public class MyView extends View {

    static {
        System.loadLibrary("test");
    }

    public MyView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        final float SX = 100;
        final float SY = 200;

        float X = getX(SX, SY);

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

        canvas.drawCircle(X, SY, SX, paint);
    }
    public native float getX(float x, float y);
}
