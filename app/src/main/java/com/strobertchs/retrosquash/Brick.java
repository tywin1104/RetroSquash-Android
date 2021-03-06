package com.strobertchs.retrosquash;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by admin on 2017/5/22.
 */

public class Brick extends Sprite {
    private Bitmap bitmap;
    private int duration;
    private boolean destroyed;

    public int getDuration() {
        return duration;
    }
    public void setDuration( int d) {
        duration = d;
    }
    public  boolean isDestroyed() {
        return destroyed;
    }
    public void setDestroyed(boolean d) {
        destroyed = d;
    }
    public Brick(Context context, int screen_width){
        super();
        duration = 5;
        destroyed = false;
        setPositionX((int)(screen_width/+ (Math.random()*8+1)));
        setPositionY((int)(screen_width/(Math.random()*8+1)));
        setWidth((int)(screen_width/(Math.random()*14+1)));
        setHeight((int)(screen_width/(Math.random()*8+1)));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.birckphoto);
    }
    @Override
    public void draw(Canvas canvas) {
//        canvas.drawRect(getPositionX(),
//                getPositionY(),
//                getPositionX() + getWidth(),
//                getPositionY() + getHeight(),
//                paint);
        Rect destRect = new Rect(getPositionX(),
                getPositionY(),
                getPositionX() + getWidth(),
                getPositionY() + getHeight());
        canvas.drawBitmap(bitmap, null, destRect, null);
    }
}