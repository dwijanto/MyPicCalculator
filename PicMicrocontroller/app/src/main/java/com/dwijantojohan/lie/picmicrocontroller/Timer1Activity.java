package com.dwijantojohan.lie.picmicrocontroller;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Timer1Activity extends AppCompatActivity {

    private Calculator cal;
    private CheckBox checkBox;
    private EditText editText2;
    private EditText editText3;
    private TextView lowText;
    private TextView highText;
    private Spinner spinner2;
    private Spinner spinner3;
    private int prescallerValue = 1;
    private int clockSourceValue = 4;
    private int myClock;
    private final int MaxPreload = 65536;
    private String TimerErrMsg = "";
    private String PreloadErrMsg= "";

    private CalculatorContextWrapper ccw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.TitleTimer1Activity);
        setContentView(R.layout.activity_timer0);

        initialized();
        //listener();
        ccw = new CalculatorContextWrapper(this);
        ccw.MaxPreloadValue=65536;
        listener();

        ccw.calculateTimer(prescallerValue,clockSourceValue);
    }

    private void initialized(){
        setTitle(R.string.TitleTimer1Activity);
    }

    private void listener(){

        ccw.editText2.setOnFocusChangeListener(ccw);
        ccw.editText3.setOnFocusChangeListener(ccw);
        ccw.checkBox.setOnCheckedChangeListener(ccw);
        ccw.spinner2.setOnItemSelectedListener(ccw);
        ccw.spinner3.setOnItemSelectedListener(ccw);
    }

    /*private void listener(){
        //final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = 0;
                if (position > 0){
                    pos=position+1;
                }
                prescallerValue = (int) Math.pow(2,pos);
                cal.calculateTimer(prescallerValue,clockSourceValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        clockSourceValue=4;
                        break;
                    case 1:
                        clockSourceValue = 1;
                }
                cal.calculateTimer(prescallerValue,clockSourceValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Spinner spinner = (Spinner) findViewById(R.id.spinner2);
                spinner2.setEnabled(isChecked);
                spinner2.setSelection(0);
                prescallerValue=1;
                if(isChecked){
                    prescallerValue=2;
                }
                cal.calculateTimer(prescallerValue,clockSourceValue);


            }
        });









    }*/
}
