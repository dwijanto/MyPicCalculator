package com.dwijantojohan.lie.picmicrocontroller;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.Timer;

/**
 * Created by dlie on 5/25/2016.
 */
public class Calculator extends AppCompatActivity {
    private EditText editText2;
    private EditText editText3;
    private TextView lowText;
    private TextView highText;
    private Double MinTime;
    private Double MaxTime;
    private int prescallerValue = 1;
    private int clockSourceValue=4;
    private int myClock;
    private AppCompatActivity me;
    private String parent;
    private int MaxPreloadValue;
    private String TimerErrMsg;
    private String PreloadErrMsg;
    private String SMinTime;
    private String SMaxTime;
    private Object Timer;
    private Context context;
    private AppCompatActivity appCompatActivity;

    public Calculator(int myClock, int MaxPreloadValue,EditText editText2,EditText editText3,TextView lowText,TextView highText,AppCompatActivity me,String TimerErrMsg,String PreloadErrMsg){
        this.MaxPreloadValue = MaxPreloadValue;
        this.editText2 = editText2;
        this.editText3 = editText3;
        this.lowText = lowText;
        this.highText = highText;
        this.myClock = myClock;
        this.me = me;
        this.TimerErrMsg = TimerErrMsg;
        this.PreloadErrMsg = PreloadErrMsg;

    }
    public Calculator(Context context,AppCompatActivity appCompatActivity){
        this.context = context;
        this.appCompatActivity = appCompatActivity;


    }

    public void calculatePreload(int prescallerValue,int clockSourceValue){
        this.prescallerValue = prescallerValue;
        this.clockSourceValue = clockSourceValue;
        int preload;

        String value= editText3.getText().toString();

        if(Helper.validateText((value))){
            if(MinTime <= (Double)Helper.result && MaxTime >= (Double) Helper.result){
                double p = (Double) Helper.result;
                preload = Helper.getPreload(p,myClock,prescallerValue,MaxPreloadValue,clockSourceValue);
                String HexValue = String.format("%02x",preload);
                editText2.setText(String.format("0x%s",HexValue.toUpperCase()));
            }else{
                setDefaultValue((String)TimerErrMsg);
            }
        }else{
            setDefaultValue((String)TimerErrMsg);
        }
    }

    public void calculateTimer(int prescallerValue,int clockSourceValue){
        this.prescallerValue = prescallerValue;
        this.clockSourceValue = clockSourceValue;
        int minReload =0,maxReload=MaxPreloadValue-1,curReload =0;
        String value = editText2.getText().toString();
        if(Helper.validateText(value)){
            curReload = (int) Helper.result;
            SMinTime = Helper.getTout(maxReload,myClock,prescallerValue,MaxPreloadValue,clockSourceValue);
            lowText.setText(SMinTime);
            MinTime = Helper.ToutResult;
            editText3.setText( Helper.getTout(curReload,myClock,prescallerValue,MaxPreloadValue,clockSourceValue));
            SMaxTime = Helper.getTout(minReload,myClock,prescallerValue,MaxPreloadValue,clockSourceValue);
            highText.setText(SMaxTime);
            Helper.validateText(SMaxTime);
            MaxTime = (Double) Helper.result;
        }else{
            setDefaultValue((String)PreloadErrMsg);
        }
    }

    public void setDefaultValue(String message){
        Toast.makeText(me.getApplicationContext(),String.format("%s",message),Toast.LENGTH_LONG).show();
        editText2.setText("0x00");
        calculateTimer(prescallerValue,clockSourceValue);
    }

    public void setText(){
        //TextView textView = (TextView) ((Activity) this.getBaseContext()).findViewById(R.id.textView2);
        //AppCompatActivity act = (AppCompatActivity) getBaseContext();
        TextView textView = (TextView) appCompatActivity.findViewById(R.id.textView2);
        // View v = LayoutInflater.from(getBaseContext()).inflate(R.layout.activity_timer0,null);
        // TextView textView = (TextView) v.findViewById(R.id.textView2);
        textView.setText("HI");
    }
}
