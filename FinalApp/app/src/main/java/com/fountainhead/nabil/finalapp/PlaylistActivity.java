package com.fountainhead.nabil.finalapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class PlaylistActivity extends Activity
{
    PaintPanel imagePanel,imagePanel2,imagePanel3,imagePanel4,imagePanel5,imagePanel6,imagePanel7,
            imagePanel8,imagePanel9,imagePanel10,imagePanel11,imagePanel12,imagePanel13,imagePanel14,imagePanel15,
            imagePanel16,imagePanel17,imagePanel18,imagePanel19,imagePanel20;
    StatusPanel statusPanel; // a status panel the display the image coordinates, size, and scale
    private String inputMethod;
    private String participantCode;
    private String groupCode;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // hide title bar
        setContentView(R.layout.main);

        Bundle b = getIntent().getExtras();
        participantCode = b.getString("participantCode");
        inputMethod = b.getString("inputMethod");
        groupCode = b.getString("groupCode");


        // get references to UI components
        imagePanel = (PaintPanel)findViewById(R.id.paintpanel);
        imagePanel2 = (PaintPanel)findViewById(R.id.paintpanel2);
        imagePanel3 = (PaintPanel)findViewById(R.id.paintpanel3);
        imagePanel4 = (PaintPanel)findViewById(R.id.paintpanel4);
        imagePanel5 = (PaintPanel)findViewById(R.id.paintpanel5);
        imagePanel6 = (PaintPanel)findViewById(R.id.paintpanel6);
        imagePanel7 = (PaintPanel)findViewById(R.id.paintpanel7);
        imagePanel8 = (PaintPanel)findViewById(R.id.paintpanel8);
        imagePanel9 = (PaintPanel)findViewById(R.id.paintpanel9);
        imagePanel10 = (PaintPanel)findViewById(R.id.paintpanel10);
        imagePanel11 = (PaintPanel)findViewById(R.id.paintpanel11);
        imagePanel12 = (PaintPanel)findViewById(R.id.paintpanel12);
        imagePanel13 = (PaintPanel)findViewById(R.id.paintpanel13);
        imagePanel14 = (PaintPanel)findViewById(R.id.paintpanel14);
        imagePanel15 = (PaintPanel)findViewById(R.id.paintpanel15);
        imagePanel16 = (PaintPanel)findViewById(R.id.paintpanel16);
        imagePanel17 = (PaintPanel)findViewById(R.id.paintpanel17);
        imagePanel18 = (PaintPanel)findViewById(R.id.paintpanel18);
        imagePanel19 = (PaintPanel)findViewById(R.id.paintpanel19);
        imagePanel20 = (PaintPanel)findViewById(R.id.paintpanel20);

        statusPanel = (StatusPanel)findViewById(R.id.statuspanel);
        statusPanel.setInfo(participantCode,groupCode,inputMethod);

        imagePanel.setStatusPanel(statusPanel);
        imagePanel2.setStatusPanel(statusPanel);
        imagePanel3.setStatusPanel(statusPanel);
        imagePanel4.setStatusPanel(statusPanel);
        imagePanel5.setStatusPanel(statusPanel);
        imagePanel6.setStatusPanel(statusPanel);
        imagePanel7.setStatusPanel(statusPanel);
        imagePanel8.setStatusPanel(statusPanel);
        imagePanel9.setStatusPanel(statusPanel);
        imagePanel10.setStatusPanel(statusPanel);
        imagePanel11.setStatusPanel(statusPanel);
        imagePanel12.setStatusPanel(statusPanel);
        imagePanel13.setStatusPanel(statusPanel);
        imagePanel14.setStatusPanel(statusPanel);
        imagePanel15.setStatusPanel(statusPanel);
        imagePanel16.setStatusPanel(statusPanel);
        imagePanel17.setStatusPanel(statusPanel);
        imagePanel18.setStatusPanel(statusPanel);
        imagePanel19.setStatusPanel(statusPanel);
        imagePanel20.setStatusPanel(statusPanel);

        imagePanel.setInput(inputMethod);
        imagePanel2.setInput(inputMethod);
        imagePanel3.setInput(inputMethod);
        imagePanel4.setInput(inputMethod);
        imagePanel5.setInput(inputMethod);
        imagePanel6.setInput(inputMethod);
        imagePanel7.setInput(inputMethod);
        imagePanel8.setInput(inputMethod);
        imagePanel9.setInput(inputMethod);
        imagePanel10.setInput(inputMethod);
        imagePanel11.setInput(inputMethod);
        imagePanel12.setInput(inputMethod);
        imagePanel13.setInput(inputMethod);
        imagePanel14.setInput(inputMethod);
        imagePanel15.setInput(inputMethod);
        imagePanel16.setInput(inputMethod);
        imagePanel17.setInput(inputMethod);
        imagePanel18.setInput(inputMethod);
        imagePanel19.setInput(inputMethod);
        imagePanel20.setInput(inputMethod);

        imagePanel.setSongTitle("Sweet Child of Mine");
        imagePanel2.setSongTitle("Night of the Long Knives");
        imagePanel3.setSongTitle("Did I Let You Know");
        imagePanel4.setSongTitle("Dani California");
        imagePanel5.setSongTitle("Stairway to Heaven");
        imagePanel6.setSongTitle("By the Way");
        imagePanel7.setSongTitle("Spring");
        imagePanel8.setSongTitle("Under The Bridge");
        imagePanel9.setSongTitle("Scar Tissue");
        imagePanel10.setSongTitle("Sound of Madness");
        imagePanel11.setSongTitle("Black Dog");
        imagePanel12.setSongTitle("State Run Radio");
        imagePanel13.setSongTitle("The Battle of Evermore");
        imagePanel14.setSongTitle("Soldier");
        imagePanel15.setSongTitle("When The Levee Breaks");
        imagePanel16.setSongTitle("Sin with a Grin");
        imagePanel17.setSongTitle("Smoke");
        imagePanel18.setSongTitle("Misty Mountain Hop");
        imagePanel19.setSongTitle("Sooner or Later");
        imagePanel20.setSongTitle("Blood Sugar Sex Magik");

    }

    // Called when the "Reset" button is pressed.
    public void clickReset(View view)
    {
        if(statusPanel.totalSongs == 10){
            imagePanel.invalidate();
            statusPanel.reset();

            imagePanel.reset();
            imagePanel2.reset();
            imagePanel3.reset();
            imagePanel4.reset();
            imagePanel5.reset();
            imagePanel6.reset();
            imagePanel7.reset();
            imagePanel8.reset();
            imagePanel9.reset();
            imagePanel10.reset();
            imagePanel11.reset();
            imagePanel12.reset();
            imagePanel13.reset();
            imagePanel14.reset();
            imagePanel15.reset();
            imagePanel16.reset();
            imagePanel17.reset();
            imagePanel18.reset();
            imagePanel19.reset();
            imagePanel20.reset();
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Not done yet still need 10 songs";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }


    }
}