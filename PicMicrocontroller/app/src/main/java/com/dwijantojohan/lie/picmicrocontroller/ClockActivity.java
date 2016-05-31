package com.dwijantojohan.lie.picmicrocontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ClockActivity extends AppCompatActivity {

    private ClockContextWrapper ccw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        initialized();
    }

    private void initialized(){
        setTitle(R.string.TitleClockActivity);
        ccw = new ClockContextWrapper(this);
        ccw.loadSharedPreferences();
        listener();
    }

    private void listener(){
        ccw.AcceptBtn.setOnClickListener(ccw);
        ccw.spinner.setOnItemSelectedListener(ccw);
    }

}
