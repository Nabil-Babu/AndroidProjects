package com.fountainhead.nabil.finalapp;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Nabil on 2/26/2016.
 */

public class ResultPage extends Activity{
    // declare values to be displayed

    String participantCode;
    String groupCode;
    BufferedWriter sd2;
    String[] times;
    int[] correctSongs;
    String inputMethod;

    File f2;

    final static String MYDEBUG = "MYDEBUG"; // for Log.i messages
    final String WORKING_DIRECTORY = "/FinalAppData/";
    final String APP = "FinalAPP";
    final String SD2_HEADER = "Final App File\n";


    TextView trial1, trial2, trial3, trial4, trial5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);


        // grab views
        trial1 = (TextView) findViewById(R.id.Trial1);
        trial2 = (TextView) findViewById(R.id.Trial2);
        trial3 = (TextView) findViewById(R.id.Trial3);
        trial4 = (TextView) findViewById(R.id.Trial4);
        trial5 = (TextView) findViewById(R.id.Trial5);

        //get Data from Bundle
        Bundle b = getIntent().getExtras();
        participantCode = b.getString("participant");
        groupCode = b.getString("group");
        times = b.getStringArray("times");
        correctSongs = b.getIntArray("correctSongs");
        inputMethod = b.getString("input");


        //Set views
        trial1.setText("Trial 1: Time - "+times[0]+" Correct songs(out of 10) - "+correctSongs[0]);
        trial2.setText("Trial 2: Time - "+times[1]+" Correct songs(out of 10) - "+correctSongs[1]);
        trial3.setText("Trial 3: Time - "+times[2]+" Correct songs(out of 10) - "+correctSongs[2]);
        trial4.setText("Trial 4: Time - "+times[3]+" Correct songs(out of 10) - "+correctSongs[3]);
        trial5.setText("Trial 5: Time - "+times[4]+" Correct songs(out of 10) - "+correctSongs[4]);


        File dataDirectory = new File(Environment.getExternalStorageDirectory() + WORKING_DIRECTORY);
        if (!dataDirectory.exists() && !dataDirectory.mkdirs())
        {
            Log.e(MYDEBUG, "ERROR --> FAILED TO CREATE DIRECTORY: " + WORKING_DIRECTORY);
            super.onDestroy(); // cleanup
            this.finish(); // terminate
        }
        Log.i(MYDEBUG, "Working directory=" + dataDirectory);

        int blockNumber = 0;
        do
        {
            ++blockNumber;
            String blockCode = String.format("B%02d", blockNumber);
            String baseFilename = String.format("%s-%s-%s-%s", participantCode,inputMethod, groupCode, blockCode);

            f2 = new File(dataDirectory, baseFilename + ".txt");

            // also make a comma-delimited leader that will begin each data line written to the sd2 file

        } while (f2.exists());

        try
        {
            sd2 = new BufferedWriter(new FileWriter(f2));

            // output header in sd2 file
            sd2.write(SD2_HEADER, 0, SD2_HEADER.length());
            sd2.flush();
        } catch (IOException e)
        {
            Log.e(MYDEBUG, "ERROR OPENING DATA FILES! e=" + e.toString());
            super.onDestroy();
            this.finish();
        }

        StringBuilder sd2Data = new StringBuilder(100);
        sd2Data.append("Trial 1 completion time " +times[0]+" | "+correctSongs[0]+" out 10 songs correct\n");
        sd2Data.append("Trial 2 completion time " +times[1]+" | "+correctSongs[1]+" out 10 songs correct\n");
        sd2Data.append("Trial 3 completion time " +times[2]+" | "+correctSongs[2]+" out 10 songs correct\n");
        sd2Data.append("Trial 4 completion time " +times[3]+" | "+correctSongs[3]+" out 10 songs correct\n");
        sd2Data.append("Trial 5 completion time " +times[4]+" | "+correctSongs[4]+" out 10 songs correct\n");


        try
        {
            sd2.write(sd2Data.toString(), 0, sd2Data.length());
            sd2.flush();
        } catch (IOException e)
        {
            Log.e(MYDEBUG, "ERROR WRITING TO DATA FILE!\n" + e);
            super.onDestroy();
            this.finish();
        }

        try
        {
            sd2.close();
            MediaScannerConnection.scanFile(this, new String[]{f2.getAbsolutePath()}, null, null);

        } catch (IOException e)
        {
            Log.e(MYDEBUG, "ERROR CLOSING DATA FILES! e=" + e);
        }



    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void clickSetup(View view){
        Intent i = new Intent(getApplicationContext(), PlaylistSetup.class);
        startActivity(i);
        finish();
    }

    public void clickExit(View view){
        super.onDestroy(); // cleanup
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // terminate
    }
}
