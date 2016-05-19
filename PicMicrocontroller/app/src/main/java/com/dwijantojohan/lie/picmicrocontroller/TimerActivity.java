package com.dwijantojohan.lie.picmicrocontroller;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle(R.string.TitleTimerActivity);
        setContentView(R.layout.activity_timer);
    }

    public void onClick(View view){
        CharSequence text = String.format("Ver : %d",Build.VERSION.SDK_INT);
        Button b = (Button) view;
        int bId = view.getId();
        Resources res = b.getResources();
        String idString = "noId";
        idString = res.getResourceEntryName(bId);
        Intent intent = null;

        switch (idString){
            case "button":
                intent = new Intent(getApplicationContext(),ClockActivity.class);
                break;
            case "button1":
                intent = new Intent(getApplicationContext(),Timer0Activity.class);
                break;
            case "button2":
                intent = new Intent(getApplicationContext(),Timer1Activity.class);
                break;
            case "button3":
                intent = new Intent(getApplicationContext(),Timer2Activity.class);
        }

        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

        //Toast toast = Toast.makeText(getApplicationContext(),String.format("Button Id : %s",idString),Toast.LENGTH_SHORT);
        //toast.show();
    }

}
