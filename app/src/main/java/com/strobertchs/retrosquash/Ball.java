package com.strobertchs.retrosquash;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by eric on 2017-05-07.
 * Class representing the ball gamepiece.
 *
 */
public class Ball extends AnimatedSprite {

    protected Bitmap bitmap;
    protected int frameNumber;
    protected int frameHeight;
    protected int frameWidth;
    protected int numFrames;
    protected int framesPerRow;
    protected Rect rectToBeDrawn;
    protected ArrayList<Bitmap> asteroids;



    /**
     * Constructor
     * @param screen_width The width of the devices screen
     */
    public Ball(Context context, int screen_width){
        super();

        setWidth(screen_width/20);       // width set to 1/35th of the screen width
        //setWidth(70);
        setPositionX(screen_width/2);    // default the ball to be in the horizontal middle
        setPositionY(1 + getWidth());    // default the ball to be at the top

        setHorizontal_amount(12);     // horizontal_amount default to 12 pixels
        setUp_amount(10);             // default up_amount to 10
        setDown_amount(6);            // default down_amount to be 6

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.asteroid1);

        frameHeight = bitmap.getHeight();
        frameWidth = bitmap.getWidth();


        asteroids = new ArrayList<Bitmap>();
        asteroids.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.asteroid1));
        asteroids.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.asteroid2));
        asteroids.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.asteroid3));
        asteroids.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.asteroid4));
        numFrames = asteroids.size();


    }


    /**
     * turn off horizontal movement flags
     */
    public void moveStraight(){
        setMovingLeft(false);
        setMovingRight(false);
    }

    /**
     * updatePosition: based on movement flags, move update the position x and y by the proper movement amount
     */
    public void updatePosition(){

        if(isMovingLeft()){
            setPositionX(getPositionX() - getHorizontal_amount());
        }

        if(isMovingRight()){
            setPositionX(getPositionX() + getHorizontal_amount());
        }

        if(isMovingDown()){
            setPositionY(getPositionY() + getDown_amount());

        }else{
            setPositionY(getPositionY() - getUp_amount());

        }

        //advance the frame count
        frameNumber++;

        // this code below was for computing a source rect for a multi-image sprite sheet
        // Android scaling of the source image made this really buggy, so lets not use it
        /*
        int animframeX = frameWidth*(frameNumber%framesPerRow);
        int animframeY = (frameNumber%numFrames)/framesPerRow * frameHeight;

        System.out.println("x: " + animframeX);
        System.out.println("y: " + animframeY);

        double scale_factor = 2;
        rectToBeDrawn = new Rect(animframeX,    //left x
                animframeY,                     //top y
                animframeX + (int)((double)frameWidth*scale_factor),        //right x
                animframeY + (int)((double)frameHeight*scale_factor));      //bottom y

        System.out.println("srcRect: " + rectToBeDrawn.toString());
         */
    }


    /**
     * draw: draws the ball to the canvas object
     * @param source_canvas the canvas object to draw the ball on
     */
    public void draw(Canvas source_canvas){

        // compute the rectangle to draw the image to
        Rect destRect = new Rect(getPositionX(),
                getPositionY(),
                getPositionX() + getWidth(),
                getPositionY() + getWidth());

        //System.out.println("destRect: " + destRect.toString());

        //source_canvas.drawBitmap(bitmap, null, destRect, null);  //show a single image
        source_canvas.drawBitmap(asteroids.get(frameNumber%numFrames), null, destRect, null);  //show a frame in the animation
    }



    /**
     * toString: print current position to the console in the F
     * @return
     */
    public String toString(){
        return "BALL--> x: " + Integer.toString(getPositionX()) + "  y: " + Integer.toString(getPositionY());
    }

}
