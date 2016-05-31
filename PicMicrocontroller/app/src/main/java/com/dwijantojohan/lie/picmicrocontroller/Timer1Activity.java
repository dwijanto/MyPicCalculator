package com.dwijantojohan.lie.picmicrocontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class Timer1Activity extends AppCompatActivity {


    private final int prescallerValue = 1;
    private final int clockSourceValue = 4;
    private final int MaxPreload = 65536;

    private CalculatorContextWrapper ccw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer0); //using Activity_timer0 as a shared template

        initialized();

    }

    private void initialized(){
        setTitle(R.string.TitleTimer1Activity);
        ccw = new CalculatorContextWrapper(this,MaxPreload);
        listener();
        ccw.calculateTimer(prescallerValue,clockSourceValue);
    }

    private void listener(){
        ccw.editText2.setOnFocusChangeListener(ccw);
        ccw.editText3.setOnFocusChangeListener(ccw);
        ccw.checkBox.setOnCheckedChangeListener(ccw);
        ccw.spinner2.setOnItemSelectedListener(ccw);
        ccw.spinner3.setOnItemSelectedListener(ccw);
    }

}
