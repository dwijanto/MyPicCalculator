package com.dwijantojohan.lie.picmicrocontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ListCodeActivity extends AppCompatActivity {
    public int callerActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_code);
        initialized();
    }

    private void initialized(){
        setTitle(R.string.TitleListCodeActivity);
        //Intent intent=getIntent();
        //String result = getCallingActivity().getClassName();

        callerActivity = getIntent().getIntExtra("calling_activity",0);
        //GenerateCodeContextWrapper gccw;
        /*switch (callerActivity){
            case ActivityConstant.TIMER0:
                gccw = new GenerateCodeContextWrapper(this);

                break;
            case ActivityConstant.TIMER1:
                break;
            case ActivityConstant.TIMER2:
                break;
        }*/
        GenerateCodeContextWrapper gccw = new GenerateCodeContextWrapper(this);

        ListView l = (ListView) findViewById(R.id.listView);
        l.setAdapter(new CodeCustomAdapter(this,gccw.getModel()));



    }
}
