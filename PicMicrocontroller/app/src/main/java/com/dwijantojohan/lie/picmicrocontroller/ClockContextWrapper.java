package com.dwijantojohan.lie.picmicrocontroller;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dlie on 5/31/2016.
 */
public class ClockContextWrapper extends ContextWrapper implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    Activity act;

    public static final String MyPreferences = "MyPref";
    public static final String CurrentClockKey = "CurrentClock";
    public static final String CurrentClockKeyHz = "CurrentClockHz";
    public static final String Frequency = "frequency";
    private static String SpinnerValue = "MHz";
    public Button AcceptBtn;
    public Spinner spinner;
    SharedPreferences sp;

    public ClockContextWrapper(Context base){
        super(base);
        act = (Activity) this.getBaseContext();
        initialized();
    }
    private void initialized(){
        AcceptBtn = (Button) act.findViewById(R.id.button4);
        spinner = (Spinner) act.findViewById(R.id.spinner);
        loadSharedPreferences();
    }

    public void loadSharedPreferences() {
        sp = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        int myValue = sp.getInt(CurrentClockKey, 4);
        SpinnerValue = sp.getString(Frequency, "MHz");
        final TextView textView = (TextView) act.findViewById(R.id.textView2);
        final TextView textView3 = (TextView) act.findViewById(R.id.textView3);
        textView.setText(String.format("%s", myValue));
        textView3.setText(String.format("%s", SpinnerValue));
    }

    public void saveSharedPreference(View view) {
        SharedPreferences.Editor editor = sp.edit();
        EditText e = (EditText) act.findViewById(R.id.editText);
        String str = e.getText().toString();
        int val;
        if (Helper.tryParseInt(str)) {
            val = Integer.parseInt(str);
            editor.putInt(CurrentClockKey, val);
            editor.putString(Frequency, SpinnerValue);
            editor.putInt(CurrentClockKeyHz, Helper.getValueHz(val, SpinnerValue));
            editor.commit();
            int myvalue = sp.getInt(CurrentClockKey, 4);
            Toast.makeText(getApplicationContext(), String.format("%s %d %s",this.getResources().getString(R.string.clockcurrentvalueis), myvalue, SpinnerValue), Toast.LENGTH_LONG).show();
            act.finish();
        }
    }

    //Listener
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] frequency = getResources().getStringArray(R.array.frequency);
        SpinnerValue = frequency[position];

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        saveSharedPreference(v);
    }

}
