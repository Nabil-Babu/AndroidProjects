package com.fountainhead.nabil.finalapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class StatusPanel extends View
{
    final static String MYDEBUG = "MYDEBUG"; // for Log.i messages
    final float TEXT_SIZE = 12f;
    final int OFFSET = 5;

    public int totalSongs;
    public int correctSongs;
    float pixelDensity; // ...to control text size and margins
    Paint p;
    float textSize;
    float margin;


    private String[] times;
    private int[] correctSongsPerTrial;
    private int trials;
    private long elapsedTime;
    private long firstSong;
    private long lastSong;
    private String inputMethod;
    private String participantCode;
    private String groupCode;


    // Should provide three constructors to correspond to each of the three in View.
    public StatusPanel(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        initialize(context);
    }

    public StatusPanel(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initialize(context);
    }

    public StatusPanel(Context context)
    {
        super(context);
        initialize(context);
    }

    public void initialize(Context c)
    {
        // get the pixel density for the device's display
        pixelDensity = c.getResources().getDisplayMetrics().density;

        textSize = TEXT_SIZE * pixelDensity;
        margin = OFFSET * pixelDensity;
        p = new Paint();
        p.setColor(Color.WHITE);
        p.setAntiAlias(true);
        p.setTextSize(textSize);
        times = new String[5];
        correctSongsPerTrial = new int[5];
        totalSongs = 0;
        correctSongs = 0;
        elapsedTime = 0;
    }

    private String timeString(long elapsedTime)
    {
        StringBuilder time = new StringBuilder();
        int minutes = (int)(elapsedTime / 1000) / 60;
        int seconds = (int)(elapsedTime / 1000) - (minutes * 60);
        int tenths = (int)(elapsedTime / 10) % 10;
        time.append(minutes + ":");
        if (seconds < 10)
            time.append("0" + seconds);
        else
            time.append(seconds);
        time.append("." + tenths);
        return time.toString();
    }

    // update the values in the status panel
    public void update(String song)
    {
        if(totalSongs == 0){
            firstSong = SystemClock.elapsedRealtime();
        }

        if(totalSongs < 10) {
            totalSongs++;
            String c = song.substring(0, 1);
            if (c.equals("S")) {
                correctSongs++;
            }
            invalidate();
        }

        if (totalSongs == 10){
            lastSong = SystemClock.elapsedRealtime();
            elapsedTime = lastSong - firstSong;
            times[trials] = timeString(elapsedTime);
            correctSongsPerTrial[trials] = correctSongs;
            trials++;
        }

        if (trials == 5){
            Bundle b = new Bundle();
            Intent i = new Intent(getContext(), ResultPage.class);
            b.putStringArray("times", times);
            b.putIntArray("correctSongs", correctSongsPerTrial);
            b.putString("participant", participantCode);
            b.putString("group", groupCode);
            b.putString("input", inputMethod);
            i.putExtras(b);
            getContext().startActivity(i);

        }
    }

    public void reset()
    {

            totalSongs = 0;
            correctSongs = 0;
            invalidate();

    }

    public void setInfo(String participant, String group, String input)
    {
        participantCode = participant;
        groupCode = group;
        inputMethod = input;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        final float fieldWidth = (this.getWidth() / 2);
        canvas.drawText("Number of Songs Added = " + totalSongs, margin + 0 * fieldWidth, 1 * (textSize + textSize / 4), p);
        canvas.drawText("Completed Trials = " + trials, margin + 0 * fieldWidth, 2 * (textSize + textSize / 4), p);


    }
}
