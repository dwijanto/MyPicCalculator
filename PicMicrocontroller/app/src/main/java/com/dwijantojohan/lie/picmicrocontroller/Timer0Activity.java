package com.dwijantojohan.lie.picmicrocontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Timer0Activity extends AppCompatActivity implements ActivityConstant {
    //public final static String PASSING_MESSAGE="null";
    private final int prescallerValue = 1;
    private final int clockSourceValue = 4;
    private final int MaxPreload = 256;
    private CalculatorContextWrapper ccw;
    private GenerateCodeContextWrapper gccw;
    public final static int callingActivity = ActivityConstant.TIMER0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer0);
        initialized();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.action_code:
                //Toast.makeText(this.getBaseContext(),String.format("%s","Code"),Toast.LENGTH_LONG).show();
                //gccw = new GenerateCodeContextWrapper(this);

                //intent = new Intent(getApplicationContext(),ListCodeActivity.class);
                intent = new Intent(this,ListCodeActivity.class);
                intent.putExtra("calling_activity", callingActivity);

                //startActivityForResult(intent,1);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                return true;
            case R.id.action_settings:
                Toast.makeText(this.getBaseContext(),String.format("%s","Settings"),Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void initialized(){
        setTitle(R.string.TitleTimer0Activity);
        ccw = new CalculatorContextWrapper(this,MaxPreload,callingActivity);
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
