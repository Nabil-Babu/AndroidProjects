package com.fountainhead.nabil.finalapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import android.view.View;


public class PaintPanel extends View
{
    final static String MYDEBUG = "MYDEBUG"; // for Log.i messages
    public Paint p, p2;
    public String song;
    public String input;
    StatusPanel sp;
    public boolean added;
    private GestureDetector gestureDetector;


    float pixelDensity;

    // Provide three constructors to correspond to each of the three in View
    public PaintPanel(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        initialize(context);
    }

    public PaintPanel(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initialize(context);
    }

    public PaintPanel(Context context)
    {
        super(context);
        initialize(context);
    }

    private void initialize(Context context)
    {
        this.setBackgroundColor(0xffffafb0); // AARRGGBB: opacity, red, green, blue

        p = new Paint();
        p2 = new Paint();
        p2.setColor(Color.BLACK);
        p.setColor(Color.BLACK);
        p.setAntiAlias(true);
        song = "This is a test song title 1 2 3 ";
        added = false;
        // create the fling gesture detector
        gestureDetector = new GestureDetector(context, new MyGestureListener());
        pixelDensity = context.getResources().getDisplayMetrics().density;
        p.setTextSize(14 * pixelDensity);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.save();
        float width = this.getWidth();
        float height = this.getHeight()/2;
        if(input.equals("Button")){
            canvas.drawCircle(width/8,height-15, 25, p2);
        }
        canvas.drawText(song, width/4,height, p);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent me)
    {
        // check for a fling gesture
        gestureDetector.onTouchEvent(me);
        return true;
    }

    public void setStatusPanel(StatusPanel spArg)
    {
        sp = spArg;
    }

    public void setSongTitle(String title)
    {
        song = title;
    }

    public void setInput(String method)
    {
        input = method;
    }

    public void reset()
    {
        p.setColor(Color.BLACK);
        p2.setColor(Color.BLACK);
        added = false;
        invalidate();
    }


    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener
    {

        @Override
        public boolean onSingleTapUp (MotionEvent me)
        {
            if(input.equals("Button")){
                if(sp.totalSongs < 10){
                    if(!added){
                        float x = me.getX();
                        if (x < 250 && x > 80){
                            p2.setColor(Color.GREEN);
                            sp.update(song);
                            added = true;
                            invalidate();
                        }
                    }
                }
            }
            return true;
        }


        /*
         * onFling - This method is executed when a fling or flick gesture is detected
         */
        @Override
        public boolean onFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY)
        {
            // ignore this fling gesture if the initial ACTION_DOWN was outside the image


            if(input.equals("Flick")){
                if(sp.totalSongs < 10)
                {
                    if(!added)
                    {
                        Log.i(MYDEBUG, "Song Added!");
                        if (velocityX > 0)
                        {
                            p.setColor(Color.BLACK);
                        } else {
                            p.setColor(Color.GREEN);
                            sp.update(song);
                            added = true;
                        }

                        invalidate();
                    }
                }
            }
            return true;
        }
    }
}
