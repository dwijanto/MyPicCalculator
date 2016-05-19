package com.dwijantojohan.lie.picmicrocontroller;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ClockActivity extends AppCompatActivity {
    public static final String MyPreferences = "MyPref";
    public static final String CurrentClockKey = "CurrentClock";
    public static final String CurrentClockKeyHz = "CurrentClockHz";
    public static final String Frequency = "frequency";
    private static String SpinnerValue = "MHz";

    private static int ClockValue = 4;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.TitleClockActivity);
        setContentView(R.layout.activity_clock);

        loadSharedPreferences();


        //final String oriValue=textView.getText().toString();

        final EditText editText = (EditText) findViewById(R.id.editText);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0) {
                    //textView.setText(editText.getText().toString());

                } else {
                    //textView.setText(oriValue);
                }
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast toast =
                String[] frequency = getResources().getStringArray(R.array.frequency);
                SpinnerValue = frequency[position];
                //Toast.makeText(getApplicationContext(),SpinnerValue,Toast.LENGTH_SHORT).show();
                //toast.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadSharedPreferences() {
        sp = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        int myValue = sp.getInt(CurrentClockKey, 4);
        SpinnerValue = sp.getString(Frequency, "MHz");
        final TextView textView = (TextView) findViewById(R.id.textView2);
        final TextView textView3 = (TextView) findViewById(R.id.textView3);
        textView.setText(String.format("%s", myValue));
        textView3.setText(String.format("%s", SpinnerValue));
    }

    public void saveSharedPreference(View view) {
        SharedPreferences.Editor editor = sp.edit();
        EditText e = (EditText) findViewById(R.id.editText);
        String str = e.getText().toString();
        int val;
        if (Helper.tryParseInt(str)) {
            val = Integer.parseInt(str);
            editor.putInt(CurrentClockKey, val);
            editor.putString(Frequency, SpinnerValue);
            editor.putInt(CurrentClockKeyHz, Helper.getValueHz(val, SpinnerValue));
            editor.commit();
            int myvalue = sp.getInt(CurrentClockKey, 4);
            Toast.makeText(getApplicationContext(), String.format("Clock Current value is : %d %s", myvalue, SpinnerValue), Toast.LENGTH_LONG).show();
            finish();
        }
    }


}
