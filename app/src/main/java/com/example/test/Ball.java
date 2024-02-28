package com.example.test;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {
  float x;
  float y;

  float Vx = 5; // Example
  float Vy = 5; // Example

  int ballSize = 150;
  boolean isAlive = false;

  private Bitmap image;
  private Bitmap ball;

  public Ball(float x, float y) {
    this.x = x;
    this.y = y;
    isAlive = true;
  }

  public Ball(float x, float y, Bitmap bitmap) {
    this(x, y);
    this.image = bitmap;
  }

  public void drawBall(Canvas canvas){
    Paint p = new Paint();
    p.setColor(Color.WHITE);
    p.setStrokeWidth(5);
    p.setStyle(Paint.Style.STROKE);
    p.setColor(Color.BLUE);

    ball = Bitmap.createScaledBitmap(image, ballSize, ballSize, false);
    canvas.drawBitmap(ball, x, y, p);
    // TODO: Delete
    // canvas.drawRect(x, y, x+150, y+150, p);
  }
}
