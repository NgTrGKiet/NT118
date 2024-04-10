package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSimplePref;
    Button btnFancyPref;
    TextView txtCaption1;
    Boolean fancyPrefChosen = false;
    View myLayout1Vertical;

    final int mode = Activity.MODE_PRIVATE;
    final String MYPREFS = "MyPreference_001";

    SharedPreferences mySharedPreferences;
    SharedPreferences.Editor myEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myLayout1Vertical = (View) findViewById (R.id. linLayout1Vertical) ;
        txtCaption1 = (TextView) findViewById(R.id. txtCaption1) ;
        txtCaption1.setText ("This is a sample line \n"
                + "suggesting the way the UI looks \n"
                + "after you choose your preference") ;
        mySharedPreferences = getSharedPreferences (MYPREFS, 0);
        myEditor = mySharedPreferences.edit ();
        if (mySharedPreferences != null
                && mySharedPreferences.contains ("backColor") ) {
// object and key found, show all saved values
            applySavedPreferences () ;
        } else {
            Toast.makeText(getApplicationContext(),
                    "No Preferences found", Toast.LENGTH_LONG).show();

            btnSimplePref = (Button) findViewById(R.id.btnPrefSimple);
            btnSimplePref.setOnClickListener(this);
            btnFancyPref = (Button) findViewById(R.id.btnPrefFancy);
            btnFancyPref.setOnClickListener(this);
        }
    }

    public void onClick(View v) {
        myEditor.clear();
        if(v.getId() == btnSimplePref.getId()){
            myEditor.putInt("backColor", Color.BLACK);
            myEditor.putInt("textSize", 12);
        } else {
            myEditor.putInt("backColor", Color.BLUE);
            myEditor.putInt("textSize", 20);
            myEditor.putString("textStyle", "bold");
            myEditor.putInt("layoutColor", Color.GREEN);
        }
        myEditor.commit();
        applySavedPreferences ();
    }

    public void applySavedPreferences () {
// extract the <key/value> pairs, use default param for missing data
        int backColor = mySharedPreferences.getInt("backColor", Color.BLACK);
        int textSize = mySharedPreferences.getInt("textSize", 12);
        String textStyle = mySharedPreferences.getString("textStyle", "normal");
        int layoutColor = mySharedPreferences.getInt("layoutColor", Color.DKGRAY);
        String msg = "color " + backColor + "\n"
                + "size " + textSize
                + "style " + textStyle;
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

        txtCaption1.setBackgroundColor(backColor);
        txtCaption1.setTextSize(textSize);
        if (textStyle.compareTo("normal") == 0) {
            txtCaption1.setTypeface(Typeface.SERIF, Typeface.NORMAL);
        } else {
            txtCaption1.setTypeface(Typeface.SERIF, Typeface.BOLD);

            myLayout1Vertical.setBackgroundColor(layoutColor);
        }
    }

    protected void onPause() {
        myEditor.putString("DateLastExecution", new Date().toLocaleString());
        myEditor.commit();
        super.onPause();
    }
}