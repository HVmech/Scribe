package com.example.test;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {
  float x;
  float y;

  float Vx;
  float Vy;

  boolean isAlive = false;

  public Ball(float x, float y) {
    this.x = x;
    this.y = y;
    isAlive = true;
  }

  public void drawBall(Canvas canvas){
    Paint p = new Paint();
    p.setColor(Color.WHITE);
    p.setStrokeWidth(5);
    p.setStyle(Paint.Style.STROKE);
    p.setColor(Color.BLUE);



    canvas.drawCircle(x, y, 50, p);
  }
}
