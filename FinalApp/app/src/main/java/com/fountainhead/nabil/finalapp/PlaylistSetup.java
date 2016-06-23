package com.fountainhead.nabil.finalapp;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * SoftKeyboardExperimentSetup - a class that implements a setup dialog for experimental applications on Android. <p>
 *
 * @author Scott MacKenzie, 2014-2016
 */
@SuppressWarnings("unused")
public class PlaylistSetup extends Activity implements View.OnClickListener
{
    final static String MYDEBUG = "MYDEBUG"; // for Log.i messages
    final static String TITLE = "Final App Setup";


    /*
     * The following arrays are used to fill the spinners in the set up dialog. The first entries will be replaced by
     * corresponding values in the app's shared preferences, if any exist. In order for a value to exit as a shared
     * preference, the app must have been run at least once with the "Save" button tapped.
     */
    String[] participantCode = {"P01", "P02", "P03", "P04", "P05", "P06", "P07", "P08", "P09", "P10"};
    String[] sessionCode = {"S99", "S01", "S02", "S03", "S04", "S05", "S06", "S07", "S08", "S09", "S10", "S11", "S12",
            "S13", "S14", "S15", "S16", "S17", "S18", "S19", "S20", "S21", "S22", "S23", "S24", "S25"};
    String[] blockCode = {"(auto)"};
    String[] groupCode = {"G01", "G02"};
    String[] inputMethod = {"Flick", "Button"};


    // default values for EditText fields (may be different if shared preferences saved)
    String conditionCode = "C01";

    // defaults for booleans (may be different if shared preferences saved)

    SharedPreferences sp;
    SharedPreferences.Editor spe;
    Button ok, save, exit;

    private Spinner spinParticipantCode;
    private Spinner spinSessionCode, spinBlockCode, spinGroupCode;
    private Spinner spinInputMethod;
    private EditText editConditionCode;
    // end set up parameters

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.setup);

        // get a reference to a SharedPreferences object (used to store, retrieve, and save setup parameters)
        sp = this.getPreferences(MODE_PRIVATE);

        // overwrite 1st entry from shared preferences, if corresponding value exits




        // get references to widgets in setup dialog
        spinParticipantCode = (Spinner)findViewById(R.id.spinParticipantCode);
        spinSessionCode = (Spinner)findViewById(R.id.spinSessionCode);
        spinBlockCode = (Spinner)findViewById(R.id.spinBlockCode);
        spinGroupCode = (Spinner)findViewById(R.id.spinGroupCode);
        editConditionCode = (EditText)findViewById(R.id.conditionCode);
        spinInputMethod = (Spinner)findViewById(R.id.inputMethod);



        // get references to OK, SAVE, and EXIT buttons
        ok = (Button)findViewById(R.id.ok);
        save = (Button)findViewById(R.id.save);
        exit = (Button)findViewById(R.id.exit);

        // initialise spinner adapters
        ArrayAdapter<CharSequence> adapterPC = new ArrayAdapter<CharSequence>(this, R.layout.spinnerstyle,
                participantCode);
        spinParticipantCode.setAdapter(adapterPC);

        ArrayAdapter<CharSequence> adapterSC = new ArrayAdapter<CharSequence>(this, R.layout.spinnerstyle,
                sessionCode);
        spinSessionCode.setAdapter(adapterSC);

        ArrayAdapter<CharSequence> adapterBC = new ArrayAdapter<CharSequence>(this, R.layout.spinnerstyle,
                blockCode);
        spinBlockCode.setAdapter(adapterBC);

        ArrayAdapter<CharSequence> adapterGC = new ArrayAdapter<CharSequence>(this, R.layout.spinnerstyle,
                groupCode);
        spinGroupCode.setAdapter(adapterGC);

        ArrayAdapter<CharSequence> adapterNOP = new ArrayAdapter<CharSequence>(this, R.layout.spinnerstyle,
                inputMethod);
        spinInputMethod.setAdapter(adapterNOP);

        // initialize EditText setup items
        editConditionCode.setText(conditionCode);

        // prevent soft keyboard from popping up when activity launches
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onClick(View v)
    {
        if (v == ok)
        {
            // get user's choices
            String part = participantCode[spinParticipantCode.getSelectedItemPosition()];
            String sess = sessionCode[spinSessionCode.getSelectedItemPosition()];
            // String block = blockCode[spinBlock.getSelectedItemPosition()];
            String group = groupCode[spinGroupCode.getSelectedItemPosition()];
            String cond = editConditionCode.getText().toString();
            String input = inputMethod[spinInputMethod.getSelectedItemPosition()];



            // package the user's choices in a bundle
            Bundle b = new Bundle();
            b.putString("participantCode", part);
            b.putString("sessionCode", sess);
            // b.putString("blockCode", block);
            b.putString("groupCode", group);
            b.putString("conditionCode", cond);
            b.putString("inputMethod", input);


            // start experiment activity (sending the bundle with the user's choices)
            Intent i = new Intent(getApplicationContext(), PlaylistActivity.class);
            i.putExtras(b);
            startActivity(i);
            finish();

        } else if (v == save)
        {
            spe = sp.edit();
            spe.putString("participantCode", participantCode[spinParticipantCode.getSelectedItemPosition()]);
            spe.putString("sessionCode", sessionCode[spinSessionCode.getSelectedItemPosition()]);
            spe.putString("groupCode", groupCode[spinGroupCode.getSelectedItemPosition()]);
            spe.putString("conditionCode", editConditionCode.getText().toString());
            spe.putString("inputMethod", inputMethod[spinInputMethod.getSelectedItemPosition()]);

            spe.commit();
            Toast.makeText(this, "Preferences saved!", Toast.LENGTH_SHORT).show();

        } else if (v == exit)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish(); // terminate
        }
    }
}
