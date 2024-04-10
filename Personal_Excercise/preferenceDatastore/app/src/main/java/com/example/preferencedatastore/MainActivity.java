package com.example.preferencedatastore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava2.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava2.RxDataStore;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.Single;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button btnSimple;
    Button btnFancy;
    TextView txtCap;
    View myLayout1Vertical;
    RxDataStore<Preferences> dataStoreRX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLayout1Vertical = (View) findViewById(R.id.linLayout1Vertical);
        txtCap = (TextView) findViewById(R.id.txtCaption1);
        btnSimple = (Button) findViewById(R.id.btnPrefSimple);
        btnFancy = (Button) findViewById(R.id.btnPrefFancy);
        txtCap.setText("This is a sample line \nsuggesting the way the UI looks \nafter you choose your preference(using preferences datastore)");
        dataStoreRX = new RxPreferenceDataStoreBuilder(this,"your_datastorename").build();
        btnSimple.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                putIntValue("backColor", Color.RED);
                putIntValue("textSize",12);
                putIntValue("layoutColor", Color.GRAY);
                applySavedPreferences();
            }
        });
        btnFancy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                putIntValue("backColor", Color.MAGENTA);
                putIntValue("textSize", 20);
                putStringValue("textStyle", "bold");
                putIntValue("layoutColor", Color.GREEN);
                applySavedPreferences();
            }
        });
        if(dataStoreRX!=null ){
            applySavedPreferences();
        }
        else{
            Toast.makeText(getApplicationContext(),"No preferences found", Toast.LENGTH_LONG).show();
        }
    }
    public void putIntValue(String key, int color) {
        Preferences.Key<Integer> PREF_KEY = PreferencesKeys.intKey(key);
        dataStoreRX.updateDataAsync(prefsIn -> {
            MutablePreferences mutablePreferences = prefsIn.toMutablePreferences();
            mutablePreferences.set(PREF_KEY, color);
            return Single.just(mutablePreferences);
        }).subscribe();
    }
    public void putStringValue(String key, String value) {
        Preferences.Key<String> PREF_KEY = PreferencesKeys.stringKey(key);
        dataStoreRX.updateDataAsync(prefsIn -> {
            MutablePreferences mutablePreferences = prefsIn.toMutablePreferences();
            mutablePreferences.set(PREF_KEY, value);
            return Single.just(mutablePreferences);
        }).subscribe();
    }
    int getIntValue(String key) {
        Preferences.Key<Integer> PREF_KEY = PreferencesKeys.intKey(key);
        Single<Integer> value = dataStoreRX.data().firstOrError().map(prefs -> prefs.get(PREF_KEY)).onErrorReturnItem(0);
        return value.blockingGet();
    }
    String getStringValue(String Key) {
        Preferences.Key<String> PREF_KEY = PreferencesKeys.stringKey(Key);
        Single<String> value = dataStoreRX.data().firstOrError().map(prefs -> prefs.get(PREF_KEY)).onErrorReturnItem("null");
        return value.blockingGet();
    }

    public void applySavedPreferences(){
        int backcolor = getIntValue("backColor");
        int textSize = getIntValue("textSize");
        String textStyle = getStringValue("textStyle");
        int layoutColor = getIntValue("layoutColor");
        String msg = "color "+backcolor+"\nsize "+textSize+"\nstyle "+textStyle;
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
        txtCap.setBackgroundColor(backcolor);
        txtCap.setTextSize(textSize);
        if(textStyle.compareTo("normal")==0){
            txtCap.setTypeface(Typeface.SERIF,Typeface.NORMAL);
        }
        else{
            txtCap.setTypeface(Typeface.SERIF,Typeface.BOLD);
        }
        myLayout1Vertical.setBackgroundColor(layoutColor);
    }
}